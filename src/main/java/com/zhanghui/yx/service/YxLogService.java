package com.zhanghui.yx.service;

import com.zhanghui.yx.entity.YxLog;

import java.util.Map;

/**
 * (YxLog)表服务接口
 *
 * @author makejava
 * @since 2020-07-07 16:41:29
 */
public interface YxLogService {


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
     * @param yxLog 实例对象
     * @return 实例对象
     */
    YxLog insert(YxLog yxLog);


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Map<String, Object> deleteById(String id);

}