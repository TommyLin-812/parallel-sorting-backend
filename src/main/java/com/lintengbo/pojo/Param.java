package com.lintengbo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Param {
    private Integer dataQty;    //数据规模
    private Short threadNum;    //线程数量
    private Short executeTimes; //执行次数
    private Boolean saveResult; //是否保存排序结果
}
