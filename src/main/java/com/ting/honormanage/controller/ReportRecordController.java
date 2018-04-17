package com.ting.honormanage.controller;

import com.ting.honormanage.entity.*;
import com.ting.honormanage.model.HonorInfoModel;
import com.ting.honormanage.model.ReportRecordModel;
import com.ting.honormanage.repository.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nitmali
 */
@RestController
public class ReportRecordController {
    @Resource
    private ReportRecordRepository reportRecordRepository;

    @Resource
    private StudentInfoRepository studentInfoRepository;

    @Resource
    private HonorInfoRepository honorInfoRepository;

    @Resource
    private CheckerInfoRepository checkerInfoRepository;

    @Resource
    private CheckRecordRepository checkRecordRepository;

    @GetMapping("/api/checker_manager/get_reportRecord_all")
    public List<ReportRecordModel> getReportRecordList() {
        List<ReportRecordModel> reportRecordModelArrayList = new ArrayList<>();
        List<ReportRecord> reportRecordList = (List<ReportRecord>) reportRecordRepository.findAll();
        for (ReportRecord aReportRecordList : reportRecordList) {
            ReportRecordModel reportRecordModel = new ReportRecordModel(aReportRecordList);
            reportRecordModelArrayList.add(reportRecordModel);
        }
        return reportRecordModelArrayList;
    }

    @GetMapping("/api/checker/get_reportRecord_all_of_check")
    public List<ReportRecordModel> getReportRecordOfcheckList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CheckerInfo checkerInfo = checkerInfoRepository
                .findCheckerInfoByUsername((String) session.getAttribute("userName"));
        List<ReportRecord> reportRecordList;
        if (checkerInfo.getAuthority() == CheckerInfo.Authority.FIRST_LEVEL) {
            reportRecordList = reportRecordRepository
                    .findReportRecordByStatus(ReportRecord.Status.WAITING_REVIEW);
        } else {
            reportRecordList = reportRecordRepository
                    .findReportRecordByStatus(ReportRecord.Status.FIRST_REVIEW);
        }
        List<ReportRecordModel> reportRecordModelArrayList = new ArrayList<>();

