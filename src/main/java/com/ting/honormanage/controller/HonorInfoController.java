package com.ting.honormanage.controller;

import com.ting.honormanage.entity.HonorInfo;
import com.ting.honormanage.repository.HonorInfoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/3/31 14:29
 */
@RestController
public class HonorInfoController {
    @Resource
    private HonorInfoRepository honorInfoRepository;

    @GetMapping("/get_honorInfo_all")
    public List<HonorInfo> honorInfoList() {
        return (List<HonorInfo>) honorInfoRepository.findAll();
    }

    @PostMapping("/add_honorInfo")
    public String addHonorInfo(@RequestBody HonorInfo honorInfo) {
        honorInfoRepository.save(honorInfo);
        return "add honorInfo success";
    }

    @GetMapping("/get_honorInfo_yearBetween")
    public List<HonorInfo> getHonorInfoFromYearBetween(Date year1,Date year2) {
        return honorInfoRepository.findHonorInfoByYearBetween(year1,year2);
    }

    @GetMapping("/get_honorInfo_year")
    public List<HonorInfo> getHonorInfoFromYear(Date year) {
        return honorInfoRepository.findHonorInfoByYear(year);
    }

    @GetMapping("/get_honorInfo_id")
    public HonorInfo getHonorInfoFromId(Long id) {
        return honorInfoRepository.findHonorInfoById(id);
    }

    @GetMapping("/get_honorInfo_name")
    public List<HonorInfo> getHonorInfoFromName(String name) {
        return  honorInfoRepository.findHonorInfoByName(name);
    }

    @GetMapping("/get_honorInfo_kind")
    public List<HonorInfo> getHonorInfoFromKind(HonorInfo.Kind kind) {
        return honorInfoRepository.findHonorInfoByKind(kind);
    }

    @GetMapping("/get_honorInfo_status")
    public List<HonorInfo> getHonorInfoFromStdtus(HonorInfo.Status status) {
        return honorInfoRepository.findHonorInfoByStatus(status);
    }


}
