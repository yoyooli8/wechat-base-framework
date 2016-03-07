package com.xa3ti.business.entity;

public class PlateGame {
	private int angle;//角度
	private int awaId;//奖品Id
	private boolean noEnough;//积分是否足够
	private String info;//信息
	private boolean winner;//是否是中奖者角度信息
	private String awardInfo1;
	private String awardInfo2;
	private String awardInfo3;
	
	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	public int getAwaId() {
		return awaId;
	}
	public void setAwaId(int awaId) {
		this.awaId = awaId;
	}
	public boolean isNoEnough() {
		return noEnough;
	}
	public void setNoEnough(boolean noEnough) {
		this.noEnough = noEnough;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	public String getAwardInfo1() {
		return awardInfo1;
	}
	public void setAwardInfo1(String awardInfo1) {
		this.awardInfo1 = awardInfo1;
	}
	public String getAwardInfo2() {
		return awardInfo2;
	}
	public void setAwardInfo2(String awardInfo2) {
		this.awardInfo2 = awardInfo2;
	}
	public String getAwardInfo3() {
		return awardInfo3;
	}
	public void setAwardInfo3(String awardInfo3) {
		this.awardInfo3 = awardInfo3;
	}
}
