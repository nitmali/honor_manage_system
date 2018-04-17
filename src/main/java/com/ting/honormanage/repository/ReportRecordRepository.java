package com.ting.honormanage.repository;

import com.ting.honormanage.entity.HonorInfo;
import com.ting.honormanage.entity.ReportRecord;
import com.ting.honormanage.entity.StudentInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/3/21 13:14
 */
public interface ReportRecordRepository extends CrudRepository<ReportRecord, Long> {

    /**
     * 根据ID查询申报记录
     */
    ReportRecord findReportRecordById(Long id);

    List<ReportRecord> findReportRecordByStatus(ReportRecord.Status status);

    List<ReportRecord> findReportRecordByStudentInfo(StudentInfo studentInfo);

    List<ReportRecord> findReportRecordByHonorInfo(HonorInfo honorInfo);

    List<ReportRecord> findReportRecordByStudentInfoAndHonorInfo(StudentInfo studentInfo, HonorInfo honorInfo);

    Long countReportRecordByStatus(ReportRecord.Status status);

    Long countReportRecordByStatusAndHonorInfo_LevelAndHonorInfo_Kind(ReportRecord.Status status
            , HonorInfo.Level level, HonorInfo.Kind kind);

}
