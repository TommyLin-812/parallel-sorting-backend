package com.lintengbo.utils;

import com.lintengbo.pojo.Activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Semaphore;

public class MultiThreadMergeSort extends Thread {
    private Thread t;

    private final String threadName;

    private static List<Activity> activityList;

    private static final SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss:SS");

    private static int[] arr;       //目标数组
    private static int[] result;    //辅助数组

    private static Semaphore[] s;   //信号量数组

    private static final Semaphore activityListSemaphore = new Semaphore(1);

    private final int start;        //区域左边界
    private final int mid;          //区域中心
    private final int end;          //区域右边界

    private final boolean flag;     //线程操作标识

    public MultiThreadMergeSort(String threadName, int start, int mid, int end, boolean flag) {
        this.threadName = threadName;
        this.start = start;
        this.mid = mid;
        this.end = end;
        this.flag = flag;

        //System.out.println("Creating " + threadName);
    }

    public static void initArray(int[] arr) {
        MultiThreadMergeSort.arr = arr;
        result = new int[arr.length];
    }

    public void start() {
        //System.out.println("Starting " + threadName);

        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public void run() {
        String timestamp = format.format(new Date());
        String content;
        Activity activity;
        //排序的第一个循环执行完整归并排序操作，后续循环执行合并操作
        if (flag) {
            SingleThreadMergeSort.merge_sort_recursive(arr, result, start, end);
            content = threadName + "号线程正在对" + start + "~" + end + "范围进行归并排序操作。";
        } else {
            SingleThreadMergeSort.merge(arr, result, start, mid, end);
            content = threadName + "号线程正在对" + start + "~" + mid + "和" + (mid + 1) + "~" + end + "范围进行合并操作。";
        }
        activity = new Activity(content, timestamp);

        try {
            activityListSemaphore.acquire();
            activityList.add(activity);
            activityListSemaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //System.out.println("Thread " + threadName + " exiting.");

        s[Integer.parseInt(threadName)].release();  //释放信号量
    }

    public static void startSorting(int threadNum, List<Activity> activityList) {
        MultiThreadMergeSort.activityList = activityList;

        format.setTimeZone(TimeZone.getTimeZone("Etc/GMT-8"));

        //剩余区域检测标识，当初始线程数量为奇数时，第一轮归并排序会产生奇数个区域，导致之后始终剩余最后一个区域没有合并
        boolean hasExtraDiv = false;

        boolean last = false;

        int cycleNum = 0;

        int oldThreadNum = threadNum;

        //初始化
        int len = arr.length;
        int[] start = new int[threadNum];
        int[] mid = new int[threadNum];
        int[] end = new int[threadNum];

        //计算第一次划分区域的步长
        int step = (int) Math.ceil(len * 1.0 / threadNum) - 1;

        //第一次划分区域
        start[0] = 0;
        end[0] = start[0] + step;
        for (int i = 1; i < threadNum; i++) {
            start[i] = end[i - 1] + 1;

            //若是最后一个区域，将右边界设置为整个数组的末尾
            if (i == threadNum - 1)
                end[i] = len - 1;
            else
                end[i] = start[i] + step;
        }

        boolean flag = true;    //标记第一次运行，线程要执行完整归并排序
        while (threadNum > 0) {
            activityList.add(new Activity("程序开始执行第" + ++cycleNum + "轮操作，共" + threadNum + "个线程", format.format(new Date())));

            MultiThreadMergeSort[] t = new MultiThreadMergeSort[threadNum]; //根据线程数量创建线程数组
            s = new Semaphore[threadNum];   //根据线程数量创建信号量数组
            for (int i = 0; i < threadNum; i++) {
                t[i] = new MultiThreadMergeSort(Integer.toString(i), start[i], mid[i], end[i], flag);   //创建线程，分配区域
                s[i] = new Semaphore(0);    //创建对应信号量
            }

            //启动所有线程
            for (int i = 0; i < threadNum; i++) {
                t[i].start();
            }

            //等待所有信号量，即等待所有线程结束
            for (int i = 0; i < threadNum; i++) {
                try {
                    s[i].acquire(); //获取信号量
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (oldThreadNum > 2 && threadNum % 2 == 1 && !last) hasExtraDiv = true;
            int newThreadNum = threadNum / 2; //计算下一循环所需线程数

            //若无剩余区域，且线程数量为0，结束操作
            if (!hasExtraDiv && newThreadNum == 0) return;

            flag = false;   //标记非第一次运行，后续线程进行合并操作

            //再次划分区域
            for (int i = 0; i < newThreadNum; i++) {
                start[i] = start[i * 2];
                mid[i] = end[i * 2];
                end[i] = end[i * 2 + 1];
            }

            //若有剩余区域，需要包括进去
            if (hasExtraDiv) {
                if (newThreadNum == 0) {
                    mid[0] = end[0];
                    end[0] = end[1];
                    newThreadNum++;
                    hasExtraDiv = false;
                    last = true;
                } else {
                    start[newThreadNum] = start[newThreadNum * 2];
                    end[newThreadNum] = end[newThreadNum * 2];
                    mid[newThreadNum] = mid[newThreadNum * 2];
                }
            }
            threadNum = newThreadNum;
        }
    }
}
