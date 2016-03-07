package com.xa3ti.business.repository;

import com.xa3ti.business.entity.Prize;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PrizeRepository extends
		PagingAndSortingRepository<Prize, Integer>,
		JpaSpecificationExecutor<Prize> {

    public Prize findByTypeAndStatusNot(Integer type,Integer status);

    @Query("SELECT p FROM Prize p where p.type=?1 and p.status=1")
    public Prize findPublishByType(Integer type);

}
