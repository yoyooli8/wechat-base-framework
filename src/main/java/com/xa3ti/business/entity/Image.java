package com.xa3ti.business.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="wx_image")
public class Image implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//ID
	private int id;
	//属性
	private String url;

	private String name;

    /**getter setter*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    @Column(name = "url" ,length = 500)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    @Column(name = "name" ,length = 500)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
