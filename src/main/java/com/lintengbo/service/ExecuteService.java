package com.lintengbo.service;

import com.lintengbo.pojo.Activity;

import java.io.IOException;
import java.util.List;

public interface ExecuteService {
    List<Activity> execute() throws IOException;
}
