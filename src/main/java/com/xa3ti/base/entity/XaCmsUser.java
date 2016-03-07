package com.xa3ti.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 用户实体.
 */
@Entity
@Table(name = "tb_ms_cms_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XaCmsUser implements java.io.Serializable {

	private static final long serialVersionUID = -2588141205901945887L;

	/** 用户Id. */
	private Long userId;

	/** 用户名. */
	private String userName;

	/** 真实姓名. */
	private String realName;

	/**
	 * 用户状态. 0.正常 1.锁定 9.删除
	 */
	private String status;

	/** 密码. */
	private String password;

	/** 手机号. */
	private String mobile;

	/** 邮箱地址. */
	private String email;

	/** 用户描述. */
	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "user_name", nullable = false, length = 20)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "real_name", nullable = false, length = 20)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "status", nullable = false, length = 2)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "password", nullable = false, length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "description", length = 150)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}