package com.ting.honormanage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author nitmali@126.com
 * @date 2018/3/14 16:21
 */
@Entity
public class StudentInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 10,nullable = false,unique = true)
    private String number;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String sex;

    @ManyToOne
    private CollegeInfo collegeInfo;

    @ManyToOne
    private GradeInfo gradeInfo;

    @ManyToOne
    private MajorInfo majorInfo;

    @ManyToOne
    private ClassInfo classInfo;

    public StudentInfo() {
    }

    public StudentInfo(StudentInfo studentInfo) {
        this.number = studentInfo.number;
        this.password = studentInfo.password;
        this.name = studentInfo.name;
        this.sex = studentInfo.sex;
        this.collegeInfo = studentInfo.collegeInfo;
        this.gradeInfo = studentInfo.gradeInfo;
        this.majorInfo = studentInfo.majorInfo;
        this.classInfo = studentInfo.classInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.number = studentInfo.number;
        this.password = studentInfo.password;
        this.name = studentInfo.name;
        this.sex = studentInfo.sex;
        this.collegeInfo = studentInfo.collegeInfo;
        this.gradeInfo = studentInfo.gradeInfo;
        this.majorInfo = studentInfo.majorInfo;
        this.classInfo = studentInfo.classInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public CollegeInfo getCollegeInfo() {
        return collegeInfo;
    }

    public void setCollegeInfo(CollegeInfo collegeInfo) {
        this.collegeInfo = collegeInfo;
    }

    public GradeInfo getGradeInfo() {
        return gradeInfo;
    }

    public void setGradeInfo(GradeInfo gradeInfo) {
        this.gradeInfo = gradeInfo;
    }

    public MajorInfo getMajorInfo() {
        return majorInfo;
    }

    public void setMajorInfo(MajorInfo majorInfo) {
        this.majorInfo = majorInfo;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }
}
