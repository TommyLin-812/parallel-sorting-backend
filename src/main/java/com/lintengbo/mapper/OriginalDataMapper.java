package com.lintengbo.mapper;

import com.lintengbo.pojo.Num;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OriginalDataMapper {
    @Insert("insert into parallelsortingdata.originaldata value (#{num})")
    void insert(Integer num);

    @Select("select num from parallelsortingdata.originaldata limit #{index}, 50")
    List<Num> selectByStartIndex(Integer index);

    @Select("select num from parallelsortingdata.originaldata limit 0, #{total}")
    int[] selectByTotal(Integer total);
}
