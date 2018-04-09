package com.ting.honormanage.controller;

import com.ting.honormanage.entity.HonorInfo;
import com.ting.honormanage.model.HonorInfoModel;
import com.ting.honormanage.repository.HonorInfoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public List<HonorInfoModel> honorInfoModelList() {
        List<HonorInfoModel> honorInfoModelArrayList = new ArrayList<>();
        List<HonorInfo> honorInfoList = (List<HonorInfo>) honorInfoRepository.findAll();
        for (int i = 0; i < honorInfoList.size() - 1; i++){
            HonorInfoModel honorInfoModel = new HonorInfoModel(honorInfoList.get(i));
            honorInfoModelArrayList.add(honorInfoModel);
        }
            return honorInfoModelArrayList;
    }

    @PostMapping("/add_honorInfo")
    public String addHonorInfo(@RequestBody HonorInfoModel honorInfoModel) {
        HonorInfo honorInfo = new HonorInfo(honorInfoModel);
        honorInfoRepository.save(honorInfo);
        return "{\"message\":\"add honorInfo success\"}";
    }

    @GetMapping("/get_honorInfo_yearBetween")
    public List<HonorInfoModel> getHonorInfoFromYearBetween(Date year1, Date year2) {
        List<HonorInfoModel> honorInfoModelArrayList = new ArrayList<>();
        List<HonorInfo> honorInfoList = (List<HonorInfo>) honorInfoRepository.findHonorInfoByYearBetween(year1, year2);
        for (int i = 0; i < honorInfoList.size() - 1; i++){
            HonorInfoModel honorInfoModel = new HonorInfoModel(honorInfoList.get(i));
            honorInfoModelArrayList.add(honorInfoModel);
        }
        return honorInfoModelArrayList;
    }

    @GetMapping("/get_honorInfo_id")
    public HonorInfoModel getHonorInfoFromId(Long id) {
        return new HonorInfoModel(honorInfoRepository.findHonorInfoById(id));
    }

    @GetMapping("/get_honorInfo_name")
    public List<HonorInfoModel> getHonorInfoFromName(String name) {
        List<HonorInfoModel> honorInfoModelArrayList = new ArrayList<>();
        List<HonorInfo> honorInfoList = (List<HonorInfo>) honorInfoRepository.findHonorInfoByName(name);
        for (int i = 0; i < honorInfoList.size() - 1; i++){
            HonorInfoModel honorInfoModel = new HonorInfoModel(honorInfoList.get(i));
            honorInfoModelArrayList.add(honorInfoModel);
        }
        return honorInfoModelArrayList;
    }

    @GetMapping("/get_honorInfo_kind")
    public List<HonorInfoModel> getHonorInfoFromKind(HonorInfo.Kind kind) {
        List<HonorInfoModel> honorInfoModelArrayList = new ArrayList<>();
        List<HonorInfo> honorInfoList = (List<HonorInfo>) honorInfoRepository.findHonorInfoByKind(kind);
        for (int i = 0; i < honorInfoList.size() - 1; i++){
            HonorInfoModel honorInfoModel = new HonorInfoModel(honorInfoList.get(i));
            honorInfoModelArrayList.add(honorInfoModel);
        }
        return honorInfoModelArrayList;
    }

    @GetMapping("/get_honorInfo_status")
    public List<HonorInfoModel> getHonorInfoFromStdtus(HonorInfo.Status status) {
        List<HonorInfoModel> honorInfoModelArrayList = new ArrayList<>();
        List<HonorInfo> honorInfoList = (List<HonorInfo>) honorInfoRepository.findHonorInfoByStatus(status);
        for (int i = 0; i < honorInfoList.size() - 1; i++){
            HonorInfoModel honorInfoModel = new HonorInfoModel(honorInfoList.get(i));
            honorInfoModelArrayList.add(honorInfoModel);
        }
        return honorInfoModelArrayList;
    }

    @PostMapping("/update_honorInfo")
    public String updateHonorInfo(@RequestBody HonorInfoModel honorInfoModel) {
        HonorInfo honorInfo1 = honorInfoRepository.findHonorInfoById(honorInfoModel.getId());
        {
            if (honorInfo1 == null) {
                return "{\"message\":\"not find honorInfo\"}";
            } else {
                honorInfo1.setHonorInfoFromModel(honorInfoModel);
                honorInfoRepository.save(honorInfo1);
            }
            return "{\"message\":\"update honorInfo success\"}";
        }
    }

}
