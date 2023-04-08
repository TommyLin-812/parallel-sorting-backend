package com.lintengbo.service;

import com.lintengbo.pojo.TestData;

import java.util.List;

public interface TestDataService {
    List<Integer> getDataQty();

    List<TestData> getTestData(Integer dataQty);
}
