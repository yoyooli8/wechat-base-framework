package com.xa3ti.business.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="wx_article")
public class Article implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//ID
	private int artId;
	//属性
	private int resourceId;
	private String title;
	private String description;
	private String picUrl;
	private String url;
	private int infoId;
	//private Information info;
    
    /**getter setter*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getArtId() {
		return artId;
	}
	public void setArtId(int artId) {
		this.artId = artId;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	@Column(length = 600)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
//	@Transient
//	public Information getInfo() {
//		return info;
//	}
//	public void setInfo(Information info) {
//		this.info = info;
//	}
}
