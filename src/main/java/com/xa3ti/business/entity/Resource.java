package com.xa3ti.business.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="wx_resource")
public class Resource implements Serializable{
	private static final long serialVersionUID = 1L;
	public static int text = 1;
	//ID
	private int resourceId;
	//属性
	private int type;//资源类型|文本:1|单图文:2|多图文:3
	private String name;//资源名称
	private String description;//资源描述信息
	private String content;
	private List<Article> article;
    
    /**getter setter*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    @Column(name = "description" ,length = 500)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(length = 20000)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Transient
	public List<Article> getArticle() {
		return article;
	}
	public void setArticle(List<Article> article) {
		this.article = article;
	}
}
