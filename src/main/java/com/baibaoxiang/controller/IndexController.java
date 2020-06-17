package com.baibaoxiang.controller;
import com.baibaoxiang.po.Area;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.po.ArticleType;
import com.baibaoxiang.po.School;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.service.ArticleTypeService;
import com.baibaoxiang.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
/**
 * @author chenlin
 */
@Controller
public class IndexController {
    @Autowired
    SchoolService schoolService;
    @Autowired
    ArticleTypeService articleTypeService;
    @Autowired
    ArticleService articleService;
    /**
     * 展示首页
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView showIndex() throws Exception {
        List<School> school = schoolService.selectDifferentSchoolName();
        ModelAndView model = new ModelAndView("/index");
        model.addObject("school",school);
        //默认二师的外键为1
        List<Area> area = schoolService.selectSchoolArea(1);
        model.addObject("area",area);
        List<ArticleType> articleTypeList = articleTypeService.selectArticleTypes();
        model.addObject("articleTypeList",articleTypeList);
        List<Article> articleList = articleService.selectTopArticle(1,null,null);
        model.addObject("articleList",articleList);
        return model;
    }
    /**
     * 显示查询的页面
     *  url:/item-list
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
    /**
     * 查询地区名
     *
     * @param schoolNo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index/queryAreaName",method=RequestMethod.POST)
    @ResponseBody
    public List<Area> queryAreaName(Integer schoolNo) throws Exception{
       return schoolService.selectSchoolArea(schoolNo);
    }
    /**
     * 得到地区文章
     * @param areaNo
     * @param typeNo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index/getAreaArticle",method= RequestMethod.POST)
    @ResponseBody
    public List<Article> getAreaArticle(@RequestParam("areaNo") String areaNo,@RequestParam("typeNo") String typeNo,Integer page,Integer rows) throws Exception {
        if ("1".equals(typeNo) ){
            return articleService.selectTopArticle(Integer.parseInt(areaNo),page,rows);
        }
        return articleService.selectByTypeArea(Integer.parseInt(areaNo),Integer.parseInt(typeNo),page,rows);
    }
    /**
     * 改变地区时，文章相应改变
     * @param areaNo
     * @param typeNo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index/changeAreaArticle",method= RequestMethod.POST)
    @ResponseBody
    public List<Article> changeAreaArticle(@RequestParam("areaNo") String areaNo, @RequestParam("typeNo") String typeNo, Integer page) throws  Exception{
        if ("1".equals(typeNo)){
            return articleService.selectTopArticle(Integer.parseInt(areaNo),null,null);
        }
        return articleService.selectByTypeArea(Integer.parseInt(areaNo),Integer.parseInt(typeNo),page,null);
    }
 }
