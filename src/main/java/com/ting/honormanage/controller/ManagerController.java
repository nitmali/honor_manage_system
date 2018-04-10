package com.ting.honormanage.controller;

import com.ting.honormanage.entity.ManagerInfo;
import com.ting.honormanage.repository.ManagerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/4/10 15:25
 */

@RestController
public class ManagerController {
    @Resource
    private ManagerRepository managerRepository;

    @PostMapping("/api/manager/add_managerInfo")
    public String addManagerInfo(@RequestBody ManagerInfo managerInfo) {
        ManagerInfo managerInfo1 = managerRepository.findManagerInfoByUsername(managerInfo.getUsername());
        if (managerInfo1 != null) {
            return "{\"message\":\"username error\"}";
        } else {
            managerRepository.save(managerInfo);
            return "{\"message\":\"add managerInfo success\"}";
        }
    }


    @PostMapping("/api/manager/delete_managerInfo")
    public String deleteManagerInfo(@RequestBody ManagerInfo managerInfo) {
        ManagerInfo managerInfo1 = managerRepository.findManagerInfoByUsername(managerInfo.getUsername());
        if (managerInfo1 == null) {
            return "{\"message\":\"managerInfo not find\"}";
        } else {
            managerRepository.delete(managerInfo1);
            return "{\"message\":\"delete managerInfo success\"}";
        }
    }

    @PostMapping("/api/manager/update_managerInfo")
    public String updateManagerInfo(@RequestBody ManagerInfo managerInfo) {
        ManagerInfo managerInfo1 = managerRepository.findManagerInfoByUsername(managerInfo.getUsername());
        if (managerInfo1 == null) {
            return "{\"message\":\"managerInfo not find\"}";
        } else {
            managerInfo1.setPassword(managerInfo.getPassword());
            managerRepository.save(managerInfo1);
            return "{\"message\":\"update managerInfo success\"}";
        }
    }

    @GetMapping("/api/manager/get_managerInfo_all")
    public List<ManagerInfo> getManagerInfoAll() {
        return (List<ManagerInfo>) managerRepository.findAll();
    }
}
