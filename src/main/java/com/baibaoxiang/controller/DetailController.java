package com.baibaoxiang.controller;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.service.ManagerService;
import com.baibaoxiang.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chenlin
 */
@Controller
public class DetailController {
    @Autowired
    ArticleService articleService;
    @Autowired
    RedisService redisService;
    @Autowired
    ManagerService managerService;
    @RequestMapping(value = "/detail",method= RequestMethod.GET)
    public ModelAndView showDetail(String no) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/detail");
        //根据id查文章的具体内容
        Article article = articleService.selectByPrimaryKey(no);
        redisService.saveReadNumRedis(no);
        modelAndView.addObject("article",article);
        return modelAndView;
    }
}
