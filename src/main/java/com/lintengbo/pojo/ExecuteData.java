package com.lintengbo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteData {
    private Integer id; //线程号
    private String startTime;   //开始时间
    private String endTime;   //开始时间
    private Integer costTime;   //执行时间
    private String content; //活动内容
}
