package com.xa3ti.business.entity;

import javax.persistence.*;

/**
 * Created by 闯儿 on 14-4-25.
 */
@Entity
@Table(name = "wx_keyword")
public class WXKeyword implements java.io.Serializable {
//    private static final long serialVersionUID = 1L;

    private Long id;
    private String wxKey;
    private String resName;
    private Integer resId;
    private Integer status;

    public WXKeyword() {
    }

    public WXKeyword(Long id, String wxKey, Integer resId, Integer status) {
        this.id = id;
        this.wxKey = wxKey;
        this.resId = resId;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "kw_key",length = 255)
    public String getWxKey() {
        return wxKey;
    }

    public void setWxKey(String wxKey) {
        this.wxKey = wxKey;
    }

    @Column(name = "res_id")
    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    @Column(name = "res_name",length = 500)
    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}