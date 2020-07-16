package com.zhanghui.yx.dao;

import com.zhanghui.yx.entity.YxGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (YxGroup)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-05 19:58:20
 */
public interface YxGroupDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YxGroup queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YxGroup> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param yxGroup 实例对象
     * @return 对象列表
     */
    List<YxGroup> queryAll(YxGroup yxGroup);

    /**
     * 新增数据
     *
     * @param yxGroup 实例对象
     * @return 影响行数
     */
    int insert(YxGroup yxGroup);

    /**
     * 修改数据
     *
     * @param yxGroup 实例对象
     * @return 影响行数
     */
    int update(YxGroup yxGroup);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    int queryCounts();

}