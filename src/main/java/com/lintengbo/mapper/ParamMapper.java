package com.lintengbo.mapper;

import com.lintengbo.pojo.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ParamMapper {
    @Select("select data_qty, thread_num, execute_times from param where id=1")
    Param getParam();

    @Update("update param set data_qty=#{dataQty}, thread_num=#{threadNum}, execute_times=#{executeTimes} where id=1")
    void setParam(Param param);
}
