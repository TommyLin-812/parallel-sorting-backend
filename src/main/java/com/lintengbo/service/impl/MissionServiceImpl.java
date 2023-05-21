package com.lintengbo.service.impl;

import com.lintengbo.mapper.OriginalDataMapper;
import com.lintengbo.pojo.Num;
import com.lintengbo.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionServiceImpl implements MissionService {
    @Autowired
    private OriginalDataMapper originalDataMapper;

    @Override
    public List<Num> getOriginalDataByPage(Integer page) {
        int index = (page-1)*50;
        return originalDataMapper.selectByStartIndex(index);
    }
}
