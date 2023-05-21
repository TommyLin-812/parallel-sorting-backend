package com.lintengbo.controller;

import com.lintengbo.pojo.Num;
import com.lintengbo.pojo.Result;
import com.lintengbo.service.MissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/mission")
public class MissionController {
    @Autowired
    private MissionService missionService;

    @GetMapping("/{page}")
    public Result getSortedDataByPage(@PathVariable Integer page) {
        List<Num> sortedData = missionService.getOriginalDataByPage(page); //获取排序后数据

        return Result.success(sortedData);
    }
}
