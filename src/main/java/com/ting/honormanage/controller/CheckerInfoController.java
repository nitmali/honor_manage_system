package com.ting.honormanage.controller;

import com.ting.honormanage.entity.CheckerInfo;
import com.ting.honormanage.repository.CheckerInfoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nitmali
 */
@RestController
public class CheckerInfoController {
    @Resource
    private CheckerInfoRepository checkerInfoRepository;

    @GetMapping("/get_checkerInfo_one")
    public CheckerInfo checkerInfoOne(Long id){
        return checkerInfoRepository.findCheckerInfoById(id);
    }

    @GetMapping("/get_checkerInfo_all")
    public List<CheckerInfo> checkerInfoList() {
        return (List<CheckerInfo>) checkerInfoRepository.findAll();
    }

    @GetMapping("/get_checkerInfo_name")
    public List<CheckerInfo> getCheckerFromName(String name) {
        System.err.println(name);
        return checkerInfoRepository.findCheckerInfoByName(name);
    }

    @GetMapping("/get_checkerInfo_username")
    public List<CheckerInfo> getCheckerFromUsername(String username) {
        return checkerInfoRepository.findCheckerInfoByUsername(username);
    }

    @GetMapping("/get_checkerInfo_phone")
    public List<CheckerInfo> getCheckerFromPhone(String phone) {
        return checkerInfoRepository.findCheckerInfoByPhone(phone);
    }

    @GetMapping("/get_checkerInfo_authority")
    public List<CheckerInfo> getCheckerFromAuthority(String authority) {
        return checkerInfoRepository.findCheckerInfoByAuthority(authority);
    }

    @PostMapping("/add_checkerInfo")
    public String addCheckerInfo(@RequestBody CheckerInfo checkerInfo) {
        checkerInfoRepository.save(checkerInfo);
        return "{\"message\":\"add checkerInfo success\"}";
    }

    @PostMapping("/update_checkerInfo")
    public String updateCheckerInfo(@RequestBody CheckerInfo checkerInfo) {
        CheckerInfo checkerInfo1 = checkerInfoRepository.findCheckerInfoById(checkerInfo.getId());
        if(checkerInfo1 == null)
        {
            return "{\"message\":\"checker not find\"}";
        }else {
            if("".equals(checkerInfo.getPassword())){
                checkerInfo.setPassword(checkerInfo1.getPassword());
            }
            checkerInfo1.setCheckerInfo(checkerInfo);
            checkerInfoRepository.save(checkerInfo1);
        }
        return "{\"message\":\"update checkerInfo success\"}";
    }

    @PostMapping("/delete_checkerInfo")
    public String deleteCheckerInfo(@RequestBody CheckerInfo checkerInfo) {
        System.err.println(checkerInfo.getId());
        checkerInfoRepository.delete(checkerInfo.getId());
        return "{\"message\":\"delete checkerInfo success\"}";
    }

}
