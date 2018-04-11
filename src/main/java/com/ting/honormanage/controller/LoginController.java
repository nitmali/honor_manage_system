package com.ting.honormanage.controller;

import com.ting.honormanage.entity.CheckerInfo;
import com.ting.honormanage.entity.ManagerInfo;
import com.ting.honormanage.entity.StudentInfo;
import com.ting.honormanage.model.UserModel;
import com.ting.honormanage.repository.CheckerInfoRepository;
import com.ting.honormanage.repository.ManagerInfoRepository;
import com.ting.honormanage.repository.StudentInfoRepository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author nitmali@126.com
 * @date 2018/4/10 10:44
 */

@RestController
public class LoginController {
    @Resource
    private ManagerInfoRepository managerInfoRepository;

    @Resource
    private CheckerInfoRepository checkerInfoRepository;

    @Resource
    private StudentInfoRepository studentInfoRepository;

    @PostMapping("/api/post_manager_login")
    public String managerLogin(@RequestBody UserModel userModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ManagerInfo managerInfo = managerInfoRepository.findManagerInfoByUsername(userModel.getUserName());
        if(managerInfo == null)
        {
            return "{\"message\":\"user not find\"}";
        }else if(managerInfo.getPassword().equals(userModel.getPassword())){
            session.setAttribute("userType", userModel.getUserType());
            session.setAttribute("userName", userModel.getUserName());
            return "{\"message\":\"login success\"}";
        }else {
            return "{\"message\":\"password error\"}";
        }
    }

    @PostMapping("/api/post_checker_login")
    public String checkerLogin(@RequestBody UserModel userModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        CheckerInfo checkerInfo = checkerInfoRepository.findCheckerInfoByUsername(userModel.getUserName());
        if(checkerInfo == null){
            return "{\"message\":\"user not find\"}";
        }else if (checkerInfo.getPassword().equals(userModel.getPassword())){
            session.setAttribute("userType", userModel.getUserType());
            session.setAttribute("userName", userModel.getUserName());
            return "{\"message\":\"login success\"}";
        }else {
            return "{\"message\":\"password error\"}";
        }
    }

    @PostMapping("/api/post_student_login")
    public String studentLogin(@RequestBody UserModel userModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        StudentInfo studentInfo = studentInfoRepository.findStudentInfoByNumber(userModel.getUserName());
        if(studentInfo == null){
            return "{\"message\":\"user not find\"}";
        }else if (studentInfo.getPassword().equals(userModel.getPassword())){
            session.setAttribute("userType", userModel.getUserType());
            session.setAttribute("userName", userModel.getUserName());
            return "{\"message\":\"login success\"}";
        }else {
            return "{\"message\":\"password error\"}";
        }
    }
}
