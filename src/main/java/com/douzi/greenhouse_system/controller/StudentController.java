package com.douzi.greenhouse_system.controller;

import com.douzi.greenhouse_system.entity.StudentModel;
import com.douzi.greenhouse_system.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/list")
    public List<StudentModel> getStudentList(@RequestBody StudentModel record) {
        log.info("----  调用列表查询接口 -----");
        List<StudentModel> resultList = studentService.listStudent(record);
        return resultList;
    }

    @GetMapping("/get")
    public StudentModel getStudent(@RequestParam("id") Integer id) {
        log.info("----  调用单个查询接口,id:{} -----",id);
        StudentModel result = studentService.getByByPrimaryKey(id);
        return result;
    }

    @PostMapping("/add")
    public boolean addStudent(@RequestBody StudentModel record) {
        log.info("----  调用新增接口 -----");
        return studentService.saveStudent(record);
    }

    @PostMapping("/update")
    public boolean updateStudent(@RequestBody StudentModel record) {
        log.info("----  调用修改接口 -----");
        return studentService.updateStudent(record);
    }

    @DeleteMapping("/del")
    public boolean deleteStudent(Integer id) {
        log.info("----  调用删除接口,id:{} -----",id);
        return studentService.deleteStudent(id);
    }


    @GetMapping("/getMap")
    public Map<String,String> getCountBySex() {
        log.info("----  调用getCountBySex -----");
        Map<String,String> map = new HashMap<>();
        List<Map<String,String>> mapList = studentService.getCountBySex();
        if(mapList != null && mapList.size() > 0){
            for(Map tem : mapList){
                log.info("key = {},value = {}",tem.get("type"),tem.get("num"));
                map.put(tem.get("type").toString(),tem.get("num").toString());
            }
        }

        return map;
    }



    @GetMapping("/getObject")
    public Object getStudent2(@RequestParam("id") Integer id) {
        log.info("----  调用单个查询接口,id:{} -----",id);
        Object result = studentService.getById(id);
        return result;
    }
}
