package com.ting.honormanage.repository;

import com.ting.honormanage.entity.ManagerInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author nitmali@126.com
 * @date 2018/4/10 10:46
 */
public interface ManagerInfoRepository extends CrudRepository<ManagerInfo,Long>{
    ManagerInfo findManagerInfoByUsername(String username);
}
