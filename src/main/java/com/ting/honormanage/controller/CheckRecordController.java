package com.ting.honormanage.controller;

import com.ting.honormanage.entity.CheckRecord;
import com.ting.honormanage.entity.CheckerInfo;
import com.ting.honormanage.entity.ReportRecord;
import com.ting.honormanage.model.CheckRecordModel;
import com.ting.honormanage.model.ReportRecordModel;
import com.ting.honormanage.repository.CheckRecordRepository;
import com.ting.honormanage.repository.CheckerInfoRepository;
import com.ting.honormanage.repository.ReportRecordRepository;
import com.ting.honormanage.service.storage.StorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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

    @Resource
    private StorageService storageService;

    @GetMapping("/api/manager_checker/get_checkRecord_all_bf")
    public List<CheckRecordModel> getCheckRecordModelList() {
        List<CheckRecordModel> checkRecordModelArrayList = new ArrayList<>();
        List<CheckRecord> checkRecordList = (List<CheckRecord>) checkRecordRepository.findAll();
        for (CheckRecord aCheckRecordList : checkRecordList) {
            CheckRecordModel checkRecordModel = new CheckRecordModel(aCheckRecordList);
            checkRecordModelArrayList.add(checkRecordModel);
        }
        return checkRecordModelArrayList;
    }

    @PostMapping("/api/manager_checker/get_checkRecord_all")
    public Map<String, Object> getCheckRecordModelList(int draw, int start, int length) {
        PageRequest pageRequest = new PageRequest((start / length), length);
        Page<CheckRecord> page = checkRecordRepository.findAll(pageRequest);
        List<CheckRecordModel> checkRecordModelArrayList = new ArrayList<>();
        for (int i = 0; i < page.getContent().size(); i++) {
            CheckRecordModel checkRecordModel = new CheckRecordModel(page.getContent().get(i));
            checkRecordModelArrayList.add(checkRecordModel);
        }
        Map<String, Object> maps = new HashMap<>();
        long totalCount = page.getTotalElements();
        maps.put("draw", draw);
        maps.put("recordsTotal", totalCount);
        maps.put("recordsFiltered", totalCount);
        maps.put("data", checkRecordModelArrayList);
        return maps;
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

    @PostMapping("/api/checker/check_reportRecord")
    public String checkReportRecord(@RequestBody ReportRecordModel reportRecordModel
            , HttpServletRequest request) {
        ReportRecord reportRecord1 = reportRecordRepository.findReportRecordById(reportRecordModel.getId());
        if (reportRecord1 == null) {
            return "{\"message\":\"reportRecord not find\"}";
        } else {
            HttpSession session = request.getSession();
            CheckerInfo checkerInfo = checkerInfoRepository
                    .findCheckerInfoByUsername((String) session.getAttribute("userName"));
            if (reportRecordModel.getStatus().equals("PASS")) {
                if (checkerInfo.getAuthority() == CheckerInfo.Authority.FIRST_LEVEL) {
                    reportRecord1.setStatus(ReportRecord.Status.FIRST_REVIEW);
                } else if (checkerInfo.getAuthority() == CheckerInfo.Authority.SECOND_LEVEL) {
                    reportRecord1.setStatus(ReportRecord.Status.ALREADY_REVIEW);
                }

            } else if (reportRecordModel.getStatus().equals("NOT_PASS")) {
                reportRecord1.setStatus(ReportRecord.Status.NOT_PASS);
            }
            CheckRecord checkRecord = new CheckRecord(reportRecord1, checkerInfo
                    , reportRecordModel.getStatus());
            checkRecord.setOpinion(reportRecordModel.getOpinion());
            reportRecordRepository.save(reportRecord1);
            checkRecordRepository.save(checkRecord);
            return "{\"message\":\"check reportRecord success\"}";
        }
    }

    @GetMapping("/api/checker_manager/get_annex")
    public ResponseEntity<org.springframework.core.io.Resource> getCarImage(Long id) {

        try {
            String annexName = reportRecordRepository.findOne(id).getAnnex();
            org.springframework.core.io.Resource annex = storageService.loadAsResource(annexName);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + annexName + "\"").body(annex);
        } catch (Exception e) {
            return null;
        }

    }
}
