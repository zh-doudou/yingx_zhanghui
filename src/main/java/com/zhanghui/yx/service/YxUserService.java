package com.zhanghui.yx.service;

import com.zhanghui.yx.aliyunutil.AliYunVO;
import com.zhanghui.yx.eChartsVO.PushStatVO;
import com.zhanghui.yx.eChartsVO.SexVO;
import com.zhanghui.yx.entity.YxUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (YxUser)表服务接口
 *
 * @author makejava
 * @since 2020-07-01 11:40:05
 */
public interface YxUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YxUser queryById(String id);

    /**
     * 查询多条数据
     *
     * @param rows 展示条数
     * @param page 页数
     * @return map集合
     */
    Map<String, Object> queryAllByLimit(int rows, int page);

    /**
     * 新增数据
     *
     * @param yxUser 实例对象
     * @return 实例对象
     */
    Map<String, Object> insert(YxUser yxUser);

    /**
     * 文件上传
     *
     * @param headImg 文件名字
     * @param id      当前用户名字
     */

    Map<String, Object> upLode(AliYunVO aliYunVO);

    /**
     * 修改数据
     *
     * @param yxUser 实例对象
     * @return 实例对象
     */
    Map<String, Object> update(YxUser yxUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Map<String, Object> deleteById(AliYunVO aliYunVO, String id);

    Map<String, Object> derivedInformation(AliYunVO aliYunVO, String drive, String folderLocation) throws IOException;

    ArrayList<SexVO> selectAllBySexGroupByCity();

    List<PushStatVO> selectAllBySexAndCreateDate();
}