        for (ReportRecord aReportRecordList : reportRecordList) {
            ReportRecordModel reportRecordModel = new ReportRecordModel(aReportRecordList);

            reportRecordModelArrayList.add(reportRecordModel);

        }
        return reportRecordModelArrayList;
    }

    @GetMapping("/api/manager_checker/get_reportRecord_id")
    public ReportRecordModel getReportRecordFromId(Long id) {
        return new ReportRecordModel(reportRecordRepository.findReportRecordById(id));
    }

    @GetMapping("/api/manager_checker/get_reportRecord_status")
    public List<ReportRecordModel> getReportRecordListFromStatus(ReportRecord.Status status) {
        List<ReportRecordModel> reportRecordModelArrayList = new ArrayList<>();
        List<ReportRecord> reportRecordList = reportRecordRepository.findReportRecordByStatus(status);
        for (ReportRecord aReportRecordList : reportRecordList) {
            ReportRecordModel reportRecordModel = new ReportRecordModel(aReportRecordList);
            reportRecordModelArrayList.add(reportRecordModel);
        }
        return reportRecordModelArrayList;
    }

    @GetMapping("/api/manager_checker/get_reportRecord_studentInfo")
    public List<ReportRecordModel> getReportRecordListFromStudentInfo(Long studentId) {
        StudentInfo studentInfo = studentInfoRepository.findStudentInfoById(studentId);
        List<ReportRecordModel> reportRecordModelArrayList = new ArrayList<>();
        List<ReportRecord> reportRecordList = reportRecordRepository.findReportRecordByStudentInfo(studentInfo);
        for (ReportRecord aReportRecordList : reportRecordList) {
            ReportRecordModel reportRecordModel = new ReportRecordModel(aReportRecordList);
            reportRecordModelArrayList.add(reportRecordModel);
        }
        return reportRecordModelArrayList;
    }


    @GetMapping("/api/manager_checker/get_reportRecord_honorInfo")
    public List<ReportRecordModel> getReportRecordListFromHonorInfo(Long honorId) {
        HonorInfo honorInfo = honorInfoRepository.findHonorInfoById(honorId);
        List<ReportRecordModel> reportRecordModelArrayList = new ArrayList<>();
        List<ReportRecord> reportRecordList = reportRecordRepository.findReportRecordByHonorInfo(honorInfo);
        for (ReportRecord aReportRecordList : reportRecordList) {
            ReportRecordModel reportRecordModel = new ReportRecordModel(aReportRecordList);
            reportRecordModelArrayList.add(reportRecordModel);
        }
        return reportRecordModelArrayList;
    }

    @GetMapping("/api/manager_checker/get_reportRecord_studentInfo_honorInfo")
    public List<ReportRecordModel> getReportRecordListFromStudentInfoAndHonorInfo(Long studentId, Long honorId) {
        StudentInfo studentInfo = studentInfoRepository.findStudentInfoById(studentId);
        HonorInfo honorInfo = honorInfoRepository.findHonorInfoById(honorId);
        List<ReportRecordModel> reportRecordModelArrayList = new ArrayList<>();
        List<ReportRecord> reportRecordList = reportRecordRepository
                .findReportRecordByStudentInfoAndHonorInfo(studentInfo, honorInfo);
        for (ReportRecord aReportRecordList : reportRecordList) {
            ReportRecordModel reportRecordModel = new ReportRecordModel(aReportRecordList);
            reportRecordModelArrayList.add(reportRecordModel);
        }
        return reportRecordModelArrayList;
    }

    @PostMapping("/api/student/add_reportRecord")
    public String addReportRecord(@RequestBody HonorInfoModel honorInfoModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        StudentInfo studentInfo = studentInfoRepository
                .findStudentInfoByNumber((String) session.getAttribute("userName"));
        HonorInfo honorInfo = new HonorInfo(honorInfoModel);
        ReportRecord reportRecord = new ReportRecord(honorInfo, studentInfo);
        reportRecordRepository.save(reportRecord);
        return "{\"message\":\"add reportRecord success\"}";
    }

    @PostMapping("/api/manager_checker/update_reportRecord")
    public String updateReportRecord(@RequestBody ReportRecordModel reportRecordModel) {
        ReportRecord reportRecord1 = reportRecordRepository.findReportRecordById(reportRecordModel.getId());
        if (reportRecord1 == null) {
            return "{\"message\":\"reportRecord not find\"}";
        } else {
            reportRecord1.setStatus(reportRecordModel.getStatus());
            reportRecordRepository.save(reportRecord1);
        }
        return "{\"message\":\"update reportRecord success\"}";
    }

    @GetMapping("/api/manager/count_reportRecord_status")
    @ResponseBody
    public Map<String, Long> countReportRecordFromStatus() {
        Map<String, Long> map = new HashMap<>();
        map.put("WAITING_REVIEW", reportRecordRepository
                .countReportRecordByStatus(ReportRecord.Status.WAITING_REVIEW));
        map.put("FIRST_REVIEW", reportRecordRepository
                .countReportRecordByStatus(ReportRecord.Status.FIRST_REVIEW));
        map.put("ALREADY_REVIEW", reportRecordRepository
                .countReportRecordByStatus(ReportRecord.Status.ALREADY_REVIEW));
        return map;
    }

    @GetMapping("/api/manager/count_reportRecord_Kind_level")
    public List<Map<String,Long>> countReportRecordFrom(){

        List<Map<String,Long>> mapList = new ArrayList<>();
        Map<String,Long> map1 = new HashMap<>();
        map1.put("ACADEMIC_RESEARCH",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.NATIONAL,HonorInfo.Kind.ACADEMIC_RESEARCH));
        map1.put("DISCIPLINE_COMPETITION",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.NATIONAL,HonorInfo.Kind.DISCIPLINE_COMPETITION));
        map1.put("INNOVATION_ENTREPRENEURSHIP",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.NATIONAL,HonorInfo.Kind.INNOVATION_ENTREPRENEURSHIP));
        map1.put("EXAMINATIONS",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.NATIONAL,HonorInfo.Kind.EXAMINATIONS));

        Map<String,Long> map2 = new HashMap<>();
        map2.put("ACADEMIC_RESEARCH",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.PROVINCIAL,HonorInfo.Kind.ACADEMIC_RESEARCH));
        map2.put("DISCIPLINE_COMPETITION",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.PROVINCIAL,HonorInfo.Kind.DISCIPLINE_COMPETITION));
        map2.put("INNOVATION_ENTREPRENEURSHIP",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.PROVINCIAL,HonorInfo.Kind.INNOVATION_ENTREPRENEURSHIP));
        map2.put("EXAMINATIONS",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.PROVINCIAL,HonorInfo.Kind.EXAMINATIONS));

        Map<String,Long> map3 = new HashMap<>();
        map3.put("ACADEMIC_RESEARCH",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.MUNICIPAL,HonorInfo.Kind.ACADEMIC_RESEARCH));
        map3.put("DISCIPLINE_COMPETITION",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.MUNICIPAL,HonorInfo.Kind.DISCIPLINE_COMPETITION));
        map3.put("INNOVATION_ENTREPRENEURSHIP",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.MUNICIPAL,HonorInfo.Kind.INNOVATION_ENTREPRENEURSHIP));
        map3.put("EXAMINATIONS",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.MUNICIPAL,HonorInfo.Kind.EXAMINATIONS));

        Map<String,Long> map4 = new HashMap<>();
        map4.put("ACADEMIC_RESEARCH",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.SCHOOL,HonorInfo.Kind.ACADEMIC_RESEARCH));
        map4.put("DISCIPLINE_COMPETITION",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.SCHOOL,HonorInfo.Kind.DISCIPLINE_COMPETITION));
        map4.put("INNOVATION_ENTREPRENEURSHIP",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.SCHOOL,HonorInfo.Kind.INNOVATION_ENTREPRENEURSHIP));
        map4.put("EXAMINATIONS",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.SCHOOL,HonorInfo.Kind.EXAMINATIONS));

        Map<String,Long> map5 = new HashMap<>();
        map5.put("ACADEMIC_RESEARCH",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.COLLEGE,HonorInfo.Kind.ACADEMIC_RESEARCH));
        map5.put("DISCIPLINE_COMPETITION",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.COLLEGE,HonorInfo.Kind.DISCIPLINE_COMPETITION));
        map5.put("INNOVATION_ENTREPRENEURSHIP",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.COLLEGE,HonorInfo.Kind.INNOVATION_ENTREPRENEURSHIP));
        map5.put("EXAMINATIONS",reportRecordRepository
                .countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status.ALREADY_REVIEW,
                        HonorInfo.Level.COLLEGE,HonorInfo.Kind.EXAMINATIONS));

        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);
        mapList.add(map5);
        return mapList;
    }

}
