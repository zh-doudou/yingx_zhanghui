package com.zhanghui.yx.service;

import com.zhanghui.yx.entity.YxCategory;
import com.zhanghui.yx.po.CateGoryPO;

import java.util.List;
import java.util.Map;

/**
 * (YxCategory)表服务接口
 *
 * @author makejava
 * @since 2020-06-30 20:41:15
 */
public interface YxCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YxCategory queryById(String id);


    /**
     * 查询多条数据
     *
     * @param rows 查询条数
     * @param page 当前页
     * @return 对象列表
     */
    Map<String, Object> selectAllClass(Integer rows, Integer page);

    /**
     * 新增数据
     *
     * @param yxCategory 实例对象
     * @return 实例对象
     */
    Map<String, Object> insert(YxCategory yxCategory);

    /**
     * 修改数据
     *
     * @param yxCategory 实例对象
     * @return 实例对象
     */
    YxCategory update(YxCategory yxCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Map<String, Object> deleteById(String id);

    List<CateGoryPO> queryAllCategory();

}