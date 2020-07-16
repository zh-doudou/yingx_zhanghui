package com.zhanghui.yx.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (YxUser)实体类
 *
 * @author makejava
 * @since 2020-07-01 11:40:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YxUser implements Serializable {
    private static final long serialVersionUID = -99240935346850312L;
    /**
     * 用户主键
     */
    @Excel(name = "id")
    private String id;
    /**
     * 用户名名字
     */
    @Excel(name = "名字")
    private String userName;
    /**
     * 用户简介
     */
    @Excel(name = "用户简介")
    private String brief;
    /**
     * 用户手机号
     */
    @Excel(name = "用户手机号")
    private String phone;
    /**
     * 用户头像
     */
    @Excel(name = "用户头像", type = 2)
    @ExcelEntity
    private String headImg;
    /**
     * 微信
     */
    @Excel(name = "微信")
    private String wechat;
    /**
     * 用户注册时间
     *
     * @DateTimeFormat ：接收格式设定
     * @JsonFormat ：响应格式设置
     */
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    // @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "注册时间", format = "yyyy年MM月dd日", width = 20)
    private Date createDate;
    @Excel(name = "用户状态")
    private String status;
    private String sex;
    private String city;
}