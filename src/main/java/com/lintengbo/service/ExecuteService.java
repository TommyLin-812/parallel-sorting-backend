package com.lintengbo.service;

import com.lintengbo.pojo.ExecuteData;

import java.io.IOException;
import java.util.List;

public interface ExecuteService {
    List<List<ExecuteData>> execute() throws IOException;
}
