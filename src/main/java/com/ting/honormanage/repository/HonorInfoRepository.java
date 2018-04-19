package com.ting.honormanage.repository;

import com.ting.honormanage.entity.HonorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.w3c.dom.ls.LSInput;

import java.util.Date;
import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/3/21 13:14
 */
public interface HonorInfoRepository extends JpaRepository<HonorInfo,Long> {
    /**
     * 根据学年间断查询荣誉
     */
    List<HonorInfo> findHonorInfoByYearBetween(Date year1, Date year2);

    /**
     * 根据学年查询荣誉
     */
    List<HonorInfo> findHonorInfoByYear(Date year);

    /**
     * 根据ID查询荣誉
     */
    HonorInfo findHonorInfoById(Long id);

    /**
     * 根据名称查询荣誉
     */
    List<HonorInfo> findHonorInfoByName(String name);

    /**
     * 根据类型查询荣誉
     */
    List<HonorInfo> findHonorInfoByKind(HonorInfo.Kind kind);


    /**
     * 根据状态查询荣誉
     */
    List<HonorInfo> findHonorInfoByStatus(HonorInfo.Status status);

    List<HonorInfo> findHonorInfoByStatusNotLike(HonorInfo.Status status);

    List<HonorInfo> findAll();
}
