package com.xa3ti.business.controller;

import com.xa3ti.base.util.WebUitl;
import com.xa3ti.business.commons.ParamHandler;
import com.xa3ti.business.entity.Article;
import com.xa3ti.business.entity.Image;
import com.xa3ti.business.entity.Resource;
import com.xa3ti.business.service.ArticleService;
import com.xa3ti.business.service.ImageService;
import com.xa3ti.business.service.ResourceService;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class ResController {
    @Autowired
    ResourceService service;

    @Autowired
    ImageService imageSer;

    @Autowired
    ArticleService artSer;

    /**
     * 资源管理页面
     * @return
     */
    @RequestMapping(value="/3ti/resource/index")
    public ModelAndView index(
            HttpServletRequest request,
            @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,String sort,@RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize) {
        ModelAndView mv = new ModelAndView("/resource/resourceList");
        Pageable pageable = WebUitl.buildPageRequest(pageNumber, pageSize, sort);
        org.springframework.data.domain.Page<Resource> page = service.queryAll(pageable);
        mv.addObject("page",page);
        mv.addObject("index",WeixinController.Index);
        request.setAttribute("pageNumber", pageNumber);
        return mv;
    }
    /**
     * 检查资源名称是否存在
     * @return
     */
    @RequestMapping(value="3ti/resource/checkNameExist")
    @ResponseBody
    public String checkNameExist(HttpServletRequest request,String name){
        String isExist="n";
        isExist=service.checkNameExist(name)?"{\"error\":\"名称已存在\"}":"{\"ok\":\"可以使用\"}";
        return isExist;

    }

    /**
     * 资源管理页面
     * @return
     */
    @RequestMapping(value="/3ti/resource/show")
    public ModelAndView show(@RequestParam(value = "page", defaultValue = "1") Integer pageNumber,String sort,@RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize) {
        ModelAndView mv = new ModelAndView("/resource/resourceShow");
        Pageable pageable = WebUitl.buildPageRequest(pageNumber, pageSize, sort);
        org.springframework.data.domain.Page<Resource> page = service.queryAll(pageable);
        mv.addObject("page",page);
        return mv;
    }

    /**
     * 保存资源数据
     */
    @RequestMapping(value="/3ti/resource/save")
    public ModelAndView save(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("redirect:/3ti/resource/index");
        try {
            boolean flag = false;
            Resource r = ParamHandler.process(request, Resource.class);

            if(r.getResourceId() == 0) {
                flag = true;
            }

            service.saveOrUpdate(r);

            //新建资源并且资源类型为图文
            if(flag) {
                if(r.getType() == 2 || r.getType() == 3) {
                    mv = new ModelAndView("/resource/resource");
                    request.setAttribute("res", r);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }

    /**
     * 删除资源数据
     */
    @RequestMapping(value="/3ti/resource/delete")
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("redirect:/3ti/resource/index");
        String resId = request.getParameter("resourceId");
        List<Article> list=null;
        if(resId!=null&&!"".equals(resId)){
            list=artSer.queryByResourceId(Integer.valueOf(resId));
            for(Article a:list){
                artSer.deleteById(a.getArtId());
            }
        }
        service.deleteById(Integer.valueOf(resId));
        return mv;
    }

    /**
     * 新建资源
     */
    @RequestMapping(value="/3ti/resource/create")
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/resource/resource");
        return mv;
    }

    /**
     * 更新或者查看操作
     */
    @RequestMapping(value="/3ti/resource/process")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/resource/resourceUpdate");
        String resId = request.getParameter("resourceId");

        if(resId != null && !"".equals(resId)) {
            Resource r = service.queryById(Integer.valueOf(resId));

            //单图文和多图文时，请求art资源
            if(r.getType() == 2 || r.getType() == 3) {
                List<Article> arts = service.queryArticleByResourceId(r.getResourceId());
                r.setArticle(arts);
            }

            request.setAttribute("res", r);
        }

        //查看
        if("look".equals(request.getParameter("look"))) {
            mv = new ModelAndView("/resource/resourceLook");
        }
        return mv;
    }

    /**
     * 图片列表
     */
    @RequestMapping(value="/3ti/resource/imageList")
    public ModelAndView imageList(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView mv = new ModelAndView("/resource/imageList");
//
//        //当前页数
//        int page = 1;
//        try {
//            page = Integer.valueOf(request.getParameter("page"));
//        } catch (Exception e) {
//        }
//        //每页20张
//        Long count = imageSer.queryCount();
//        //总页数
//        int sum = (int) (count / 20);
//        if(count % 20 > 0) {
//            sum++;
//        }
//        //上一页
//        int last = page - 1;
//        if(last < 1) {
//            last = 1;
//        }
//        //下一页
//        int next = page + 1;
//        if(next > sum) {
//            if (sum==0)
//            {
//                next =1;
//            }else
//            {
//                next = sum;
//            }
//        }
//
//        int begin = (page - 1) * 20;
//
//        List<Image> imgs = imageSer.queryAll(begin, 20);
//        request.setAttribute("imgs", imgs);
//        request.setAttribute("page", page);
//        request.setAttribute("sum", sum);
//        request.setAttribute("last", last);
//        request.setAttribute("next", next);
//        request.setAttribute("count", count);
//
//        return mv;


        ModelAndView mv = new ModelAndView("/resource/imageList");
        //当前页数
        int page = 1;
        try {
            page = Integer.valueOf(request.getParameter("page"));
            System.err.println("^^^^^^^^^^^^^^^^^^"+page);
        } catch (Exception e) {
        }
        //每页20张
        Long count = imageSer.queryCount();
        //总页数
        int sum = (int) (count / 20);
        if(count % 20 > 0) {
            sum++;
        }
        //上一页
        int last = page - 1;
        if(last < 1) {
            last = 1;
        }
        //下一页
        int next = page + 1;
        if(next > sum) {
            if (sum==0)
            {
                next =1;
            }else
            {
                next = sum;
            }
        }

        int begin = (page - 1) ;
        List<Image> imgs = imageSer.queryAll(begin,20);

        request.setAttribute("imgs", imgs);
        request.setAttribute("page", page);
        request.setAttribute("sum", sum);
        request.setAttribute("last", last);
        request.setAttribute("next", next);
        request.setAttribute("count", count);

        return mv;
    }

    /**
     * 添加图片
     */
    @RequestMapping(value="/3ti/resource/addImage")
    public ModelAndView addImage(HttpServletRequest request
            , @RequestParam("imgFile")MultipartFile file
            , @RequestParam("name")String name) {
        ModelAndView mv = new ModelAndView("redirect:/3ti/resource/image");

        //保存文件至服务器
        try {
            String path = request.getSession().getServletContext().getRealPath("/upload/image");
            String fn = new Date().getTime() + ".jpg";

            Image im = new Image();
            im.setUrl("/upload/image/" + fn);
            if(name!="")
            {
                name=name.substring(name.lastIndexOf("\\")+1);
            }
            im.setName(name);
            imageSer.save(im);

            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, fn));
        } catch (IOException e) {
        }
        return mv;
    }

    /**
     * 添加管理
     */
    @RequestMapping(value="/3ti/resource/image")
    public ModelAndView image(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/resource/imageUpload");

        //当前页数
        int page = 1;
        try {
            page = Integer.valueOf(request.getParameter("page"));
        } catch (Exception e) {
        }
        //每页24张
        Long count = imageSer.queryCount();
        //总页数
        int sum = (int) (count / 24);
        if(count % 24 > 0) {
            sum++;
        }
        //上一页
        int last = page - 1;
        if(last < 1) {
            last = 1;
        }
        //下一页
        int next = page + 1;
        if(next > sum) {
            if (sum==0)
            {
                next =1;
            }else
            {
                next = sum;
            }
        }

        int begin = (page - 1);

        List<Image> imgs = imageSer.queryAll(begin, 24);

        request.setAttribute("imgs", imgs);
        request.setAttribute("page", page);
        request.setAttribute("sum", sum);
        request.setAttribute("last", last);
        request.setAttribute("next", next);
        request.setAttribute("count", count);

        return mv;
    }

    /**
     * 图片列表
     */
    @RequestMapping(value="/3ti/resource/deleteImage")
    public ModelAndView deleteImage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("redirect:/3ti/resource/image");

        String id = request.getParameter("id");

        if(id != null && !"".equals(id)) {
            imageSer.deleteById(Integer.valueOf(id));
        }

        return mv;
    }

    @RequestMapping(value="/3ti/resource/updateIndex")
    @ResponseBody
    public boolean updateIndex(int index)
    {
        try{
            WeixinController.Index =index;
            return 	WeixinController.Index ==index;
        }catch(Exception e){
            return false;
        }
    }
}
