package com.lintengbo.controller;

import com.lintengbo.pojo.Result;
import com.lintengbo.pojo.TestData;
import com.lintengbo.service.TestDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class TestDataController {
    @Autowired
    private TestDataService testDataService;

    @GetMapping("/dataqty")
    public Result getDataQty() {
        log.info("获取数据规模列表");

        List<Integer> list = testDataService.getDataQty();

        return Result.success(list);
    }

    @GetMapping("/testdata/{dataQty}")
    public Result getTestData(@PathVariable Integer dataQty){
        log.info("获取指定数据规模的实验数据");

        List<TestData> list = testDataService.getTestData(dataQty);

        return Result.success(list);
    }
}
