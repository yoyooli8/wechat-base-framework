package com.xa3ti.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.xa3ti.base.entity.XaCmsResource;

public interface XaCmsResourceRepository extends
		PagingAndSortingRepository<XaCmsResource, Long>,
		JpaSpecificationExecutor<XaCmsResource> {

	@Query("select ro.roleName from XaCmsRoleResource rr,XaCmsRole ro where rr.resourceId = ?1 and rr.roleId=ro.roleId")
	public List<String> findRoleNameByResourceId(String resourceId);

	@Query("select ro.roleName from XaCmsUser us,XaCmsUserRole ur,XaCmsRole ro where us.userName=?1 and us.userId = ur.userId and ro.roleId=ur.roleId")
	public List<String> findRoleNameByUserName(String userName);

	@Query("select rr.resourceId from XaCmsUserRole ur,XaCmsRoleResource rr"
			+ " where ur.userId = ?1 and ur.roleId=rr.roleId")
	public List<String> findResourceByUserId(Long userId);

	@Query("select mr.resourceUrl from XaCmsRoleResource rr, XaCmsResource mr"
			+ " where rr.roleId=?1 and rr.resourceId=mr.resourceId")
	public List<String> findResourceByRoleId(Long roleId);


}