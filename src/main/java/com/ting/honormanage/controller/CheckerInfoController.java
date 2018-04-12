package com.ting.honormanage.controller;

import com.ting.honormanage.entity.CheckerInfo;
import com.ting.honormanage.model.ChangePasswordModel;
import com.ting.honormanage.model.CheckerInfoModel;
import com.ting.honormanage.repository.CheckerInfoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nitmali
 */
@RestController
public class CheckerInfoController {
    @Resource
    private CheckerInfoRepository checkerInfoRepository;

    @GetMapping("/api/checker/get_checkerInfo")
    public CheckerInfoModel getCheckerInfo(HttpServletRequest request) {
        return new CheckerInfoModel(checkerInfoRepository
                .findCheckerInfoByUsername((String) request.getSession().getAttribute("userName")));
    }

    @GetMapping("/api/manager/get_checkerInfo_id")
    public CheckerInfoModel getCheckerInfoFromId(Long id) {
        return new CheckerInfoModel(checkerInfoRepository.findCheckerInfoById(id));
    }

    @GetMapping("/api/manager/get_checkerInfo_all")
    public List<CheckerInfoModel> getCheckerInfoList() {
        List<CheckerInfoModel> checkerInfoModelArrayList = new ArrayList<>();
        List<CheckerInfo> checkerInfoList = (List<CheckerInfo>) checkerInfoRepository.findAll();
        for (CheckerInfo aCheckerInfoList : checkerInfoList) {
            CheckerInfoModel checkerInfoModel = new CheckerInfoModel(aCheckerInfoList);
            checkerInfoModelArrayList.add(checkerInfoModel);
        }
        return checkerInfoModelArrayList;
    }

    @GetMapping("/api/manager/get_checkerInfo_name")
    public List<CheckerInfoModel> getCheckerInfoFromName(String name) {
        List<CheckerInfoModel> checkerInfoModelArrayList = new ArrayList<>();
        List<CheckerInfo> checkerInfoList = checkerInfoRepository.findCheckerInfoByName(name);
        for (CheckerInfo aCheckerInfoList : checkerInfoList) {
            CheckerInfoModel checkerInfoModel = new CheckerInfoModel(aCheckerInfoList);
            checkerInfoModelArrayList.add(checkerInfoModel);
        }
        return checkerInfoModelArrayList;
    }

    @GetMapping("/api/manager/get_checkerInfo_username")
    public String getCheckerInfoFromUsername(String username) {
        CheckerInfo checkerInfo = checkerInfoRepository.findCheckerInfoByUsername(username);
        if (checkerInfo == null) {
            return null;
        } else {
            return "{\"message\":\"checker already exists\"}";
        }
    }

    @GetMapping("/api/manager/get_checkerInfo_phone")
    public List<CheckerInfoModel> getCheckerInfoFromPhone(String phone) {
        List<CheckerInfoModel> checkerInfoModelArrayList = new ArrayList<>();
        List<CheckerInfo> checkerInfoList = checkerInfoRepository.findCheckerInfoByPhone(phone);
        for (CheckerInfo aCheckerInfoList : checkerInfoList) {
            CheckerInfoModel checkerInfoModel = new CheckerInfoModel(aCheckerInfoList);
            checkerInfoModelArrayList.add(checkerInfoModel);
        }
        return checkerInfoModelArrayList;
    }

    @GetMapping("/api/manager/get_checkerInfo_authority")
    public List<CheckerInfoModel> getCheckerInfoFromAuthority(String authority) {
        List<CheckerInfoModel> checkerInfoModelArrayList = new ArrayList<>();
        List<CheckerInfo> checkerInfoList = checkerInfoRepository.findCheckerInfoByAuthority(authority);
        for (CheckerInfo aCheckerInfoList : checkerInfoList) {
            CheckerInfoModel checkerInfoModel = new CheckerInfoModel(aCheckerInfoList);
            checkerInfoModelArrayList.add(checkerInfoModel);
        }
        return checkerInfoModelArrayList;
    }

    @PostMapping("/api/manager/add_checkerInfo")
    public String addCheckerInfo(@RequestBody CheckerInfoModel checkerInfoModel) {
        CheckerInfo checkerInfo1 = checkerInfoRepository.findCheckerInfoByUsername(checkerInfoModel.getUsername());
        if (checkerInfo1 != null) {
            return "{\"message\":\"username error\"}";
        } else {
            CheckerInfo checkerInfo = new CheckerInfo(checkerInfoModel);
            checkerInfoRepository.save(checkerInfo);
            return "{\"message\":\"add checkerInfo success\"}";
        }
    }

    @PostMapping("/api/manager/update_checkerInfo")
    public String updateCheckerInfo(@RequestBody CheckerInfoModel checkerInfoModel) {
        CheckerInfo checkerInfo1 = checkerInfoRepository.findCheckerInfoById(checkerInfoModel.getId());
        if (checkerInfo1 == null) {
            return "{\"message\":\"checker not find\"}";
        } else {
            if (checkerInfoModel.getPassword() == null) {
                checkerInfoModel.setPassword(checkerInfo1.getPassword());
            }
            checkerInfo1.setCheckerInfoFromModel(checkerInfoModel);
            checkerInfoRepository.save(checkerInfo1);
        }
        return "{\"message\":\"update checkerInfo success\"}";
    }

    @PostMapping("/api/manager/delete_checkerInfo")
    public String deleteCheckerInfo(@RequestBody CheckerInfoModel checkerInfoModel) {
        checkerInfoRepository.delete(checkerInfoModel.getId());
        return "{\"message\":\"delete checkerInfo success\"}";
    }

    @PostMapping("/api/checker/change_checkerPassword")
    public String changeCheckerPassword(@RequestBody ChangePasswordModel changePasswordModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        CheckerInfo checkerInfo = checkerInfoRepository
                .findCheckerInfoByUsername((String) session.getAttribute("userName"));
        if (changePasswordModel.getOldPassword().equals(checkerInfo.getPassword())) {
            checkerInfo.setPassword(changePasswordModel.getNewPassword());
            checkerInfoRepository.save(checkerInfo);
            return "{\"message\":\"change password success\"}";
        } else {
            return "{\"message\":\"old password error\"}";
        }
    }
}
