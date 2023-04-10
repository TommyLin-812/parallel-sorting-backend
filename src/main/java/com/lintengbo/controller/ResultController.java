package com.lintengbo.controller;

import com.lintengbo.service.ResultService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin
public class ResultController {
    @Autowired
    private ResultService resultService;

    @GetMapping("/result")
    public void getSortedData(HttpServletRequest request, HttpServletResponse response) {
        resultService.getSortedData(request, response);
    }
}
