package com.baibaoxiang.controller;

import com.baibaoxiang.po.Area;
import com.baibaoxiang.po.Manager;
import com.baibaoxiang.service.AreaService;
import com.baibaoxiang.service.ManagerService;
import com.baibaoxiang.service.SchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @create 2019-07-03-17:47
 */
@Controller(value = "area")
public class AreaController {

    private String defSchoolName = "广东第二师范学院";
    @Autowired
    AreaService areaService;
    @Autowired
    ManagerService managerService;
    @Autowired
    SchoolService schoolService;

    private final static Logger logger = LoggerFactory.getLogger(SchoolController.class);

    /** 查询某校名的 所有校区
     *
     * @return
     * @throws Exception
     */
    @RequestMapping( value = "/findAreaBySchoolName" ,method = RequestMethod.POST)
    @ResponseBody
    public List<Area> findAreaBySchoolName(HttpServletRequest request) throws Exception {
        String name = request.getParameter("name");
        List<Area> areaBySchoolName = areaService.findAreaBySchoolName(name);
        return areaBySchoolName;
    }

    /** 通过外键 查询校区
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping( value = "/{id}" ,method = RequestMethod.GET)
    @ResponseBody
    public Area findAreaById(@PathVariable("id") Integer id) throws Exception {
        Area area = areaService.findAreaById(id);
        return area;
    }

    /** 查询所有的校区
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/allarea", method = RequestMethod.GET)
    @ResponseBody
    public List<Area> findAllArea() throws Exception{
        List<Area> areas = areaService.findAllAreas();
        return areas;
    }
    /** 修改Area信息
     * @param area
     * @throws Exception
     */
    @RequestMapping(value = "updateArea", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> updateArea(@RequestBody Area area,HttpServletRequest request) throws Exception {
        //该参数判断当前是否超级管理员
        int isCheck = checkRight(request);
        Map<String,String> map = new HashMap<>();
        if (isCheck==1){
            areaService.updateArea(area);
            map.put("msg","修改成功");
            logger.info("修改成功。");
            return map;
        }
        map.put("msg","权限不足");
        logger.info("权限不足");
        areaService.updateArea(area);
        return map;
    }

    /** 通过id 批量删除area
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/deleteAreaBatch", method = RequestMethod.POST)
    public Map<String,String> deleteSchoolBatch(HttpServletRequest request) throws Exception{
        Map<String,String> map = new HashMap<String,String>(16);
        //该参数判断当前是否超级管理员
        int isCheck = checkRight(request);
        String idstr = request.getParameter("ids");
        Integer id = Integer.parseInt(idstr);
        List<Area> areas = areaService.findAreaBySchoolName(defSchoolName);
        if (isCheck==1){
            //接受前端传来的 ids字符串  将ids拆分成数组
            String str = request.getParameter("ids");
            //将ids拆分成数组
            String arr[] = str.split(",");
            Integer ids [] = new Integer[arr.length];
            for(int i = 0; i < ids.length; i++){
                ids[i] = Integer.valueOf(arr[i]);
                for(Area area : areas){
                    if(area.getNo().equals(ids[i])){
                        map.put("msg","广东第二师范学院为默认保留学校，不可删除");
                        logger.info("广东第二师范学院为默认保留学校，不可删除");
                        return map;
                    }
                }
            }
            areaService.deleteAreaBatch(ids);
            map.put("msg", "删除成功");
            logger.info("删除成功");
        }
        return map;

    }

    /** 添加校区
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addArea", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> addArea(HttpServletRequest request) throws Exception{
        //该参数判断当前是否超级管理员
        int isCheck = checkRight(request);
        Map<String,String> map = new HashMap<>();

        String schoolName = request.getParameter("schoolName");
        String areaName = request.getParameter("areaName");
        Integer schoolno = schoolService.selectNoBySchoolName(schoolName);
        Integer maxAreaNo = areaService.findMaxAreaNo();
        areaService.insertArea(maxAreaNo.intValue()+1,schoolno,areaName);
        map.put("msg","添加成功");
        logger.info("学校添加成功。");
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
