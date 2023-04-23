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
    public List<Activity> execute() throws IOException {
        Param param = paramMapper.getParam();
        int qty = param.getDataQty();
        short threadNum = param.getThreadNum();
        short executeTimes = param.getExecuteTimes();

        File file = new File("RandomData.txt");
        int[] arr = new int[qty];

        List<Activity> activityList = null;

        if (!file.exists()) {
            int num;
            file.createNewFile();   //如果文件不存在，创建文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));   //创建BufferedWriter对象
            //向文件中写入内容
            for (int i = 0; i < 10000000; i++) {
                num = (int) (random() * 100000000);
                if (i < qty) arr[i] = num;
                bw.write(num + "\n");
            }
            bw.flush();
            bw.close();
        } else {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int i = 0;
            String line;
            while (i < qty) {
                line = br.readLine();
                arr[i++] = Integer.parseInt(line);
            }
            br.close();
        }

        for (int i = 0; i < executeTimes; i++) {
            activityList = new ArrayList<>();

            int[] result = new int[qty];
            System.arraycopy(arr, 0, result, 0, qty);
            MultiThreadMergeSort.initArray(result);

            long startTime = System.currentTimeMillis();
            MultiThreadMergeSort.startSorting(threadNum, activityList);
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;

            TestData testData = new TestData(qty, threadNum, (int) costTime);
            testDataMapper.insert(testData);
        }

        file = new File("SortedData.txt");
        if (!file.exists()) file.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i : arr) {
            bw.write(i + "\n");
        }
        bw.flush();
        bw.close();

        return activityList;
    }
}
