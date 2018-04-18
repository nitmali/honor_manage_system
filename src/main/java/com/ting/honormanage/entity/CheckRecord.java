package com.ting.honormanage.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author nitmali@126.com
 * @date 2018/3/14 15:18
 */
@Entity
public class CheckRecord {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ReportRecord reportRecord;

    @ManyToOne
    private CheckerInfo checkerInfo;

    @Column
    @Lob
    private String opinion;

    @Column(nullable = false)
    private Timestamp checkTime;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public CheckRecord() {
    }

    public CheckRecord(ReportRecord reportRecord, CheckerInfo checkerInfo, String status) {
        this.reportRecord = reportRecord;
        this.checkerInfo = checkerInfo;
        this.checkTime = new Timestamp(System.currentTimeMillis());
        if ("PASS".equals(status)) {
            this.status = Status.PASS;
        } else if ("NOT_PASS".equals(status)) {
            this.status = Status.NOT_PASS;
        } else {
            this.status = null;
        }
    }

    public enum Status {
        //未通过
        NOT_PASS,
        //通过
        PASS
    }

    public String getStatus() {
        if (this.status == Status.NOT_PASS) {
            return "不通过";
        } else if (this.status == Status.PASS) {
            return "通过";
        } else {
            return null;
        }
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReportRecord getReportRecord() {
        return reportRecord;
    }

    public void setReportRecord(ReportRecord reportRecord) {
        this.reportRecord = reportRecord;
    }

    public CheckerInfo getCheckerInfo() {
        return checkerInfo;
    }

    public void setCheckerInfo(CheckerInfo checkerInfo) {
        this.checkerInfo = checkerInfo;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }
}
