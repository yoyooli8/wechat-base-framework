package com.xa3ti.business.repository;

import com.xa3ti.business.entity.Prize;
import com.xa3ti.business.entity.PrizeLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PrizeLogRepository extends
		PagingAndSortingRepository<PrizeLog, Integer>,
		JpaSpecificationExecutor<PrizeLog> {
//
    public Page<PrizeLog> findByStatusNot( Integer status,Pageable pageable);
//
//    @Query("SELECT p FROM Prize p where p.type=?1 and p.status=1")
//    public Prize findPublishByType(Integer type);

}
