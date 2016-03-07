package com.xa3ti.business.repository;

import com.xa3ti.business.entity.Admin;
import com.xa3ti.business.entity.Award;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AwardRepository extends
    PagingAndSortingRepository<Award, Integer>,JpaSpecificationExecutor<Award>{

   public Award findByAwaRank(Integer awaRank);
}
