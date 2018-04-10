package com.ting.honormanage.repository;

import com.ting.honormanage.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/3/14 23:21
 */
@Repository
public interface StudentInfoRepository extends CrudRepository<StudentInfo,Long> {

    /**
     * 根据学生学号查询学生信息
     */
    StudentInfo findStudentInfoByNumber(String number);

    /**
     * 根据学生编号查询学生信息
     */
    StudentInfo findStudentInfoById(Long id);

    /**
     * 根据学生姓名查询学生信息
     */
    List<StudentInfo> findStudentInfoByName(String name);


    List<StudentInfo> findStudentInfoByClassInfo(ClassInfo classInfo);

    List<StudentInfo> findStudentInfoByCollegeInfo(CollegeInfo collegeInfo);

    List<StudentInfo> findStudentInfoByGradeInfo(GradeInfo gradeInfo);

    List<StudentInfo> findStudentInfoByMajorInfo(MajorInfo majorInfo);
}
