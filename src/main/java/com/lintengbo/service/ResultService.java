package com.lintengbo.service;

import com.lintengbo.pojo.Num;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface ResultService {
    List<Num> getSortedDataByPage(Integer page);
}
