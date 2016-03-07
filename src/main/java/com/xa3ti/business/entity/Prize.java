package com.xa3ti.business.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 闯儿 on 14-6-6.
 */

@Entity
//奖品
@Table(name = "prize")
public class Prize {
    public static final int FIRST_PRIZE  =1;
    public static final int  SECOND_PRIZE  =2;
    public static final int  THIRD_PRIZE  =3;
//    public static final int  FRIEND_PRIZE  =4;
    public static final int  NULL_PRIZE  =5;



    private  Integer  id; //id
    private  String name; //对外中文名称
    private  String imagePath; //图片路径
    private  int type;   //奖品等级
    private  int number; //数量
    private  double  probability;//中奖概率
    private  int  status;//状态
    private List<PrizeLog> logs ;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    @Column(name = "probability")
    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    @Column(name = "image_Path")
    public String getImagePath() {
        return imagePath;
    }



    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
        @Transient
    public List<PrizeLog> getLogs() {
        return logs;
    }

    public void setLogs(List<PrizeLog> logs) {
        this.logs = logs;
    }

    @Override
    public String toString() {
        return "Prize{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", type=" + type +
                ", number=" + number +
                ", probability=" + probability +
                ", status=" + status +
                '}';
    }
}
