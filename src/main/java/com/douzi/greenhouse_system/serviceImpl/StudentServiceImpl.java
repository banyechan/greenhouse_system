package com.douzi.greenhouse_system.serviceImpl;

import com.douzi.greenhouse_system.entity.StudentModel;
import com.douzi.greenhouse_system.mapper.StudentModelMapper;
import com.douzi.greenhouse_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentModelMapper studentMapper;

    @Override
    public StudentModel getByByPrimaryKey(Integer id) {

        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StudentModel> listStudent(StudentModel record) {
        return studentMapper.listByCondition(record);
    }

    @Override
    public boolean saveStudent(StudentModel record) {
        int flag = studentMapper.insertSelective(record);
        return flag > 0;
    }

    @Override
    public boolean updateStudent(StudentModel record) {
        int flag = studentMapper.updateByPrimaryKeySelective(record);
        return flag > 0;
    }

    @Override
    public boolean deleteStudent(Integer id) {
        int flag = studentMapper.deleteByPrimaryKey(id);
        return flag > 0;
    }

    @Override
    public List<StudentModel> listAllStudent() {
        return studentMapper.listStudent();
    }


}
