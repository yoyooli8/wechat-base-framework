package com.xa3ti.business.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="wx_menu")
public class Menu implements Serializable{
	private static final long serialVersionUID = 1L;
    public static final String CLICK = "click";
    public static final String VIEW = "view";
    public static final String MENU = "menu";
	//ID
	private int meunId;
	//属性
	private String menuName;
	private String type;
	private String url;
	private String keyVal;
	private int flag;//1:一级菜单|2：二级子菜单
	private int parId;
	private int resourceId;
	private List<Menu> subMenu;
    
    /**getter setter*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    public int getMeunId() {
		return meunId;
	}
	public void setMeunId(int meunId) {
		this.meunId = meunId;
	}
    @Column(name = "menu_name",length = 100)
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
    @Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    @Column(name = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "key_val")
	public String getKeyVal() {
		return keyVal;
	}
	public void setKeyVal(String keyVal) {
		this.keyVal = keyVal;
	}
    @Column(name = "flag")
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
    @Column(name = "par_id")
	public int getParId() {
		return parId;
	}
	public void setParId(int parId) {
		this.parId = parId;
	}
    @Column(name = "resource_id")
    public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	@Transient
	public List<Menu> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}


    @Override
    public String toString() {
        return "Menu{" +
                "meunId=" + meunId +
                ", menuName='" + menuName + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", keyVal='" + keyVal + '\'' +
                ", flag=" + flag +
                ", parId=" + parId +
                ", resourceId=" + resourceId +
                ", subMenu=" + subMenu +
                '}';
    }
}
