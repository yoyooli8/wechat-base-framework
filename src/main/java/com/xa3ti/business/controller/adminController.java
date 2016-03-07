
package com.xa3ti.business.controller;

import com.xa3ti.business.entity.Admin;
import com.xa3ti.business.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Admin")
public class adminController {

    /**
     * localhost:8080/xa3ti-web/sigmaUser/regist?openId=...
     *
     * @author yzuo
     */
    @Autowired
    private AdminService adminService;
    
    /**
     *
     *@author yzuo
     */
    @RequestMapping("loginAdmin")
    public ModelAndView loginAdmin(HttpServletRequest request,
                                       HttpServletResponse response) {
        HttpSession session=request.getSession();
        String userName = request.getParameter("userName");
        String pwd=request.getParameter("pwd");
        Admin admin = adminService.validAdminUser(userName,pwd);
        if(admin==null){
            request.setAttribute("errorMsg", "用户名或密码不正确");
            return new ModelAndView("login");
        }
        request.getSession().setAttribute("admin", admin);
        return new ModelAndView("protal");
    }
    
    @RequestMapping("toLoginAdmin")
    public ModelAndView toLoginAdmin(HttpServletRequest request,
    		HttpServletResponse response) {
    	return new ModelAndView("login");
    }

    
    

}