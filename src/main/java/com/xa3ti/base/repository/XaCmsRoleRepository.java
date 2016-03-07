package com.xa3ti.base.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.xa3ti.base.entity.XaCmsRole;

public interface XaCmsRoleRepository extends
		PagingAndSortingRepository<XaCmsRole, Long>,
		JpaSpecificationExecutor<XaCmsRole> {

}
