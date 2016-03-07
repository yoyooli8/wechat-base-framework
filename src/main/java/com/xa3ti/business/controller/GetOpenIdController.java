package com.xa3ti.business.controller;

import com.google.gson.Gson;
import com.xa3ti.base.util.XaUtil;
import com.xa3ti.business.commons.HttpUtil;
import com.xa3ti.business.entity.Constant;
import com.xa3ti.business.entity.wxPOJO.WeiXinObject;
import com.xa3ti.business.util.HttpsUtil;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 闯儿 on 14-4-24.
 */
@Controller
public class GetOpenIdController {
//    https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
//    https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
    private String get_userinfo="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
//    private String get_userinfo="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
   private String TOKEN =  "";


    private String get_access_token_url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
            "appid=APPID" +
            "&secret=SECRET&" +
            "code=CODE&grant_type=authorization_code";


    @RequestMapping("loginOpenId")
    public  ModelAndView loginOpenId( HttpServletRequest request,
                          HttpServletResponse response)
    {
        String code = request.getParameter("code");
        try {
            String rp = HttpsUtil
                    .sendGetRequest("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ Constant.APPID+"&secret="+Constant.APPSECRET+"&code="
                            + code + "&grant_type=authorization_code");
            System.out.println(rp);
            JSONObject jsonObject = new JSONObject(rp);
           String openId =  jsonObject.optString("openid");
            if (XaUtil.isNotEmpty(openId))
            {
                System.out.println("openId =="+openId);
                ModelAndView modelAndView =new ModelAndView("redirect:sigmaUser/login");
                modelAndView.addObject("openId",openId);
                return   modelAndView;
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
             return null;
    }



    @RequestMapping("loginWeiXinObject")
    public  ModelAndView loginWeiXinObject( HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {

        ModelAndView modelAndView =new ModelAndView("redirect:/web/zhuanpan/index");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String code=request.getParameter("code");
        System.out.println(" code ==="+code);
        get_access_token_url=get_access_token_url.replace("APPID", Constant.APPID);
        get_access_token_url=get_access_token_url.replace("SECRET", Constant.APPSECRET);
        get_access_token_url=get_access_token_url.replace("CODE", code);

        String json= HttpsUtil.sendGetRequest(get_access_token_url);
        System.out.println(" get_access_token_url  json==="+json);

        JSONObject jsonObject=new JSONObject(json);
        String access_token=jsonObject.getString("access_token");
        String openid=jsonObject.getString("openid");
        System.out.println(" get_access_token_url  access_token==="+access_token);
        System.out.println(" get_access_token_url  openid==="+openid);
        get_userinfo=get_userinfo.replace("ACCESS_TOKEN", access_token);
        get_userinfo=get_userinfo.replace("OPENID", openid);

        String userInfoJson=HttpsUtil.sendGetRequest(get_userinfo);

        JSONObject userInfoJO=new JSONObject(userInfoJson);
        String user_openid=userInfoJO.optString("openid");
        String user_nickname=userInfoJO.optString("nickname","");
//        response.setContentType("text/html; charset=utf-8");
//        String user_sex=userInfoJO.getString("sex");
//        String user_province=userInfoJO.getString("province");
//        String user_city=userInfoJO.getString("city");
//        String user_country=userInfoJO.getString("country");
//        String user_headimgurl=userInfoJO.getString("headimgurl");
        System.out.println("userInfoJson ==="+userInfoJson);
        System.out.println("user_openid ==="+user_openid);
        System.out.println("user_nickname ==="+user_nickname);
        System.out.println("user_nickname ==="+new String(user_nickname.getBytes("UTF-8"),"GB2312"));
        System.out.println("user_nickname ==="+new String(user_nickname.getBytes("UTF-8"),"GBK"));
        System.out.println("user_nickname ==="+new String(user_nickname.getBytes("UTF-8"),"ISO8859-1"));
        System.out.println("user_nickname ==="+new String(user_nickname.getBytes("GBK"),"GB2312"));
        System.out.println("user_nickname ==="+new String(user_nickname.getBytes("GBK"),"ISO8859-1"));
        System.out.println("user_nickname ==="+new String(user_nickname.getBytes("GBK"),"UTF-8"));
        System.out.println("user_nickname ==="+new String(user_nickname.getBytes("GB2312"),"UTF-8"));
        System.out.println("user_nickname ==="+new String(user_nickname.getBytes("GB2312"),"GBK"));
        System.out.println("user_nickname ==="+new String(user_nickname.getBytes("GB2312"),"ISO8859-1"));
        System.out.println("user_nickname ==="+new String(user_nickname.getBytes("GB2312"),"GB2312"));


//        modelAndView.addObject("weiXinObject",weiXinObject);
        modelAndView.addObject("openId",user_openid);
        modelAndView.addObject("name",user_nickname);
        return modelAndView;
    }
}
