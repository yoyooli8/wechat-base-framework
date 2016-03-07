package com.xa3ti.business.entity;

/**
 * Created by 闯儿 on 14-6-24.
 */
public class User {

    private String id;

    /** 用户名称 */
    private String name;

    /** 用户昵称 */
    private String nickname;

    /** 用户密码 */
    private String password;

    /** 用户电话 */
    private String cellphone;

    /** 会员卡号 */
    private String cardnumber;

    private String vericode;

    /** 用户email */
    private String email;

    /** 用户等级 */
    private String urating = "0";

    /** 用户性别 */
    private String gender;

    /** 用户头像 */
    private String portrait;

    /** 用户简介 */
    private String brief;

    /** 用户活动积分 */
    private int acredits;

    /** 用户消费积分 */
    private int ccredits;

    private String checkin = "0";

    private String jpushId ;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getVericode() {
        return vericode;
    }

    public void setVericode(String vericode) {
        this.vericode = vericode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrating() {
        return urating;
    }

    public void setUrating(String urating) {
        this.urating = urating;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getAcredits() {
        return acredits;
    }

    public void setAcredits(int acredits) {
        this.acredits = acredits;
    }

    public int getCcredits() {
        return ccredits;
    }

    public void setCcredits(int ccredits) {
        this.ccredits = ccredits;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getJpushId() {
        return jpushId;
    }

    public void setJpushId(String jpushId) {
        this.jpushId = jpushId;
    }
}
