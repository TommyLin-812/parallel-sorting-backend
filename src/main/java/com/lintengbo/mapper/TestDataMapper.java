package com.lintengbo.mapper;

import com.lintengbo.pojo.TestData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestDataMapper {
    @Insert("insert into parallelsortingdata.testdata value (#{dataQty}, #{threadNum}, #{costTime})")
    void insert(TestData testData);

    @Select("select distinct data_qty from parallelsortingdata.testdata")
    List<Integer> getDataQty();

    @Select("select data_qty, thread_num, avg(cost_time) cost_time from parallelsortingdata.testdata where data_qty=#{dataQty}  group by thread_num, data_qty order by thread_num")
    List<TestData> getTestData(Integer dataQty);
}
