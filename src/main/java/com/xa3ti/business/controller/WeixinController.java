package com.xa3ti.business.controller;

import com.xa3ti.base.util.BeanMapper;
import com.xa3ti.base.util.Log;
import com.xa3ti.base.util.XaUtil;
import com.xa3ti.business.entity.Article;
import com.xa3ti.business.entity.Menu;
import com.xa3ti.business.entity.Resource;
import com.xa3ti.business.entity.wxPOJO.MessageType;
import com.xa3ti.business.entity.wxPOJO.responsMessage.BaseMessage;
import com.xa3ti.business.entity.wxPOJO.responsMessage.NewsMessage;
import com.xa3ti.business.entity.wxPOJO.responsMessage.TextMessage;
import com.xa3ti.business.service.IKeywordService;
import com.xa3ti.business.service.MenuService;
import com.xa3ti.business.service.ResourceService;
import com.xa3ti.business.util.MessageUtil;
import com.xa3ti.business.util.WeixinUtil;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 14-3-28.
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController {
    public static int Index = 1;

    @Autowired
    MenuService menuService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    IKeywordService keywordService;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public String testWeixinGet(HttpServletRequest request) {

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        Log.d(signature + "," + timestamp + "," + nonce + "," + echostr);
        System.out.println(signature + "," + timestamp + "," + nonce + "," + echostr);

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (WeixinUtil.checkWeixinReques(signature, timestamp, nonce)) {
            return echostr;
        }
        return "error";
    }

    @RequestMapping(value = "test", method = RequestMethod.POST)
    @ResponseBody
    public String testWeixin(HttpServletRequest request) {

        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

        String result = "";
        InputStream is;
        try {
            is = request.getInputStream();

            HashMap<String, String> xmlMap = (HashMap<String, String>) MessageUtil.parseXML(is);
            BaseMessage baseMessage = new BaseMessage();
            baseMessage.setFromUserName(xmlMap.get("ToUserName"));
            baseMessage.setToUserName(xmlMap.get("FromUserName"));
            baseMessage.setCreateTime(new Date().getTime());
            baseMessage.setMsgId(1);
            String msgType = xmlMap.get("MsgType");
            System.out.println("msgType =="+msgType);
            System.out.println("xmlMap.get(Event) =="+xmlMap.get("Event"));
            if (msgType.equals(MessageType.EVENT)&&XaUtil.isNotEmpty(xmlMap.get("Event"))) {
                if (xmlMap.get("Event").equals("subscribe")||xmlMap.get("Event").equals("unsubscribe"))
                {
                    //关注取消关注
                    System.out.println("subscribe ++");
//                    Resource res = resourceService.queryById(23);
                    Resource res = resourceService.queryById(Index);
                    if (XaUtil.isNotEmpty(res))
                    {
                        List<Article> list = resourceService.queryArticleByResourceId(res.getResourceId());
                        NewsMessage newsMessage = new NewsMessage();
                        List<com.xa3ti.business.entity.wxPOJO.responsMessage.Article> articles = new ArrayList<com.xa3ti.business.entity.wxPOJO.responsMessage.Article>();
                        for (Article item : list) {
                            com.xa3ti.business.entity.wxPOJO.responsMessage.Article article = new com.xa3ti.business.entity.wxPOJO.responsMessage.Article();
                            article.setUrl(item.getUrl());
                            article.setTitle(item.getTitle());
                            article.setPicUrl(basePath + item.getPicUrl());
                            article.setDescription(item.getDescription());
                            articles.add(article);
                        }
                        newsMessage.setArticleCount(articles.size());
                        newsMessage.setArticles(articles);
                        baseMessage.setMsgType(MessageType.NEWS);
                        BeanMapper.copy(baseMessage, newsMessage);
                        result = MessageUtil.newsToXml(newsMessage);
                    }
                }else if (xmlMap.get("Event").equals(MessageType.CLICK)){
                        //click 获取资源菜单进
                    System.out.println("xmlMap.get(\"EventKey\") = " + xmlMap.get("EventKey"));
                    Menu menu = menuService.queryByKey(xmlMap.get("EventKey"));
                    Resource res = resourceService.queryById(menu.getResourceId());
                    if (res != null) {
                        if(res.getType()==Resource.text){
                            TextMessage textMessage = new TextMessage();
                            textMessage.setContent(res.getContent());
                            baseMessage.setMsgType(MessageType.TEXT);
                            BeanMapper.copy(baseMessage,textMessage);
                            result = MessageUtil.textToXml(textMessage);
                        }else{
                            List<Article> list = resourceService.queryArticleByResourceId(res.getResourceId());
                            NewsMessage newsMessage = new NewsMessage();
                            List<com.xa3ti.business.entity.wxPOJO.responsMessage.Article> articles = new ArrayList<com.xa3ti.business.entity.wxPOJO.responsMessage.Article>();
                            for (Article item : list) {
                                com.xa3ti.business.entity.wxPOJO.responsMessage.Article article = new com.xa3ti.business.entity.wxPOJO.responsMessage.Article();
                                article.setUrl(item.getUrl());
                                article.setTitle(item.getTitle());
                                article.setPicUrl(basePath + item.getPicUrl());
                                article.setDescription(item.getDescription());
                                articles.add(article);
                            }
                            newsMessage.setArticleCount(articles.size());
                            newsMessage.setArticles(articles);
                            baseMessage.setMsgType(MessageType.NEWS);
                            BeanMapper.copy(baseMessage, newsMessage);
                            result = MessageUtil.newsToXml(newsMessage);
                            System.out.println(result);
                        }
                    }

                }else
                {
                    //event链接事件推送
                }
            }else if (msgType.equals("text"))
            {
                String key = xmlMap.get("Text");
                Resource res = keywordService.findResourceByKey(key);
                if (res != null) {
                    if(res.getType()==Resource.text){
                        TextMessage textMessage = new TextMessage();
                        textMessage.setContent(res.getContent());
                        baseMessage.setMsgType(MessageType.TEXT);
                        BeanMapper.copy(baseMessage,textMessage);
                        result = MessageUtil.textToXml(textMessage);
                    }else{
                        List<Article> list = resourceService.queryArticleByResourceId(res.getResourceId());
                        NewsMessage newsMessage = new NewsMessage();
                        List<com.xa3ti.business.entity.wxPOJO.responsMessage.Article> articles = new ArrayList<com.xa3ti.business.entity.wxPOJO.responsMessage.Article>();
                        for (Article item : list) {
                            com.xa3ti.business.entity.wxPOJO.responsMessage.Article article = new com.xa3ti.business.entity.wxPOJO.responsMessage.Article();
                            article.setUrl(item.getUrl());
                            article.setTitle(item.getTitle());
                            article.setPicUrl(basePath + item.getPicUrl());
                            article.setDescription(item.getDescription());
                            articles.add(article);
                        }
                        newsMessage.setArticleCount(articles.size());
                        newsMessage.setArticles(articles);
                        baseMessage.setMsgType(MessageType.NEWS);
                        BeanMapper.copy(baseMessage, newsMessage);
                        result = MessageUtil.newsToXml(newsMessage);
                        System.out.println(result);
                    }
                }else
                {
                    TextMessage textMessage = new TextMessage();
                    textMessage.setContent("感谢您的关注，您的满意是我们最大的追求。");
                    baseMessage.setMsgType(MessageType.TEXT);
                    BeanMapper.copy(baseMessage,textMessage);
                    result = MessageUtil.textToXml(textMessage);
                }

            }
            else{
                TextMessage textMessage = new TextMessage();
                textMessage.setContent("感谢您的关注，您的满意是我们最大的追求。");
                baseMessage.setMsgType(MessageType.TEXT);
                BeanMapper.copy(baseMessage,textMessage);
                result = MessageUtil.textToXml(textMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
//            result ="";
        } catch (DocumentException e) {
            e.printStackTrace();
//            result = "";
        }
        return result;
    }






}
