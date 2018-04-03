package com.ting.honormanage.controller;

import com.ting.honormanage.entity.StudentInfo;
import com.ting.honormanage.repository.StudentRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/3/27 9:56
 */

@RestController
public class StudentController {
    @Resource
    private StudentRepository studentRepository;

    @GetMapping("/get_studentInfo_all")
    public List<StudentInfo> studentInfoList() {
        return (List<StudentInfo>) studentRepository.findAll();
    }

    @GetMapping("/get_studentInfo_number")
    public StudentInfo getStudentFromNumber(String number) {
        return studentRepository.findStudentInfoByNumber(number);
    }

    @GetMapping("/get_studentInfo_name")
    public List<StudentInfo> getStudentFromName(String name) {
        System.err.println(name);
        return (List<StudentInfo>) studentRepository.findStudentInfoByName(name);
    }

    @PostMapping("/add_studentInfo")
    public String addStudentInfo(@RequestBody StudentInfo studentInfo) {

        StudentInfo studentInfo1 = studentRepository.findStudentInfoByNumber(studentInfo.getNumber());
        if (studentInfo1 == null) {
            studentRepository.save(studentInfo);
        } else {
            return "{\"message\":\"student already exists\"}";
        }
        return "{\"message\":\"add studentInfo success\"}";
    }

    @PostMapping("/update_studentInfo")
    public String updateStudentInfo(@RequestBody StudentInfo studentInfo) {
        StudentInfo studentInfo1 = studentRepository.findStudentInfoByNumber(studentInfo.getNumber());
        if (studentInfo1 == null) {
            return "not find student";
        } else {
            studentInfo1.setStudentInfo(studentInfo);
            studentRepository.save(studentInfo1);
        }
        return "{\"message\":\"update studentInfo success\"}";
    }

    @GetMapping("/delete_studentInfo")
    public String deleteStudentInfo(String number) {
        StudentInfo studentInfo = studentRepository.findStudentInfoByNumber(number);
        {
            if (studentInfo == null) {
                return "{\"message\":\"not find student\"}";
            } else {
                studentRepository.delete(studentInfo.getId());
            }
        }
        return "{\"message\":\"request success\"}";
    }

}
