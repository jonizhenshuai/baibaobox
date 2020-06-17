package com.baibaoxiang.tool;

import java.text.SimpleDateFormat;

/**
 * @author sheng
 * @create 2019-05-27-20:23
 */
public class StringDateUtils {

    /** 将String 类型转换为 Date
     * @param dateString
     * @return
     * @throws Exception
     */
    public static java.sql.Date stringToDate(String dateString) throws Exception{
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = df.parse(dateString);
        //再将 java.util.Date 型 转换成 java.sql.Date型
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        return date1;
    }
}
