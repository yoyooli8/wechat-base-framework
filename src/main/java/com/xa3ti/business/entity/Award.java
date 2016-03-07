package com.xa3ti.business.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="sm_AWARD")
public class Award implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final int ONE=1;
	public static final int TWO=2;
	public static final int THREE=3;
	//ID
	private Integer awaId;
	//属性
	private String awaName;//奖品名称
	private String remark;//描述信息
	private int awaNum;//奖品总数
	private int awaRank;//奖品奖项
	private float probability;//中奖概率
	private String info;

    /**getter setter*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getAwaId() {
		return awaId;
	}
	public void setAwaId(Integer awaId) {
		this.awaId = awaId;
	}
	public String getAwaName() {
		return awaName;
	}
	public void setAwaName(String awaName) {
		this.awaName = awaName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getAwaNum() {
		return awaNum;
	}
	public void setAwaNum(int awaNum) {
		this.awaNum = awaNum;
	}
	public int getAwaRank() {
		return awaRank;
	}
	public void setAwaRank(int awaRank) {
		this.awaRank = awaRank;
	}
	public float getProbability() {
		return probability;
	}
	public void setProbability(float probability) {
		this.probability = probability;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
