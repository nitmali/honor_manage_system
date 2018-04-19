package com.ting.honormanage.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author nitmali@126.com
 * @date 2018/3/14 16:35
 */
@Entity
public class ReportRecord {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private HonorInfo honorInfo;

    @ManyToOne
    private StudentInfo studentInfo;

    private Timestamp recordTime;
    //附件
    @Column
    private String annex;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public ReportRecord() {
    }

    public ReportRecord(HonorInfo honorInfo, StudentInfo studentInfo) {
        this.honorInfo = honorInfo;
        this.studentInfo = studentInfo;
        this.status = Status.WAITING_REVIEW;
        this.recordTime = new Timestamp(System.currentTimeMillis());
    }

    public enum Status {
        //待提交
        WAITING_SUBMISSION,
        //待审核
        WAITING_REVIEW,
        //一级审核
        FIRST_REVIEW,
        //二级审核
        ALREADY_REVIEW,
        //未通过
        NOT_PASS,
        //失效
        INVALID
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HonorInfo getHonorInfo() {
        return honorInfo;
    }

    public void setHonorInfo(HonorInfo honorInfo) {
        this.honorInfo = honorInfo;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String statusOfReportRecordModel() {
        String statusWAITING_SUBMISSION = "未提交";
        String statusWAITING_REVIEW = "未审核";
        String statusFIRST_REVIEW = "审核中";
        String statusALREADY_REVIEW = "通过";
        String statusNOT_PASS = "不通过";
        String statusINVALID = "失效";
        if (this.status == Status.WAITING_SUBMISSION) {
            return statusWAITING_SUBMISSION;
        } else if (this.status == Status.WAITING_REVIEW) {
            return statusWAITING_REVIEW;
        } else if (this.status == Status.FIRST_REVIEW) {
            return statusFIRST_REVIEW;
        } else if (this.status == Status.ALREADY_REVIEW) {
            return statusALREADY_REVIEW;
        } else if (this.status == Status.NOT_PASS) {
            return statusNOT_PASS;
        } else if (this.status == Status.INVALID) {
            return statusINVALID;
        } else {
            return null;
        }
    }

    public void setStatus(String status) {
        String statusWAITING_SUBMISSION = "未提交";
        String statusWAITING_REVIEW = "未审核";
        String statusFIRST_REVIEW = "审核中";
        String statusALREADY_REVIEW = "通过";
        String statusNOT_PASS = "不通过";
        String statusINVALID = "失效";
        if (statusWAITING_SUBMISSION.equals(status)) {
            this.status = Status.WAITING_SUBMISSION;
        } else if (statusWAITING_REVIEW.equals(status)) {
            this.status = Status.WAITING_REVIEW;
        } else if (statusFIRST_REVIEW.equals(status)) {
            this.status = Status.FIRST_REVIEW;
        } else if (statusALREADY_REVIEW.equals(status)) {
            this.status = Status.ALREADY_REVIEW;
        } else if (statusNOT_PASS.equals(status)) {
            this.status = Status.NOT_PASS;
        } else if (statusINVALID.equals(status)) {
            this.status = Status.INVALID;
        } else {
            this.status = null;
        }
    }
}
