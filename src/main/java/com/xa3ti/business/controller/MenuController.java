package com.xa3ti.business.controller;

import com.xa3ti.business.commons.HttpsUtils;
import com.xa3ti.business.commons.MenuJson;
import com.xa3ti.business.commons.TokenUtil;
import com.xa3ti.business.entity.Menu;
import com.xa3ti.business.entity.Resource;
import com.xa3ti.business.service.MenuService;
import com.xa3ti.business.service.ResourceService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MenuController {
    @Autowired
    MenuService service;

    @Autowired
    ResourceService resSer;

    /**
     * 菜单管理页面
     */
    @RequestMapping(value = "/3ti/menu/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/menu/menu");

        List<Menu> ms = service.queryByFlag(1);
        for (int i = 0; i < ms.size(); i++) {
            ms.get(i).setSubMenu(service.queryMenuByPId(ms.get(i).getMeunId()));
        }

        request.setAttribute("ms", ms);

        return mv;
    }

    /**
     * 请求子菜单修改页面
     */
    @RequestMapping(value = "/3ti/menu/sInfo")
    public ModelAndView sInfo(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/menu/menuSInfo");

        String menuId = request.getParameter("menuId");

        Menu m = null;
        if (menuId != null && !"".equals(menuId)) {
            m = service.queryById(Integer.valueOf(menuId));

            if (m.getResourceId() != 0) {
                Resource res = resSer.queryById(m.getResourceId());
                request.setAttribute("res", res);
            }
        } else {
            String parId = request.getParameter("parId");
            System.out.println("parID   == "+parId);
            Long c = service.getSCount(Integer.valueOf(parId));
            if (c >= 5) {
                request.setAttribute("flag", "man");
            } else {
                m = new Menu();
                m.setParId(Integer.valueOf(parId));
            }
        }

        request.setAttribute("menu", m);

        return mv;
    }

    /**
     * 请求主菜单修改页面
     */
    @RequestMapping(value = "/3ti/menu/pInfo")
    public ModelAndView pInfo(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/menu/menuPInfo");

        String menuId = request.getParameter("menuId");
        if (menuId != null && !"".equals(menuId)) {
            Menu m = service.queryById(Integer.valueOf(menuId));
            request.setAttribute("menu", m);
        } else {
            Long c = service.getPCount();
            if (c >= 3) {
                request.setAttribute("flag", "man");
            }
        }
        return mv;
    }

    /**
     * 保存数据
     */
    @RequestMapping(value = "menu/save")
    public void save(Menu menu, HttpServletResponse response) {
        System.out.println("menu = = = "+menu.toString());

        try {
            Menu dbItem = service.save(menu);
            if (dbItem.getType().equals(Menu.CLICK)) {
                dbItem.setKeyVal("3TI_" + dbItem.getMeunId());
                service.save(dbItem);
            }
            response.getWriter().write("<script type=\"text/javascript\">parent.location.reload();</script>");
            response.getWriter().flush();
        } catch (Exception e) {
        }
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "/3ti/menu/delete")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("redirect:/3ti/menu/index");

        String menuId = request.getParameter("meunId");
        String flag = request.getParameter("flag");

        if (menuId != null && !"".equals(menuId)) {
            service.deleteById(Integer.valueOf(menuId));

            //删除子菜单
            if ("1".equals(flag)) {
                service.deleteByParId(Integer.valueOf(menuId));
            }
        }

        return mv;
    }

    /**
     * 发布数据
     */
    @RequestMapping(value = "/3ti/menu/publish")
    public ModelAndView publish(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/menu/publish");

        List<Menu> ms = service.queryByFlag(1);
        for (int i = 0; i < ms.size(); i++) {
            ms.get(i).setSubMenu(service.queryMenuByPId(ms.get(i).getMeunId()));
        }

        try {
            String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + TokenUtil.getToken();

            String json = MenuJson.process(ms);
            String info = HttpsUtils.https(url, json, HttpsUtils.POST);
            JSONObject jsonObject = new JSONObject(info);
            int code = jsonObject.optInt("errcode",-2);
            String str = "";
            switch (code)
            {
                case -2:
                    str ="操作有误，请正确操作！";
                    break;
                case -1:
                    str ="系统繁忙，请稍后再试！";
                    break;
                case 0:
                    str ="请求成功，请关注公众号菜单变更！";
                    break;
                default:
                    str ="操作有误，请正确操作！";
                    break;
            }
            request.setAttribute("info", str);
        } catch (Exception e) {
            request.setAttribute("info", e.getMessage());
        }
        return mv;
    }



    @RequestMapping("/3ti/menu/checkNameExist")
    @ResponseBody
    public String checkNameExist(HttpServletRequest request,String     menuName)
    {
        String isExist="n";
        boolean b = service.checkNameExist(menuName);
        System.out.println(" service.checkNameExist(name)  ="+b);
        isExist=service.checkNameExist(menuName)?"{\"error\":\"名称已存在\"}":"{\"ok\":\"可以使用\"}";
        return isExist;
    }
}
