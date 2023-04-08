package com.lintengbo.service.impl;

import com.lintengbo.mapper.ParamMapper;
import com.lintengbo.pojo.Param;
import com.lintengbo.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParamServiceImpl implements ParamService {
    @Autowired
    private ParamMapper paramMapper;

    @Override
    public Param getParam() {
        return paramMapper.getParam();
    }

    @Override
    public void setParam(Param param) {
        paramMapper.setParam(param);
    }
}
