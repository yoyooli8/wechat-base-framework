package com.xa3ti.business.entity;

import javax.persistence.*;

/**
 * Created by 闯儿 on 14-6-24.
 */
@Entity

@Table(name = "prize_log")
public class PrizeLog {

    private Integer prizeLogId;
    private  String  user_openid;
    private  String  user_nickname;//中奖用户id
     //中奖用户名
    private Integer prizeId;
    private String prizeName;
    private Integer awaRank;

    private String  time;//中奖时间
    private Integer status;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getPrizeLogId() {
        return prizeLogId;
    }

    public void setPrizeLogId(Integer prizeLogId) {
        this.prizeLogId = prizeLogId;
    }


    @Column(name = "prize_Id")
    public Integer getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Integer prizeId) {
        this.prizeId = prizeId;
    }
    @Column(name = "prize_Name")
    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "user_openid")
    public String getUser_openid() {
        return user_openid;
    }

    public void setUser_openid(String user_openid) {
        this.user_openid = user_openid;
    }
    @Column(name = "user_nickname")
    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }
    @Column(name = "awaRank")
    public Integer getAwaRank() {
        return awaRank;
    }

    public void setAwaRank(Integer awaRank) {
        this.awaRank = awaRank;
    }


    @Override
    public String toString() {
        return "PrizeLog{" +
                "prizeLogId=" + prizeLogId +
                ", user_openid='" + user_openid + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", prizeId=" + prizeId +
                ", prizeName='" + prizeName + '\'' +
                ", awaRank=" + awaRank +
                ", time='" + time + '\'' +
                ", status=" + status +
                '}';
    }
}
