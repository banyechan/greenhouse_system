package com.douzi.greenhouse_system.mapper;

import com.douzi.greenhouse_system.entity.StudentModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StudentModelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StudentModel record);

    int insertSelective(StudentModel record);

    StudentModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentModel record);

    int updateByPrimaryKey(StudentModel record);

    List<StudentModel> listByCondition(StudentModel record);

    List<StudentModel> listStudent();


}