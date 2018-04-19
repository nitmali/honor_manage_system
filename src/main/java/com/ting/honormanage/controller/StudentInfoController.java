package com.ting.honormanage.controller;

import com.ting.honormanage.entity.StudentInfo;
import com.ting.honormanage.model.ChangePasswordModel;
import com.ting.honormanage.repository.StudentInfoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/3/27 9:56
 */

@RestController
public class StudentInfoController {
    @Resource
    private StudentInfoRepository studentInfoRepository;

    @GetMapping("/api/student/get_studentInfo_myself")
    public StudentInfo getStudentMyself(HttpServletRequest request) {
        return studentInfoRepository
                .findStudentInfoByNumber((String) request.getSession().getAttribute("userName"));
    }

    @GetMapping("/api/manager/get_studentInfo_all")
    public List<StudentInfo> getStudentInfoList() {
        return (List<StudentInfo>) studentInfoRepository.findAll();
    }

    @GetMapping("/api/manager/get_studentInfo_number")
    public StudentInfo getStudentFromNumber(String number) {
        return studentInfoRepository.findStudentInfoByNumber(number);
    }

    @GetMapping("/api/manager/get_studentInfo_name")
    public List<StudentInfo> getStudentFromName(String name) {
        System.err.println(name);
        return (List<StudentInfo>) studentInfoRepository.findStudentInfoByName(name);
    }

    @PostMapping("/api/manager/add_studentInfo")
    public String addStudentInfo(@RequestBody StudentInfo studentInfo) {

        StudentInfo studentInfo1 = studentInfoRepository.findStudentInfoByNumber(studentInfo.getNumber());
        if (studentInfo1 == null) {
            studentInfoRepository.save(studentInfo);
        } else {
            return "{\"message\":\"student already exists\"}";
        }
        return "{\"message\":\"add studentInfo success\"}";
    }

    @PostMapping("/api/manager/update_studentInfo")
    public String updateStudentInfo(@RequestBody StudentInfo studentInfo) {
        StudentInfo studentInfo1 = studentInfoRepository.findStudentInfoByNumber(studentInfo.getNumber());
        if (studentInfo1 == null) {
            return "{\"message\":\"not find student\"}";
        } else {
            studentInfo1.setStudentInfo(studentInfo);
            studentInfoRepository.save(studentInfo1);
        }
        return "{\"message\":\"update studentInfo success\"}";
    }

    @GetMapping("/api/manager/delete_studentInfo")
    public String deleteStudentInfo(String number) {
        StudentInfo studentInfo = studentInfoRepository.findStudentInfoByNumber(number);
        {
            if (studentInfo == null) {
                return "{\"message\":\"not find student\"}";
            } else {
                studentInfoRepository.delete(studentInfo.getId());
            }
        }
        return "{\"message\":\"request success\"}";
    }

    @PostMapping("/api/student/change_studentPassword")
    public String changeStudentPassword(@RequestBody ChangePasswordModel changePasswordModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        StudentInfo studentInfo = studentInfoRepository
                .findStudentInfoByNumber((String) session.getAttribute("userName"));
        if (changePasswordModel.getOldPassword().equals(studentInfo.getPassword())) {
            studentInfo.setPassword(changePasswordModel.getNewPassword());
            studentInfoRepository.save(studentInfo);
            return "{\"message\":\"change password success\"}";
        } else {
            return "{\"message\":\"old password error\"}";
        }
    }
}
