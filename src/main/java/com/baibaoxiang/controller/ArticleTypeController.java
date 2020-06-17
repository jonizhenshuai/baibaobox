package com.baibaoxiang.controller;

import com.baibaoxiang.po.ArticleType;
import com.baibaoxiang.po.Manager;
import com.baibaoxiang.service.ArticleTypeService;
import com.baibaoxiang.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author sheng
 * @create 2019-05-03-11:32
 */
@Controller
@RequestMapping(value = "/articleType")
public class ArticleTypeController {

    @Autowired
    ArticleTypeService articleTypeService;

    @Autowired
    ManagerService managerService;

    private final static Logger logger = LoggerFactory.getLogger(ArticleTypeController.class);

    /**
     * 查询 某个articleType
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ArticleType findArticleTypeById(@PathVariable Integer id) throws Exception{
        ArticleType articleType = articleTypeService.selectByPrimaryKey(id);
        return articleType;
    }

    /**
     * 查询所有的 文章类型（移动端会调用到）
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    @ResponseBody
    public List<ArticleType> findArticleTypes() throws Exception{
        List<ArticleType> articleTypes = articleTypeService.selectArticleTypes();
        return articleTypes;
    }

    /**
     * 查询除了"推荐"外 的所有其他文章类型(管理后台使用)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public List<ArticleType> findArticleTypesSelective() throws Exception{
        List<ArticleType> articleTypes = articleTypeService.selectArticleTypesSelective();
        return articleTypes;
    }


    /**
     * 添加类型
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public int addArticleType(@RequestParam String TypeNew) throws Exception{
        int i= 0;
        if (!TypeNew.equals("")){
            ArticleType articleType = new ArticleType();
            articleType.setType(TypeNew);
            articleType.setSequenceNum(articleTypeService.findMaxSequence());
            i = articleTypeService.insert(articleType);
        }
        return i;
    }

    /**
     * 修改 某文章类型
     * @param articleType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateType",method = RequestMethod.POST)
    @ResponseBody
    public int updateArticleType(ArticleType articleType) throws Exception{
        System.out.println(articleType.getId());
        int i = articleTypeService.updateByPrimaryKey(articleType);
        return i;
    }

    /**
     * 修改文章类型的顺序
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateArticleSequenceNum",method = RequestMethod.POST)
    @ResponseBody
    public int updateArticleSequenceNum(HttpServletRequest request) throws Exception{
        String idStr = request.getParameter("id");
        String oldSequenceNum = request.getParameter("oldSequenceNum");
        String newSequenceNum = request.getParameter("newSequenceNum");
        Integer id = Integer.parseInt(idStr);
        int oldNum = Integer.parseInt(oldSequenceNum);
        int newNum = Integer.parseInt(newSequenceNum);
        if(oldNum == 1 || newNum == 1){
            return 0;
        }
        if(oldNum >= newNum){
            if(oldNum == newNum){
                return 0;
            }else{
                articleTypeService.updateSequenceNumByAddOne(oldNum,newNum);
                articleTypeService.updateSequenceNumById(newNum,id);
                return 1;
            }
        }else{
            articleTypeService.updateSequenceNumBySubOne(oldNum,newNum);
            articleTypeService.updateSequenceNumById(newNum,id);
            return 1;
        }
    }

    /**
     * 通过类型 删除
     * @param
     * @return Map
     * @throws Exception
     */
    @RequestMapping(value = "/deleteArticleType",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> deleteArticleType(HttpServletRequest request) throws Exception{
        HashMap<String, String> map = new HashMap<>(16);
        int i = checkRight(request);
        if (i==1){
            String type = request.getParameter("type");
            if(articleTypeService.selectArticleTypeByType(type) == null){
                map.put("msg","删除失败，数据库没有该类型!");
                logger.info("删除失败，数据库没有该类型!");
            }else{
                articleTypeService.deleteByType(type);
                logger.info("删除成功");
                map.put("msg","删除成功");
            }
        }else {
            logger.info("您不是超级管理员。");
            map.put("msg","您不是超级管理员");
        }
        return map;
    }

    /**
     * 对权限进行认证
     * 用以对删除与添加时的认证
     * @return
     */
    public int checkRight(HttpServletRequest request) throws Exception{
        //该参数用以获取当前用户的用户名
        String cur_username = (String)request.getSession().getAttribute("username");
        Manager manager = managerService.findManagerByUsername(cur_username);
        if (manager.getTitle().equals("AAAAA")){
            return 1;
        }
        return  0;
    }
}
