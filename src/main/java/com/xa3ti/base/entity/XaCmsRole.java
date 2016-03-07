package com.xa3ti.base.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

// TODO: Auto-generated Javadoc
/**
 * 系统角色实体.
 */
@Entity
@Table(name="tb_ms_cms_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XaCmsRole implements Serializable{
	
	private static final long serialVersionUID = 700235310223571456L;

	
	/** 角色Id. */
	private Long roleId;
	
	/** 角色名称. */
	private String roleName;
	
	/** 状态. */
	private String status;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name="role_name", nullable=false, length=40)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name="status", nullable=false, length=2)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
