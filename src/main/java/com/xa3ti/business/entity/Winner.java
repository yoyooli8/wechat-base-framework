package com.xa3ti.business.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="sm_WINNER")
public class Winner implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//ID
	private int winId;
	//属性
	private int actId;//活动编号
	private int awaId;//奖品编号
	private int flag;//0:发送奖品|1:空缺
	private String winUserName;//中奖用户编号
	private String winUserId;//中奖用户编号
	private Date winDate;//中奖时间
    
    /**getter setter*/
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(length = 6)
    public int getWinId() {
		return winId;
	}
	public void setWinId(int winId) {
		this.winId = winId;
	}
	public int getActId() {
		return actId;
	}
	public void setActId(int actId) {
		this.actId = actId;
	}
	public int getAwaId() {
		return awaId;
	}
	public void setAwaId(int awaId) {
		this.awaId = awaId;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getWinUserName() {
		return winUserName;
	}
	public void setWinUserName(String winUserName) {
		this.winUserName = winUserName;
	}
	public String getWinUserId() {
		return winUserId;
	}
	public void setWinUserId(String winUserId) {
		this.winUserId = winUserId;
	}
	public Date getWinDate() {
		return winDate;
	}
	public void setWinDate(Date winDate) {
		this.winDate = winDate;
	}
}
