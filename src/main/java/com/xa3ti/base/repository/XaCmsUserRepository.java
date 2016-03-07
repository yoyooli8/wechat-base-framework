package com.xa3ti.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.xa3ti.base.entity.XaCmsUser;

public interface XaCmsUserRepository extends
		PagingAndSortingRepository<XaCmsUser, Long>,
		JpaSpecificationExecutor<XaCmsUser> {

	@Query("select ro.roleName from XaCmsUserRole ur,XaCmsRole ro where ur.userId = ?1 and ur.roleId=ro.roleId")
	public List<String> findRoleNameByUserId(Long userId);

	public XaCmsUser findByUserName(String userName);
}
