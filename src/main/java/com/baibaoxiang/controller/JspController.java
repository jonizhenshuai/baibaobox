package com.baibaoxiang.controller;

import com.baibaoxiang.po.Article;
import com.baibaoxiang.po.Manager;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author:joe
 * @create：2019/5/30
 */
@Controller
@RequestMapping("/jsp")
public class JspController {

    @Autowired
    ManagerService managerService;

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/left")
    public ModelAndView left(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/left");
        return modelAndView;
    }

    @RequestMapping(value = "/top")
    public ModelAndView top(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/top");
        return modelAndView;
    }

    @RequestMapping(value = "/main")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/main");
        return modelAndView;
    }

    @RequestMapping(value = "/rights_management")
    public ModelAndView rights_management(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/rights_management");
        return modelAndView;
    }


    @RequestMapping(value = "/article")
    public ModelAndView atricle(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/article");
        return modelAndView;
    }

    @RequestMapping(value = "/school")
    public ModelAndView school(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/school");
        return modelAndView;
    }

    @RequestMapping(value = "/editpwd")
    public ModelAndView editpwd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/editpwd");
        return modelAndView;
    }


    @RequestMapping(value = "/cancellation")
    public ModelAndView cancellation(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/cancellation");
        return modelAndView;
    }

    @RequestMapping(value = "/personal_Information")
    public ModelAndView personal_Information(HttpSession session) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/personal_Information");
        String username = (String) session.getAttribute("username");
        Manager managerByUsername = managerService.findManagerByUsername(username);
        session.setAttribute("path","http://47.102.117.141/"+managerByUsername.getPath());
        return modelAndView;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("backstage/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/editArticle")
    public ModelAndView editArticle(@RequestParam(value = "no") String no) throws Exception {
        Article article = articleService.selectByPrimaryKey(no);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("article",article);
        modelAndView.setViewName("backstage/edit");
        return modelAndView;
    }
    @RequestMapping(value = "/searchManager")
    public ModelAndView searchManager() throws Exception {
       return new ModelAndView("backstage/SearchManager");
    }
}
