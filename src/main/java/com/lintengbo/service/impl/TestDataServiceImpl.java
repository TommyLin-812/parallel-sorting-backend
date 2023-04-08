package com.lintengbo.service.impl;

import com.lintengbo.mapper.TestDataMapper;
import com.lintengbo.pojo.TestData;
import com.lintengbo.service.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDataServiceImpl implements TestDataService {
    @Autowired
    private TestDataMapper testDataMapper;

    @Override
    public List<Integer> getDataQty() {
        return testDataMapper.getDataQty();
    }

    @Override
    public List<TestData> getTestData(Integer dataQty) {
        return testDataMapper.getTestData(dataQty);
    }
}
