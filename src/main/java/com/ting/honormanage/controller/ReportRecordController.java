package com.ting.honormanage.controller;

import com.ting.honormanage.entity.HonorInfo;
import com.ting.honormanage.entity.ReportRecord;
import com.ting.honormanage.entity.StudentInfo;
import com.ting.honormanage.repository.HonorInfoRepository;
import com.ting.honormanage.repository.ReportRecordRepository;
import com.ting.honormanage.repository.StudentInfoRepository;
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
public class ReportRecordController {
    @Resource
    private ReportRecordRepository reportRecordRepository;

    @Resource
    private StudentInfoRepository studentInfoRepository;

    @Resource
    private HonorInfoRepository honorInfoRepository;

    @GetMapping("/api/manager_checker/get_reportRecord_all")
    public List<ReportRecord> getReportRecordList() {
        return (List<ReportRecord>) reportRecordRepository.findAll();
    }

    @GetMapping("/api/manager_checker/get_reportRecord_id")
    public ReportRecord getReportRecordFromId(Long id) {
        return reportRecordRepository.findReportRecordById(id);
    }

    @GetMapping("/api/manager_checker/get_reportRecord_status")
    public List<ReportRecord> getReportRecordListFromStatus(ReportRecord.Status status)
    {
        return reportRecordRepository.findReportRecordByStatus(status);
    }

    @GetMapping("/api/manager_checker/get_reportRecord_studentInfo")
    public List<ReportRecord> getReportRecordListFromStudentInfo(Long studentId)
    {
        StudentInfo studentInfo = studentInfoRepository.findStudentInfoById(studentId);
        return reportRecordRepository.findReportRecordByStudentInfo(studentInfo);
    }


    @GetMapping("/api/manager_checker/get_reportRecord_honorInfo")
    public List<ReportRecord> getReportRecordListFromHonorInfo(Long honorId)
    {
        HonorInfo honorInfo = honorInfoRepository.findHonorInfoById(honorId);
        return reportRecordRepository.findReportRecordByHonorInfo(honorInfo);
    }

    @GetMapping("/api/manager_checker/get_reportRecord_studentInfo_honorInfo")
    public List<ReportRecord> getReportRecordListFromStudentInfoAndHonorInfo(Long studentId,Long honorId)
    {
        StudentInfo studentInfo = studentInfoRepository.findStudentInfoById(studentId);
        HonorInfo honorInfo = honorInfoRepository.findHonorInfoById(honorId);
        return reportRecordRepository.findReportRecordByStudentInfoAndAndHonorInfo(studentInfo,honorInfo);
    }


    @PostMapping("/api/manager_checker/add_reportRecord")
    public String addReportRecord(@RequestBody ReportRecord reportRecord)
    {
        reportRecordRepository.save(reportRecord);
        return "{\"message\":\"add reportRecord success\"}";
    }

    @PostMapping("/api/manager_checker/update_reportRecord")
    public String updateReportRecord(@RequestBody ReportRecord reportRecord)
    {
        ReportRecord reportRecord1 = reportRecordRepository.findReportRecordById(reportRecord.getId());
        if(reportRecord1 == null)
        {
            return "{\"message\":\"reportRecord not find\"}";
        }else {
            reportRecord1.setReportRecord(reportRecord);
            reportRecordRepository.save(reportRecord1);
        }
        return "{\"message\":\"update reportRecord success\"}";
    }
}
