package com.ting.honormanage.entity;

import javax.persistence.*;
import java.util.Date;

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

    private Date recordTime;

    //附件
    @Column
    private String annex;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status{
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

    public void setReportRecord(ReportRecord reportRecord)
    {
        this.id = reportRecord.id;
        this.honorInfo = reportRecord.honorInfo;
        this.studentInfo = reportRecord.studentInfo;
        this.recordTime = reportRecord.recordTime;
        this.annex = reportRecord.annex;
        this.status = reportRecord.status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
