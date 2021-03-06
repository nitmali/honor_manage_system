package com.ting.honormanage.controller;

import com.ting.honormanage.entity.HonorInfo;
import com.ting.honormanage.model.HonorInfoModel;
import com.ting.honormanage.repository.HonorInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author nitmali@126.com
 * @date 2018/3/31 14:29
 */
@RestController
public class HonorInfoController {
    @Resource
    private HonorInfoRepository honorInfoRepository;

    @GetMapping("/api/manager/get_honorInfo_all")
    public List<HonorInfoModel> getHonorInfoModelList() {
        List<HonorInfoModel> honorInfoModelArrayList = new ArrayList<>();
        List<HonorInfo> honorInfoList = (List<HonorInfo>) honorInfoRepository.findAll();
        for (HonorInfo aHonorInfoList : honorInfoList) {
            HonorInfoModel honorInfoModel = new HonorInfoModel(aHonorInfoList);
            honorInfoModelArrayList.add(honorInfoModel);
        }
        return honorInfoModelArrayList;
    }

    @GetMapping("/api/student/get_honorInfo_all")
    public List<HonorInfoModel> getHonorInfoModelListOfStudent() {
        List<HonorInfoModel> honorInfoModelArrayList = new ArrayList<>();
        List<HonorInfo> honorInfoList = honorInfoRepository
                .findHonorInfoByStatusNotLike(HonorInfo.Status.INVALID);
        for (HonorInfo aHonorInfoList : honorInfoList) {
            HonorInfoModel honorInfoModel = new HonorInfoModel(aHonorInfoList);
            honorInfoModelArrayList.add(honorInfoModel);
        }
        return honorInfoModelArrayList;
    }

    @PostMapping("/api/student/get_honorInfo_all_test")
    public Map<String, Object> getHonorInfoModelListOfStudent(int draw, int start, int length) {
        PageRequest pageRequest = new PageRequest((start / length), length);
        Page<HonorInfo> page =  honorInfoRepository.findAll(pageRequest);
        List<HonorInfoModel> honorInfoModelArrayList = new ArrayList<>();
        for (int i = 0;i < page.getContent().size();i++) {
            if(page.getContent().get(i).getStatus() != HonorInfo.Status.INVALID)
            {
                HonorInfoModel honorInfoModel = new HonorInfoModel(page.getContent().get(i));
                honorInfoModelArrayList.add(honorInfoModel);
            }
        }
        Map<String, Object> maps = new HashMap<>();
        long totalCount = page.getTotalElements();
        maps.put("draw", draw);
        maps.put("recordsTotal", totalCount);
        maps.put("recordsFiltered", totalCount);
        maps.put("data", honorInfoModelArrayList);
        return maps;
    }

    @PostMapping("/api/manager/add_honorInfo")
    public String addHonorInfo(@RequestBody HonorInfoModel honorInfoModel) {
        HonorInfo honorInfo = new HonorInfo(honorInfoModel);
        honorInfoRepository.save(honorInfo);
        return "{\"message\":\"add honorInfo success\"}";
    }

    @GetMapping("/api/manager_student/get_honorInfo_yearBetween")
    public List<HonorInfoModel> getHonorInfoFromYearBetween(Date year1, Date year2) {
        List<HonorInfoModel> honorInfoModelArrayList = new ArrayList<>();
        List<HonorInfo> honorInfoList = honorInfoRepository.findHonorInfoByYearBetween(year1, year2);
        for (HonorInfo aHonorInfoList : honorInfoList) {
            HonorInfoModel honorInfoModel = new HonorInfoModel(aHonorInfoList);
            honorInfoModelArrayList.add(honorInfoModel);
        }
        return honorInfoModelArrayList;
    }

    @GetMapping("/api/manager_student/get_honorInfo_id")
    public HonorInfoModel getHonorInfoFromId(Long id) {
        return new HonorInfoModel(honorInfoRepository.findHonorInfoById(id));
    }

    @GetMapping("/api/manager_student/get_honorInfo_name")
    public List<HonorInfoModel> getHonorInfoFromName(String name) {
        List<HonorInfoModel> honorInfoModelArrayList = new ArrayList<>();
        List<HonorInfo> honorInfoList = honorInfoRepository.findHonorInfoByName(name);
        for (HonorInfo aHonorInfoList : honorInfoList) {
            HonorInfoModel honorInfoModel = new HonorInfoModel(aHonorInfoList);
            honorInfoModelArrayList.add(honorInfoModel);
        }
        return honorInfoModelArrayList;
    }

    @GetMapping("/api/manager_student/get_honorInfo_kind")
    public List<HonorInfoModel> getHonorInfoFromKind(HonorInfo.Kind kind) {
        List<HonorInfoModel> honorInfoModelArrayList = new ArrayList<>();
        List<HonorInfo> honorInfoList = honorInfoRepository.findHonorInfoByKind(kind);
        for (HonorInfo aHonorInfoList : honorInfoList) {
            HonorInfoModel honorInfoModel = new HonorInfoModel(aHonorInfoList);
            honorInfoModelArrayList.add(honorInfoModel);
        }
        return honorInfoModelArrayList;
    }

    @GetMapping("/api/manager_student/get_honorInfo_status")
    public List<HonorInfoModel> getHonorInfoFromStatus(HonorInfo.Status status) {
        List<HonorInfoModel> honorInfoModelArrayList = new ArrayList<>();
        List<HonorInfo> honorInfoList = honorInfoRepository.findHonorInfoByStatus(status);
        for (HonorInfo aHonorInfoList : honorInfoList) {
            HonorInfoModel honorInfoModel = new HonorInfoModel(aHonorInfoList);
            honorInfoModelArrayList.add(honorInfoModel);
        }
        return honorInfoModelArrayList;
    }

    @PostMapping("/api/manager/update_honorInfo")
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

    @PostMapping("/api/manager/delete_honorInfo")
    public String deleteHonorInfo(@RequestBody HonorInfoModel honorInfoModel) {
        HonorInfo honorInfo1 = honorInfoRepository.findHonorInfoById(honorInfoModel.getId());
        {
            if (honorInfo1 == null) {
                return "{\"message\":\"not find honorInfo\"}";
            } else {
                honorInfoRepository.delete(honorInfo1);
            }
            return "{\"message\":\"delete honorInfo success\"}";
        }
    }

}
