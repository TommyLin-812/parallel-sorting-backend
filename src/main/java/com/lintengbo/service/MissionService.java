package com.lintengbo.service;

import com.lintengbo.pojo.Num;

import java.util.List;

public interface MissionService {
    List<Num> getOriginalDataByPage(Integer page);
}
