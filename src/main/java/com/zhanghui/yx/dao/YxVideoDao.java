package com.zhanghui.yx.dao;

import com.zhanghui.yx.entity.YxVideo;
import com.zhanghui.yx.po.VideoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (YxVideo)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-03 17:08:00
 */
public interface YxVideoDao<queryByIdAndUserIdAndCategoryId> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YxVideo queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YxVideo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param yxVideo 实例对象
     * @return 对象列表
     */
    List<YxVideo> queryAll(YxVideo yxVideo);

    /**
     * 新增数据
     *
     * @param yxVideo 实例对象
     * @return 影响行数
     */
    int insert(YxVideo yxVideo);

    /**
     * 修改数据
     *
     * @param yxVideo 实例对象
     * @return 影响行数
     */
    int update(YxVideo yxVideo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 查询所有条数
     */
    int selectAllCount();

    List<YxVideo> queryByAllVideoS();


    List<VideoPO> queryByReleaseTime();

    List<VideoPO> queryAllByCategoryId(@Param("categoryId") String categoryId);

    VideoPO queryAllByIdAndCategoryIdAndUserId(@Param("id") String id, @Param("categoryId") String categoryId, @Param("userId") String userId);


}