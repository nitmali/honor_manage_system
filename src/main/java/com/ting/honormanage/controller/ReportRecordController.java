package com.ting.honormanage.controller;

import com.ting.honormanage.entity.HonorInfo;
import com.ting.honormanage.entity.ReportRecord;
import com.ting.honormanage.entity.StudentInfo;
import com.ting.honormanage.repository.HonorInfoRepository;
import com.ting.honormanage.repository.ReportRecordRepository;
import com.ting.honormanage.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
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
    private StudentRepository studentRepository;

    @Resource
    private HonorInfoRepository honorInfoRepository;

    @GetMapping("/get_reportRecord_all")
    public List<ReportRecord> reportRecordList() {
        return (List<ReportRecord>) reportRecordRepository.findAll();
    }

    @GetMapping("/get_reportRecord_id")
    public ReportRecord getReportRecordFromId(Long id) {
        return reportRecordRepository.findReportRecordById(id);
    }

    @GetMapping("/get_reportRecord_status")
    public List<ReportRecord> reportRecordListOfStatus(ReportRecord.Status status)
    {
        return reportRecordRepository.findReportRecordByStatus(status);
    }

        @GetMapping("/get_reportRecord_studentInfo")
    public List<ReportRecord> reportRecordListOfStudentInfo(Long studentId)
    {
        StudentInfo studentInfo = studentRepository.findStudentInfoById(studentId);
        return reportRecordRepository.findReportRecordByStudentInfo(studentInfo);
    }


    @GetMapping("/get_reportRecord_honorInfo")
    public List<ReportRecord> reportRecordListOfHonorInfo(Long honorId)
    {
        HonorInfo honorInfo = honorInfoRepository.findHonorInfoById(honorId);
        return reportRecordRepository.findReportRecordByHonorInfo(honorInfo);
    }

    @GetMapping("/get_reportRecord_studentInfo_honorInfo")
    public List<ReportRecord> reportRecordListOfStudentInfoAddHonorInfo(Long studentId,Long honorId)
    {
        StudentInfo studentInfo = studentRepository.findStudentInfoById(studentId);
        HonorInfo honorInfo = honorInfoRepository.findHonorInfoById(honorId);
        return reportRecordRepository.findReportRecordByStudentInfoAndAndHonorInfo(studentInfo,honorInfo);
    }


}
