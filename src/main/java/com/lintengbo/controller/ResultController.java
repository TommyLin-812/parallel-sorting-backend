package com.lintengbo.controller;

import com.lintengbo.pojo.Num;
import com.lintengbo.pojo.Result;
import com.lintengbo.service.ResultService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class ResultController {
    @Autowired
    private ResultService resultService;

    @GetMapping("/result/{page}")
    public Result getSortedDataByPage(@PathVariable Integer page) {
        List<Num> sortedData = resultService.getSortedDataByPage(page); //获取排序后数据

        return Result.success(sortedData);
    }
}
