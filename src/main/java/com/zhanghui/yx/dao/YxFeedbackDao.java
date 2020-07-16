package com.zhanghui.yx.dao;

import com.zhanghui.yx.entity.YxFeedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (YxFeedback)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-02 09:45:49
 */
public interface YxFeedbackDao {


    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YxFeedback> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param yxFeedback 实例对象
     * @return 影响行数
     */
    int insert(YxFeedback yxFeedback);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 查询总条数
     */
    int selectAllCount();


    /**
     * 根据id进行修改
     * */


}