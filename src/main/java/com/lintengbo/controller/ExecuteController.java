package com.lintengbo.controller;

import com.lintengbo.pojo.ExecuteData;
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

        List<List<ExecuteData>> executeDataList;    //用于存放运行过程的列表
        try {
            executeDataList = executeService.execute();    //执行排序，并获取运行过程信息
        } catch (IOException e) {
            return Result.error("待排序文件操作失败！");
        }

        return Result.success(executeDataList);
    }
}
