package com.ting.honormanage.repository;

import com.ting.honormanage.entity.CheckerInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nitmali@126.com
 * @date 2018/4/2 21:17
 */
@Repository
public interface CheckerInfoRepository extends CrudRepository<CheckerInfo,Long> {

    /**
     * 根据审核员编号查询审核员信息
     */
    CheckerInfo findCheckerInfoById(Long id);

    /**
     * 根据审核员姓名查询审核员信息
     */
    List<CheckerInfo> findCheckerInfoByName(String name);

    /**
     * 根据审核员用户名查询审核员信息
     */
    CheckerInfo findCheckerInfoByUsername(String username);

    /**
     * 根据审核员手机号查询审核员信息
     */
    List<CheckerInfo> findCheckerInfoByPhone(String phone);

    /**
     * 根据审核员权限查询审核员信息
     */
    List<CheckerInfo> findCheckerInfoByAuthority(String authority);

}