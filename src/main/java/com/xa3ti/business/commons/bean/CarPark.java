package com.xa3ti.business.commons.bean;

import java.io.Serializable;

public class CarPark implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;//停车场名称
	private String address;//地址
	private double distance;//距离
	private String url;//百度地图url
	private String info;//电话
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
