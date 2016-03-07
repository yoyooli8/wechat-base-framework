package com.xa3ti.business.entity.wxPOJO;


/**
 * Created by 闯儿 on 14-6-25.
 */
public class InitiativeTextMessage {

    private String touser;
    private String msgtype = "text";

    private Text text = new Text();


    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }
    public void addContent(String content)
    {
        this.text.setContent(content);
    }

}
