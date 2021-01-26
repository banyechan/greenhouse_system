package com.douzi.greenhouse_system.service;

import com.douzi.greenhouse_system.entity.StudentModel;

import java.util.List;
import java.util.Map;

public interface StudentService {

    StudentModel getByByPrimaryKey(Integer id);

    List<StudentModel> listStudent(StudentModel record);

    boolean  saveStudent(StudentModel record);

    boolean  updateStudent(StudentModel record);

    boolean  deleteStudent(Integer id);

    List<StudentModel> listAllStudent();

    List<Map<String,String>> getCountBySex();

    Object getById(Integer id);



}
