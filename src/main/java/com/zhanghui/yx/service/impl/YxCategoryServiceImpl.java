package com.zhanghui.yx.service.impl;

import com.zhanghui.yx.aspect.MyLog;
import com.zhanghui.yx.dao.YxCategoryDao;
import com.zhanghui.yx.entity.YxCategory;
import com.zhanghui.yx.po.CateGoryPO;
import com.zhanghui.yx.service.YxCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (YxCategory)表服务实现类
 *
 * @author makejava
 * @since 2020-06-30 20:41:15
 */
@Service("yxCategoryService")
@Transactional
@Slf4j
public class YxCategoryServiceImpl implements YxCategoryService {
    @Resource
    private YxCategoryDao yxCategoryDao;
    private Map<String, Object> map = new HashMap<>();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YxCategory queryById(String id) {
        return this.yxCategoryDao.queryById(id);
    }


    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override

    public Map<String, Object> selectAllClass(Integer rows, Integer page) {
        Map<String, Object> map = new HashMap<>();
        List<YxCategory> allClass = yxCategoryDao.queryAllClassByPage();
        //List<YxCategory> yxCategories = yxCategoryDao.queryAllClass((page - 1) * rows, rows);
        //查询所有1级类别用于2级类别数据添加
        List<YxCategory> oneClass = yxCategoryDao.queryAllByLevelS("1");
        map.put("allClass", allClass);
        map.put("oneClass", oneClass);
        log.info("分页查询数据----{}", allClass);
        //设置数据
        // map.put("rows", yxCategories);
        //设置页数
        map.put("page", page);
        //设置总条数
        int i = yxCategoryDao.queryCountS();
        map.put("records", i);
        //设置总条数
        map.put("total", (int) Math.ceil(1.0 * i / rows));
        return map;
    }

    /**
     * 新增数据
     *
     * @param yxCategory 实例对象
     * @return 实例对象
     */
    @MyLog(value = "新增数据")
    @Override
    public Map<String, Object> insert(YxCategory yxCategory) {
        if (this.yxCategoryDao.insert(yxCategory) > 0) {
            map = selectAllClass(20, 1);
            map.put("add", "success");
        } else {
            map.put("add", "error");
        }
        return map;
    }

    /**
     * 修改数据
     *
     * @param yxCategory 实例对象
     * @return 实例对象
     */
    @MyLog(value = "根据id修改数据")
    @Override
    public YxCategory update(YxCategory yxCategory) {
        this.yxCategoryDao.update(yxCategory);
        return this.queryById(yxCategory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @MyLog(value = "根据id删除行数据")
    @Override
    @RequestMapping("deleteById")
    public Map<String, Object> deleteById(String id) {
        if (this.yxCategoryDao.deleteById(id) > 0) {
            map = selectAllClass(20, 1);
            map.put("del", "SUCCESS");
        }
        return map;
    }

    @Override
    public List<CateGoryPO> queryAllCategory() {
        List<CateGoryPO> yxCategories = yxCategoryDao.queryAllCategory();
        return yxCategories;
    }


    /**
     * 查询12级类别
     */

}