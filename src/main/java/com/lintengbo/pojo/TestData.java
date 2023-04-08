package com.lintengbo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestData {
    private Integer dataQty;
    private Short threadNum;
    private Integer costTime;
}
