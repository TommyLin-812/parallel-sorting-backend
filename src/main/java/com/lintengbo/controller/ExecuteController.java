package com.lintengbo.controller;

import com.lintengbo.pojo.Activity;
import com.lintengbo.pojo.Result;
import com.lintengbo.service.ExecuteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class ExecuteController {
    @Autowired
    private ExecuteService executeService;

    @GetMapping("/execute")
    public Result execute() {
        log.info("开始执行排序");

        List<Activity> activityList;
        try {
            activityList = executeService.execute();
        } catch (IOException e) {
            return Result.error("待排序文件操作失败！");
        }

        return Result.success(activityList);
    }
}
