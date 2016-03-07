package com.xa3ti.base.entity;

import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

// TODO: Auto-generated Javadoc
/**
 * 系统资源实体.
 */
@Entity
@Table(name="tb_ms_cms_resource")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XaCmsResource   implements Serializable{

	private static final long serialVersionUID = -6716116635654730576L;
	
	
	/** 资源Id. */
	private String resourceId;
	
	/** 资源名称. */
	private String resourceName;
	
	/** 资源路径. */
	private String resourceUrl;
	
	/** 排序号(序号越大，菜单显示越下面). */
	private String orderNum;
	
	/** 显示类型
	 *  0: 页面级别
	 *  1：按钮级别 . */
	private String showType;
	

	@Id
	@Column(name="resource_id", nullable=false, length=40)
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name="resource_name", nullable=false, length=40)
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name="resource_url", nullable=false, length=100)
	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	@Column(name="order_num", nullable=false, length=5)
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name="show_type", nullable=false, length=1)
	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}
	
}
