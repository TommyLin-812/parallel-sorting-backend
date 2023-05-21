package com.lintengbo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestData {
    private Integer dataQty;    //数据规模
    private Short threadNum;    //线程数量
    private Integer costTime;   //消耗时间
}
