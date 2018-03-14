package com.ting.honormanage.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HonorInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column
    @Enumerated(EnumType.STRING)
    private Kind kind;

    @Column
    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column(nullable = false)
    private Date year;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Level {
        //院级
        COLLEGE,
        //校级
        SCHOOL,
        //市级
        MUNICIPAL,
        //省级
        PROVINCIAL,
        //国家级
        NATIONAL
    }

    public enum Kind {
        //学术研究
        ACADEMIC_RESEARCH,
        //学科竞赛
        DISCIPLINE_COMPETITION,
        //创新创业
        INNOVATION_ENTREPRENEURSHIP,
        //考级考证
        EXAMINATIONS
    }

    public enum Rank {
        //一等奖
        FIRST,
        //二等奖
        SECOND,
        //三等奖
        THIRD,
        //其他
        OTHER
    }

    public enum Status{
        //有效
        EFFECTIVE,
        //失效
        INVALID
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
