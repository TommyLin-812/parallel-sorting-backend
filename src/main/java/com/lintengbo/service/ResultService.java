package com.lintengbo.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ResultService {
    void getSortedData(HttpServletRequest request, HttpServletResponse response);
}
