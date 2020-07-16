package com.zhanghui.yx.service;

import com.zhanghui.yx.aliyunutil.AliYunVO;
import com.zhanghui.yx.entity.YxVideo;
import com.zhanghui.yx.po.VideoPO;

import java.util.List;
import java.util.Map;

/**
 * (YxVideo)表服务接口
 *
 * @author makejava
 * @since 2020-07-03 19:23:58
 */
public interface YxVideoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YxVideo queryById(String id);

    /**
     * 查询多条数据
     *
     * @param rows 查询条数
     * @param page 查询起始位置
     * @return 对象列表
     */
    Map<String, Object> queryAllByLimit(int rows, int page);

    /**
     * 新增数据
     *
     * @param yxVideo 实例对象
     * @return 实例对象
     */
    Map<String, Object> insert(YxVideo yxVideo);

    Map<String, Object> videoUpLoad(AliYunVO aliYunVO);

    /**
     * 修改数据
     *
     * @param yxVideo 实例对象
     * @return 实例对象
     */
    Map<String, Object> update(YxVideo yxVideo);

    /**
     * 通过主键删除数据
     *
     * @param aliYunVO 实例对象
     * @return 是否成功
     */
    Map<String, Object> deleteById(AliYunVO aliYunVO);

    List<VideoPO> queryByReleaseTime();

    List<VideoPO> queryCateVideoList(String cateId);

    VideoPO queryByVideoDetail(String videoId, String cateId, String userId);
}