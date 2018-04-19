package com.ting.honormanage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ting.honormanage.entity.CheckRecord;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.sql.Date;

@Component
public class CheckRecordModel {
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

    private Timestamp reportRecordRecordTime;

    private String reportRecordAnnex;

    private String reportRecordStatus;

    private String checkerInfoName;

    private String checkerInfoPhone;

    private String opinion;

    private Timestamp checkTime;

    private String status;

    public CheckRecordModel() {
    }

    public CheckRecordModel(CheckRecord checkRecord) {
        this.id = checkRecord.getId();
        this.honorInfoName = checkRecord.getReportRecord().getHonorInfo().getName();
        this.honorInfoLevel = checkRecord.getReportRecord().getHonorInfo().levelOfHonorInfoModel();
        this.honorInfoKind = checkRecord.getReportRecord().getHonorInfo().kindOfHonorInfoModel();
        this.honorInfoRank = checkRecord.getReportRecord().getHonorInfo().rankOfHonorInfoModel();
        this.honorInfoYear = checkRecord.getReportRecord().getHonorInfo().getYear();
        this.studentInfoNumber = checkRecord.getReportRecord().getStudentInfo().getNumber();
        this.studentInfoName = checkRecord.getReportRecord().getStudentInfo().getName();
        this.studentInfoSex = checkRecord.getReportRecord().getStudentInfo().getSex();
        this.studentInfoCollege = checkRecord.getReportRecord().getStudentInfo().getCollegeInfo().getName();
        this.studentInfoGrade = checkRecord.getReportRecord().getStudentInfo().getGradeInfo().getName();
        this.studentInfoMajor = checkRecord.getReportRecord().getStudentInfo().getMajorInfo().getName();
        this.studentInfoClass = checkRecord.getReportRecord().getStudentInfo().getClassInfo().getName();
        this.reportRecordRecordTime = checkRecord.getReportRecord().getRecordTime();
        this.reportRecordAnnex = checkRecord.getReportRecord().getAnnex();
        this.reportRecordStatus = checkRecord.getReportRecord().statusOfReportRecordModel();
        this.checkerInfoName = checkRecord.getCheckerInfo().getName();
        this.checkerInfoPhone = checkRecord.getCheckerInfo().getPhone();
        this.opinion = checkRecord.getOpinion();
        this.checkTime = checkRecord.getCheckTime();
        this.status = checkRecord.getStatus();
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


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Timestamp getReportRecordRecordTime() {
        return reportRecordRecordTime;
    }

    public void setReportRecordRecordTime(Timestamp reportRecordRecordTime) {
        this.reportRecordRecordTime = reportRecordRecordTime;
    }

    public String getReportRecordAnnex() {
        return reportRecordAnnex;
    }

    public void setReportRecordAnnex(String reportRecordAnnex) {
        this.reportRecordAnnex = reportRecordAnnex;
    }

    public String getReportRecordStatus() {
        return reportRecordStatus;
    }

    public void setReportRecordStatus(String reportRecordStatus) {
        this.reportRecordStatus = reportRecordStatus;
    }

    public String getCheckerInfoName() {
        return checkerInfoName;
    }

    public void setCheckerInfoName(String checkerInfoName) {
        this.checkerInfoName = checkerInfoName;
    }

    public String getCheckerInfoPhone() {
        return checkerInfoPhone;
    }

    public void setCheckerInfoPhone(String checkerInfoPhone) {
        this.checkerInfoPhone = checkerInfoPhone;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
