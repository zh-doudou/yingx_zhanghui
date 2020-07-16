package com.zhanghui.yx.service;

import com.zhanghui.yx.entity.YxFeedback;

import java.util.Map;

/**
 * (YxFeedback)表服务接口
 *
 * @author makejava
 * @since 2020-07-02 09:45:49
 */
public interface YxFeedbackService {


    /**
     * 查询多条数据
     *
     * @param rows 查询条数
     * @param page 起始页
     * @return 对象列表
     */
    Map<String, Object> queryAllByLimit(int rows, int page);

    /**
     * 新增数据
     *
     * @param yxFeedback 实例对象
     * @return 实例对象
     */
    Map<String, Object> insert(YxFeedback yxFeedback);


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Map<String, Object> deleteById(String id);

}