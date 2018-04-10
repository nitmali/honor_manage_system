package com.ting.honormanage.model;

import com.ting.honormanage.entity.HonorInfo;
import com.ting.honormanage.entity.ReportRecord;
import com.ting.honormanage.entity.StudentInfo;

import java.util.Date;

public class ReportRecordModel {

    private Long id;

    private String honorInfoName;

    private String honorInfoLevel;

    private String honorInfoKind;

    private String honorInfoRank;

    private Date honorInfoYear;

    private String studentInfoNumber;

    private String studentInfoName;

    private String studentInfoSex;

    private String studentInfoCollege;

    private String studentInfoGrade;

    private String studentInfoMajor;

    private String studentInfoClass;

    private Date recordTime;

    private String annex;

    private String status;

    public ReportRecordModel() {
    }

    public ReportRecordModel(ReportRecord reportRecord) {
        this.id = reportRecord.getId();
        this.honorInfoName = reportRecord.getHonorInfo().getName();
        this.honorInfoLevel = reportRecord.getHonorInfo().levelOfHonorInfoModel();
        this.honorInfoKind = reportRecord.getHonorInfo().kindOfHonorInfoModel();
        this.honorInfoRank = reportRecord.getHonorInfo().rankOfHonorInfoModel();
        this.honorInfoYear = reportRecord.getHonorInfo().getYear();
        this.studentInfoNumber = reportRecord.getStudentInfo().getNumber();
        this.studentInfoName = reportRecord.getStudentInfo().getName();
        this.studentInfoSex = reportRecord.getStudentInfo().getSex();
        this.studentInfoCollege = reportRecord.getStudentInfo().getCollegeInfo().getName();
        this.studentInfoGrade = reportRecord.getStudentInfo().getGradeInfo().getName();
        this.studentInfoMajor = reportRecord.getStudentInfo().getMajorInfo().getName();
        this.studentInfoClass = reportRecord.getStudentInfo().getClassInfo().getName();
        this.recordTime = reportRecord.getRecordTime();
        this.annex = reportRecord.getAnnex();
        this.status = reportRecord.statusOfReportRecordModel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHonorInfoName() {
        return honorInfoName;
    }

    public void setHonorInfoName(String honorInfoName) {
        this.honorInfoName = honorInfoName;
    }

    public String getHonorInfoLevel() {
        return honorInfoLevel;
    }

    public void setHonorInfoLevel(String honorInfoLevel) {
        this.honorInfoLevel = honorInfoLevel;
    }

    public String getHonorInfoKind() {
        return honorInfoKind;
    }

    public void setHonorInfoKind(String honorInfoKind) {
        this.honorInfoKind = honorInfoKind;
    }

    public String getHonorInfoRank() {
        return honorInfoRank;
    }

    public void setHonorInfoRank(String honorInfoRank) {
        this.honorInfoRank = honorInfoRank;
    }

    public Date getHonorInfoYear() {
        return honorInfoYear;
    }

    public void setHonorInfoYear(Date honorInfoYear) {
        this.honorInfoYear = honorInfoYear;
    }

    public String getStudentInfoNumber() {
        return studentInfoNumber;
    }

    public void setStudentInfoNumber(String studentInfoNumber) {
        this.studentInfoNumber = studentInfoNumber;
    }

    public String getStudentInfoName() {
        return studentInfoName;
    }

    public void setStudentInfoName(String studentInfoName) {
        this.studentInfoName = studentInfoName;
    }

    public String getStudentInfoSex() {
        return studentInfoSex;
    }

    public void setStudentInfoSex(String studentInfoSex) {
        this.studentInfoSex = studentInfoSex;
    }

    public String getStudentInfoCollege() {
        return studentInfoCollege;
    }

    public void setStudentInfoCollege(String studentInfoCollege) {
        this.studentInfoCollege = studentInfoCollege;
    }

    public String getStudentInfoGrade() {
        return studentInfoGrade;
    }

    public void setStudentInfoGrade(String studentInfoGrade) {
        this.studentInfoGrade = studentInfoGrade;
    }

    public String getStudentInfoMajor() {
        return studentInfoMajor;
    }

    public void setStudentInfoMajor(String studentInfoMajor) {
        this.studentInfoMajor = studentInfoMajor;
    }

    public String getStudentInfoClass() {
        return studentInfoClass;
    }

    public void setStudentInfoClass(String studentInfoClass) {
        this.studentInfoClass = studentInfoClass;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
