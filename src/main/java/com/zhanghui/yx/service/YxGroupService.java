package com.zhanghui.yx.service;

import com.zhanghui.yx.entity.YxGroup;

import java.util.Map;

/**
 * (YxGroup)表服务接口
 *
 * @author makejava
 * @since 2020-07-05 19:58:20
 */
public interface YxGroupService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YxGroup queryById(String id);

    /**
     * 查询多条数据
     *
     * @param page 查询起始位置
     * @param rows 查询条数
     * @return 对象列表
     */
    Map<String, Object> queryAllByLimit(Integer rows, Integer page);

    /**
     * 新增数据
     *
     * @param yxGroup 实例对象
     * @return 实例对象
     */
    Map<String, Object> insert(YxGroup yxGroup);

    /**
     * 修改数据
     *
     * @param yxGroup 实例对象
     * @return 实例对象
     */
    Map<String, Object> update(YxGroup yxGroup);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Map<String, Object> deleteById(String id);

}