package com.zhanghui.yx.dao;

import com.zhanghui.yx.entity.YxCategory;
import com.zhanghui.yx.po.CateGoryPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (YxCategory)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-30 20:41:15
 */
public interface YxCategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YxCategory queryById(String id);

    /**
     * 查询所有
     */
    List<YxCategory> queryAllClassByPage();

    /**
     * 级联查询指定行数据
     *
     * @param offset 起始
     * @param limit  结束
     * @return 对象列表
     */
    List<YxCategory> queryAllClass(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param levels 分类等级
     * @return 对象列表
     */
    List<YxCategory> queryAllByLevelS(String levels);

    /**
     * 新增数据
     *
     * @param yxCategory 实例对象
     * @return 影响行数
     */
    int insert(YxCategory yxCategory);

    /**
     * 修改数据
     *
     * @param yxCategory 实例对象
     * @return 影响行数
     */
    int update(YxCategory yxCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 查询所有条数
     *
     * @return 行数据
     */
    int queryCountS();

    /**
     *
     */
    List<CateGoryPO> queryAllCategory();


}