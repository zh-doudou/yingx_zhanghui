package com.zhanghui.yx.dao;

import com.zhanghui.yx.entity.YxLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (YxLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-07 16:41:29
 */
public interface YxLogDao {


    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YxLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 新增数据
     *
     * @param yxLog 实例对象
     * @return 影响行数
     */
    int insert(YxLog yxLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    int queryAllCounts();
}