package com.zhanghui.yx.dao;

import com.zhanghui.yx.entity.YxProvince;

import java.util.List;

/**
 * (YxProvince)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-11 19:43:16
 */
public interface YxProvinceDao {


    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<YxProvince> queryAll();


}