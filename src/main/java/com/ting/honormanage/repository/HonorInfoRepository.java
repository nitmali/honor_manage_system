package com.ting.honormanage.repository;

import com.ting.honormanage.entity.HonorInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/3/21 13:14
 */
public interface HonorInfoRepository extends CrudRepository<HonorInfo,Long>{
    /**
     * 根据学年间断查询荣誉
     */
    List<HonorInfo> findByYearBetween(Date year1,Date year2);

    /**
     * 根据学年查询荣誉
     */
    List<HonorInfo> findByYear(Date year);

    /**
     * 根据ID查询荣誉
     */
    HonorInfo findById(Long id);

    /**
     * 根据名称查询荣誉
     */
    HonorInfo findByName(String name);


}
