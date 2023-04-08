package com.lintengbo.service.impl;

import com.lintengbo.mapper.ParamMapper;
import com.lintengbo.mapper.TestDataMapper;
import com.lintengbo.pojo.Activity;
import com.lintengbo.pojo.Param;
import com.lintengbo.pojo.TestData;
import com.lintengbo.service.ExecuteService;
import com.lintengbo.utils.MultiThreadMergeSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

@Service
public class ExecuteServiceImpl implements ExecuteService {
    @Autowired
    private ParamMapper paramMapper;

    @Autowired
    private TestDataMapper testDataMapper;

    @Override
    public void execute() throws IOException {
        Param param = paramMapper.getParam();
        int qty = param.getDataQty();
        short threadNum = param.getThreadNum();
        short executeTimes = param.getExecuteTimes();

        String dir = "RandomData-" + qty + ".txt";
        File file = new File(dir);
        int[] arr = new int[qty];

        List<Activity> activityList=new ArrayList<>();

        if (!file.exists()) {
            int num;
            file.createNewFile();   //如果文件不存在，创建文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));   //创建BufferedWriter对象
            //向文件中写入内容
            for (int i = 0; i < qty; i++) {
                num = (int) (random() * 1000000);
                arr[i] = num;
                bw.write(num + "\n");
            }
            bw.flush();
            bw.close();
        } else {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int i = 0;
            String line;
            while ((line = br.readLine()) != null) {
                arr[i++] = Integer.parseInt(line);
            }
            br.close();
        }

        MultiThreadMergeSort.initArray(arr);

        for (int i = 0; i < executeTimes; i++) {
            long startTime = System.currentTimeMillis();
            MultiThreadMergeSort.startSorting(threadNum);
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;

            TestData testData = new TestData(qty, threadNum, (int) costTime);
            testDataMapper.insert(testData);
        }

        dir="SortedData-"+qty+".txt";
        file=new File(dir);
        if (!file.exists()) file.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i : arr) {
            bw.write(i + "\n");
        }
        bw.flush();
        bw.close();
    }
}
