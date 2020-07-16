package com.zhanghui.yx.dao;

import com.zhanghui.yx.eChartsVO.CityVO;
import com.zhanghui.yx.eChartsVO.MonthAndCountVO;
import com.zhanghui.yx.entity.YxUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (YxUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-07-01 11:40:05
 */
public interface YxUserDao {


    /**
     * 查询所有条数
     *
     * @return 总条数
     */
    int selectIdCount();


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YxUser queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<YxUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param yxUser 实例对象
     * @return 对象列表
     */
    List<YxUser> queryAll(YxUser yxUser);

    /**
     * 新增数据
     *
     * @param yxUser 实例对象
     * @return 影响行数
     */
    int insert(YxUser yxUser);

    /**
     * 修改数据
     *
     * @param yxUser 实例对象
     * @return 影响行数
     */
    int update(YxUser yxUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);


    /**
     * 根据月份进行查询
     **/
    List<CityVO> selectAllBySexGroupByCity(@Param("sex") String sex);

    /**
     * 柱状图用户统计
     */
    List<MonthAndCountVO> selectAllBySexAndCreateDate(@Param("sex") String sex);
}