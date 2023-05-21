package com.lintengbo.service.impl;

import com.lintengbo.mapper.OriginalDataMapper;
import com.lintengbo.mapper.ParamMapper;
import com.lintengbo.mapper.SortedDataMapper;
import com.lintengbo.mapper.TestDataMapper;
import com.lintengbo.pojo.ExecuteData;
import com.lintengbo.pojo.Param;
import com.lintengbo.pojo.TestData;
import com.lintengbo.service.ExecuteService;
import com.lintengbo.utils.MultiThreadMergeSort;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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

    @Autowired
    private SortedDataMapper sortedDataMapper;

    @Autowired
    private OriginalDataMapper originalDataMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<List<ExecuteData>> execute() throws IOException {
        Param param = paramMapper.getParam();
        int qty = param.getDataQty();
        short threadNum = param.getThreadNum();
        short executeTimes = param.getExecuteTimes();
        boolean saveResult = param.getSaveResult();

        File file = new File("RandomData.txt");
        int[] arr = new int[qty];
        int[] result = new int[0];

        List<List<ExecuteData>> executeDataList = null;

        if (originalDataMapper.selectByStartIndex(0).isEmpty()) {
            SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            OriginalDataMapper originalDataMapper1 = sqlSession.getMapper(OriginalDataMapper.class);
            int num;
            for (int i = 0; i < 10000000; i++) {
                num = (int) (random() * 100000000);
                if (i < qty) arr[i] = num;
                originalDataMapper1.insert(num);
            }
            sqlSession.commit();
            sqlSession.clearCache();
        } else arr = originalDataMapper.selectByTotal(qty);

        for (int i = 0; i < executeTimes; i++) {
            executeDataList = new ArrayList<>();

            result = new int[qty];
            System.arraycopy(arr, 0, result, 0, qty);
            MultiThreadMergeSort.initArray(result);

            long startTime = System.currentTimeMillis();
            MultiThreadMergeSort.startSorting(threadNum, executeDataList);
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;

            TestData testData = new TestData(qty, threadNum, (int) costTime);
            testDataMapper.insert(testData);
        }

        if (saveResult) {
            sortedDataMapper.clearAll();
            SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            SortedDataMapper sortedDataMapper1 = sqlSession.getMapper(SortedDataMapper.class);
            for (int i : result) {
                sortedDataMapper1.insert(i);
            }
            sqlSession.commit();
            sqlSession.clearCache();
        }

        return executeDataList;
    }
}
