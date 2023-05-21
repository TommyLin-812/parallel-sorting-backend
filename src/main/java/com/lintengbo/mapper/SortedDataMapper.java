package com.lintengbo.mapper;

import com.lintengbo.pojo.Num;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SortedDataMapper {
    @Insert("insert into parallelsortingdata.sorteddata value (#{num})")
    void insert(Integer num);

    @Delete("delete from parallelsortingdata.sorteddata")
    void clearAll();

    @Select("select num from parallelsortingdata.sorteddata limit #{index}, 50")
    List<Num> selectByStartIndex(Integer index);
}
