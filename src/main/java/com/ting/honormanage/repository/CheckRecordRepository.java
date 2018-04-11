package com.ting.honormanage.repository;

import com.ting.honormanage.entity.HonorInfo;
import com.ting.honormanage.entity.CheckRecord;
import com.ting.honormanage.entity.ReportRecord;
import com.ting.honormanage.entity.CheckerInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/3/21 13:14
 */
public interface CheckRecordRepository extends CrudRepository<CheckRecord,Long>{

    /**
     * 根据ID查询申报记录
     */
    CheckRecord findCheckRecordById(Long id);

    List<CheckRecord> findCheckRecordByReportRecord(ReportRecord reportRecord);

    List<CheckRecord> findCheckRecordByCheckerInfo(CheckerInfo studentInfo);

    List<CheckRecord> findCheckRecordByOpinion(String opinion);

    CheckRecord findCheckRecordByCheckTime(Date checkTime);

    List<CheckRecord> findCheckRecordByReportRecordAndCheckerInfo(ReportRecord reportRecord, CheckerInfo studentInfo);

}
