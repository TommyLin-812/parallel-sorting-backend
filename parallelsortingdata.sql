create table param
(
    id            int unsigned auto_increment comment '主键id'
        primary key,
    data_qty      int unsigned     not null comment '待排序数据规模',
    thread_num    tinyint unsigned not null comment '线程数量',
    execute_times tinyint unsigned not null comment '执行次数'
)
    comment '参数设置表';

create table testdata
(
    data_qty   int unsigned     not null comment '数据规模',
    thread_num tinyint unsigned not null comment '线程数量',
    cost_time  int unsigned     not null comment '执行时间'
)
    comment '实验数据表';


