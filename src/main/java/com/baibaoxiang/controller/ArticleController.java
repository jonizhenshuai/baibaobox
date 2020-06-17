package com.baibaoxiang.controller;

import com.baibaoxiang.po.Article;
import com.baibaoxiang.po.Manager;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.service.ManagerService;
import com.baibaoxiang.service.RedisService;
import com.baibaoxiang.tool.FastDfsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * @author sheng
 * @create 2019-04-26-09:59
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @Autowired
    ManagerService managerService;
    @Autowired
    RedisService redisService;

    @Autowired
    FastDfsClient fastDfsClient;

    private File file;

    private final static Logger logger = LoggerFactory.getLogger(ArticleController.class);

    /**
     * 按主键查询文章
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Article selectByPrimaryKey(@PathVariable("id") String id) throws Exception{
        Article article = articleService.selectByPrimaryKey(id);
        redisService.saveReadNumRedis(id);
        return article;
    }

    /**
     * 按地区，类型查询 并按置顶号，发布时间排序 （手机前端应用）
     *
     * @param request
     * @return
     * @throws Exception
     */
    /*@RequestMapping(value = "/type_area",method = RequestMethod.POST)
    public  List<Article> selectByTypeArea(HttpServletRequest request) throws Exception {
        String type = request.getParameter("type");
        String area = request.getParameter("area");
        List<Article> articleList = articleService.selectByTypeArea(type, area);
        return articleList;
    }*/

    /** 按地区，类型查询 并按置顶号，发布时间排序(后台地区管理员应用)
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/type",method = RequestMethod.POST)
    @ResponseBody
    public  List<Article> selectByType(HttpServletRequest request) throws Exception {
        String type = request.getParameter("type");
        Integer typeNo = Integer.valueOf(type);
        //获取session 中的username
        String username = (String)request.getSession().getAttribute("username");
        int isCheck = checkRight(request);

        if(isCheck==1){
            List<Article> articles = articleService.selectByType(typeNo);
            return articles;
        }else {
            Manager manager = managerService.findManagerByUsername(username);
            Integer areaNo = manager.getArea().getNo();
            List<Article> articleList = articleService.selectByTypeArea(typeNo,areaNo);
            for (Article article:articleList){
                System.out.println(article.getTitle());
            }
            return articleList;
        }

    }

    /** 查询所有的文章
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/allArticles",method = RequestMethod.GET)
    @ResponseBody
    public List<Article> selectAll() throws Exception{
        List<Article> articleList = articleService.selectAllArticles();
        return articleList;
    }

    /**
     * 添加文章 ,@RequestParam String type
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> insert(@RequestBody Article record) throws Exception {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        record.setNo(uuid);

        Map<String, String> map = new HashMap<>(16);
        try {
            articleService.insertSelective(record);
            map.put("msg","发布成功");
        }catch (Exception e){
            logger.error("异常抛出exception 文章发布失败。 ", e);
            map.put("msg","发布失败");
        }
        return map;
    }

    /**
     * 删除文章
     * @param no
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{no}",method = RequestMethod.DELETE)
    public void deleteByPrimaryKey(@PathVariable("no") String no) throws Exception {
        articleService.deleteByPrimaryKey(no);
    }

    /** 批量删除文章
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/deleteBatch",method = RequestMethod.POST)
    public void deleteArticleBatch(HttpServletRequest request) throws Exception{
        String str = request.getParameter("ids");
        String []ids= str.split(",");
        articleService.deleteArticleBatch(ids);
    }

    /**
     * 更新文章
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateArticle",method = RequestMethod.POST)
    @ResponseBody
    public int updateByPrimaryKey(@RequestBody Article record) {
        try {
            articleService.updateByPrimaryKey(record);
            return 1;
        }catch (Exception e){
            logger.error("文章更新异常： " + e);
        }
        return 0;
    }

    /** 点赞行为
     * @param no
     * @throws Exception
     */
    @RequestMapping(value = "/like/{no}", method = RequestMethod.GET)
    public void onclickLike(@PathVariable("no") String no) throws Exception{
        redisService.saveLikeNumRedis(no);
    }


    @RequestMapping(value="/setTop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> setTopArticle(HttpServletRequest request) throws Exception{
        String no = request.getParameter("no");
        String topStr = request.getParameter("top");
        Map map = new HashMap();
        Integer top = Integer.valueOf(topStr);
        articleService.setTopArticle(no,top);
        map.put("msg","修改成功");
        logger.info("推文置顶设置成功");
        return map;
    }

    /**
     *  这样接收文件@RequestParam Map<String,String> params,
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadArticleImg", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadArticleImg(@RequestParam MultipartFile file,
                                               HttpServletRequest request) throws Exception {
        // 文件类型
        String type = null;
        String uploadFilePath ="";
        StringBuffer picUrl = new StringBuffer();
        Map<String,Object> map=new HashMap<String, Object>(16);
        try{
            if (file!=null){
                // 文件原名称
                String fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
                //判断文件类型是否为空
                if (type!=null){
                    if("PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())){
                        uploadFilePath = fastDfsClient.uploadFile(bytes, type);
                        picUrl.append("http://");
                        picUrl.append("47.107.42.150/");
                        picUrl.append( uploadFilePath);
                        map.put("link",picUrl);
                    }else {
                        map.put("msg","上传失败，文件必须是jpg类型或者是PNG类型!");
                        logger.info("上传失败，文件必须是jpg类型或者是PNG类型!");
                    }
                }
            }
        }catch(Exception e){
            logger.error("图片上传异常：" + e);
            map.put("msg","上传失败，请重新上传");
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



