package com.xa3ti.business.repository;

import com.xa3ti.business.entity.Admin;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminRepository extends
    PagingAndSortingRepository<Admin, Long>,JpaSpecificationExecutor<Admin>{

    @Query("select n from Admin n where n.userName=?1")
    public Admin findByAdminName(String userName);
}
