package com.lintengbo.controller;

import com.lintengbo.pojo.Param;
import com.lintengbo.pojo.Result;
import com.lintengbo.service.ParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/param")
public class ParamController {
    @Autowired
    private ParamService paramService;

    @GetMapping
    public Result getParam() {
        log.info("获取程序运行参数设置");

        Param param = paramService.getParam();

        return Result.success(param);
    }

    @PostMapping
    public Result setParam(@RequestBody Param param) {
        log.info("提交程序运行参数设置：{}", param);

        paramService.setParam(param);

        return Result.success();
    }
}
