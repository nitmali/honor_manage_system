package com.ting.honormanage.controller;

import com.ting.honormanage.entity.CheckRecord;
import com.ting.honormanage.entity.CheckerInfo;
import com.ting.honormanage.entity.ReportRecord;
import com.ting.honormanage.model.CheckRecordModel;
import com.ting.honormanage.model.ReportRecordModel;
import com.ting.honormanage.repository.CheckRecordRepository;
import com.ting.honormanage.repository.CheckerInfoRepository;
import com.ting.honormanage.repository.ReportRecordRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author nitmali
 */
@RestController
public class CheckRecordController {
    @Resource
    private CheckRecordRepository checkRecordRepository;

    @Resource
    private ReportRecordRepository reportRecordRepository;

    @Resource
    private CheckerInfoRepository checkerInfoRepository;

    @GetMapping("/api/manager_checker/get_checkRecord_all")
    public List<CheckRecordModel> getCheckRecordModelList() {
        List<CheckRecordModel> checkRecordModelArrayList = new ArrayList<>();
        List<CheckRecord> checkRecordList = (List<CheckRecord>) checkRecordRepository.findAll();
        for (CheckRecord aCheckRecordList : checkRecordList) {
            CheckRecordModel checkRecordModel = new CheckRecordModel(aCheckRecordList);
            checkRecordModelArrayList.add(checkRecordModel);
        }
        return checkRecordModelArrayList;
    }

    @GetMapping("/api/manager_checker/get_checkRecord_id")
    public CheckRecordModel getCheckRecordFromId(Long id) {
        return new CheckRecordModel(checkRecordRepository.findCheckRecordById(id));
    }

    @GetMapping("/api/manager_checker/get_checkRecord_reportRecord")
    public List<CheckRecordModel> getCheckRecordListFromReportRecord(Long reportRecordId) {
        ReportRecord reportRecord = reportRecordRepository.findReportRecordById(reportRecordId);
        List<CheckRecordModel> checkRecordModelArrayList = new ArrayList<>();
        List<CheckRecord> checkRecordList = checkRecordRepository.findCheckRecordByReportRecord(reportRecord);
        for (CheckRecord aCheckRecordList : checkRecordList) {
            CheckRecordModel checkRecordModel = new CheckRecordModel(aCheckRecordList);
            checkRecordModelArrayList.add(checkRecordModel);
        }
        return checkRecordModelArrayList;
    }

    @GetMapping("/api/manager_checker/get_checkRecord_checkerInfo")
    public List<CheckRecordModel> getCheckRecordListFromCheckerInfo(Long checkerInfoId) {
        CheckerInfo checkerInfo = checkerInfoRepository.findCheckerInfoById(checkerInfoId);
        List<CheckRecordModel> checkRecordModelArrayList = new ArrayList<>();
        List<CheckRecord> checkRecordList = checkRecordRepository.findCheckRecordByCheckerInfo(checkerInfo);
        for (CheckRecord aCheckRecordList : checkRecordList) {
            CheckRecordModel checkRecordModel = new CheckRecordModel(aCheckRecordList);
            checkRecordModelArrayList.add(checkRecordModel);
        }
        return checkRecordModelArrayList;
    }

    @GetMapping("/api/manager_checker/get_checkRecord_reportRecord_checkerInfo")
    public List<CheckRecordModel> getCheckRecordListFromReportRecordAndCheckerInfo(Long reportRecordId, Long checkerInfoId) {
        ReportRecord reportRecord = reportRecordRepository.findReportRecordById(reportRecordId);
        CheckerInfo checkerInfo = checkerInfoRepository.findCheckerInfoById(checkerInfoId);
        List<CheckRecordModel> checkRecordModelArrayList = new ArrayList<>();
        List<CheckRecord> checkRecordList = checkRecordRepository.findCheckRecordByReportRecordAndCheckerInfo(reportRecord, checkerInfo);
        for (CheckRecord aCheckRecordList : checkRecordList) {
            CheckRecordModel checkRecordModel = new CheckRecordModel(aCheckRecordList);
            checkRecordModelArrayList.add(checkRecordModel);
        }
        return checkRecordModelArrayList;
    }

    @GetMapping("/api/manager_checker/get_checkRecord_checkTime")
    public CheckRecordModel getCheckRecordFromCheckTime(Date checkTime) {
        return new CheckRecordModel(checkRecordRepository.findCheckRecordByCheckTime(checkTime));
    }

    @PostMapping("/api/manager_checker/add_checkRecord")
    public String addCheckRecord(@RequestBody ReportRecordModel reportRecordModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        CheckerInfo checkerInfo = checkerInfoRepository
                .findCheckerInfoByUsername((String) session.getAttribute("userName"));
        ReportRecord reportRecord = reportRecordRepository.findReportRecordById(reportRecordModel.getId());
        CheckRecord checkRecord = new CheckRecord(reportRecord, checkerInfo);
        checkRecordRepository.save(checkRecord);
        return "{\"message\":\"add checkRecord success\"}";
    }
}
