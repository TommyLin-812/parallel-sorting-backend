package com.lintengbo.service.impl;

import com.lintengbo.mapper.SortedDataMapper;
import com.lintengbo.pojo.Num;
import com.lintengbo.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private SortedDataMapper sortedDataMapper;

    @Override
    public List<Num> getSortedDataByPage(Integer page){
        int index = (page-1)*50;
        return sortedDataMapper.selectByStartIndex(index);
    }
}
