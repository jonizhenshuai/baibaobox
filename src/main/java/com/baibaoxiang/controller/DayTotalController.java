package com.baibaoxiang.controller;

import com.baibaoxiang.po.ReadLikeNumber;
import com.baibaoxiang.service.DayTotalService;
import com.baibaoxiang.tool.StringDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * @author sheng
 * @create 2019-05-10-09:49
 */
@Controller
@RequestMapping("/dayTotal")
public class DayTotalController {

    @Autowired
    DayTotalService dayTotalService;

    /** 更新 某篇推文 今天的阅读量
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/updateReadNum", method = RequestMethod.POST)
    public void updateReadNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //将请求的 推文编号和阅读量 String转换成Integer
        String stringNum = request.getParameter("num");
        Integer num = Integer.parseInt(stringNum);
        String no = request.getParameter("no");
        //date 自动获取 java.util.Date;
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());

        dayTotalService.updateReadNum(date1,num,no);
    }

    /** 更新 某篇推文 今天的点赞量
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/updateLikeNum", method = RequestMethod.POST)
    public void updateLikeNum(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //将请求的 推文编号和阅读量 String转换成Integer
        String stringNum = request.getParameter("num");
        Integer num = Integer.parseInt(stringNum);
        String no = request.getParameter("no");
        //date 自动获取
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        // java.sql.Date 转 java.sql.Date
        dayTotalService.updateLikeNum(date1,num,no);

    }


    //查询
    /** 网站一天的总点赞数和阅读量 （超级管理员使用）
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/all",method = RequestMethod.POST)
    @ResponseBody
    public ReadLikeNumber dayTotal(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //将字符串形式的日期 转换为 java.util.Date 型 再转成j ava.sql.Date
        String dateString = request.getParameter("time");
        java.sql.Date date = StringDateUtils.stringToDate(dateString);
        ReadLikeNumber readLikeNumber = dayTotalService.dayTotal(date);
        return readLikeNumber;
    }

    /** 某校区的 某天的总浏览量和点赞数（超级管理员和该地区管理员使用)
     * @return
     * @throws Exception
     *
     */
    @RequestMapping(value = "/area" ,method = RequestMethod.POST)
    @ResponseBody
    public ReadLikeNumber dayTotalArea(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //将字符串形式的日期 转换为 java.util.Date 型 再转成j ava.sql.Date
        String dateString = request.getParameter("time");
        java.sql.Date date = StringDateUtils.stringToDate(dateString);
        String area = request.getParameter("area");
        ReadLikeNumber readLikeNumber = dayTotalService.dayTotalArea(date, area);
        return readLikeNumber;
    }

    /** 某类型的所有推文 某天的总浏览量和点赞数（超级管理员使用）
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/type" ,method = RequestMethod.POST)
    @ResponseBody
    public ReadLikeNumber dayTotalType(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //将字符串形式的日期 转换为 java.util.Date 型 再转成j ava.sql.Date
        String dateString = request.getParameter("time");
        java.sql.Date date = StringDateUtils.stringToDate(dateString);
        String type = request.getParameter("type");
        ReadLikeNumber readLikeNumber = dayTotalService.dayTotalType(date, type);
        return readLikeNumber;
    }

    /** 某校区的 某类型的所有推文 某天的总浏览量和点赞数（超级管理员和该地区管理员使用）
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/typeArea",method = RequestMethod.POST)
    @ResponseBody
    public ReadLikeNumber dayTotalTypeArea(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //将字符串形式的日期 转换为 java.util.Date 型 再转成j ava.sql.Date
        String dateString = request.getParameter("time");
        java.sql.Date date = StringDateUtils.stringToDate(dateString);
        String type = request.getParameter("type");
        String area = request.getParameter("area");
        ReadLikeNumber readLikeNumber = dayTotalService.dayTotalTypeArea(date, area, type);
        return readLikeNumber;
    }


    /** 某篇推文 某天的点赞数
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/no", method = RequestMethod.POST)
    @ResponseBody
    public ReadLikeNumber dayTotalNo(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //将字符串形式的日期 转换为 java.util.Date 型 再转成j ava.sql.Date
        String dateString = request.getParameter("time");
        java.sql.Date date = StringDateUtils.stringToDate(dateString);
        String no = request.getParameter("no");
        ReadLikeNumber readLikeNumber = dayTotalService.dayTotalNo(date, no);
        return readLikeNumber;
    }



}
