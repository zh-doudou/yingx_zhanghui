package com.zhanghui.yx.service.impl;

import com.zhanghui.yx.aspect.MyLog;
import com.zhanghui.yx.dao.YxGroupDao;
import com.zhanghui.yx.entity.YxGroup;
import com.zhanghui.yx.service.YxGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * (YxGroup)表服务实现类
 *
 * @author makejava
 * @since 2020-07-05 19:58:20
 */
@Service("yxGroupService")
@Transactional
@Slf4j
public class YxGroupServiceImpl implements YxGroupService {
    @Resource
    private YxGroupDao yxGroupDao;
    private Map<String, Object> map = new HashMap<>();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YxGroup queryById(String id) {
        return this.yxGroupDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param page 查询起始位置
     * @param rows 查询条数
     * @return 对象列表
     */
    @Override
    public Map<String, Object> queryAllByLimit(Integer rows, Integer page) {
        Map<String, Object> map = new HashMap<>();
        List<YxGroup> yxGroups = yxGroupDao.queryAllByLimit((page - 1) * rows, rows);
        int i = yxGroupDao.queryCounts();
        //设置总条数
        map.put("records", i);
        //设置当前页
        map.put("page", page);
        //设置数据
        map.put("rows", yxGroups);
        //设置总页数
        map.put("total", (int) Math.ceil(1.0 * rows));
        return map;
    }

    /**
     * 新增数据
     *
     * @param yxGroup 实例对象
     * @return 实例对象
     */
    @MyLog(value = "新增分组")
    @Override
    public Map<String, Object> insert(YxGroup yxGroup) {
        yxGroup.setId(UUID.randomUUID().toString().replace("-", ""));
        if (this.yxGroupDao.insert(yxGroup) > 0) {
            map = queryAllByLimit(5, 1);
            map.put("add", "SUCCESS");
        }
        return map;
    }

    /**
     * 修改数据
     *
     * @param yxGroup 实例对象
     * @return 实例对象
     */
    @MyLog(value = "修改分组信息")
    @Override
    public Map<String, Object> update(YxGroup yxGroup) {
        if (this.yxGroupDao.update(yxGroup) > 0) {
            map = queryAllByLimit(5, 1);
            map.put("update", "SUCCESS");
        } else {
            map.put("update", "error");
        }
        return map;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @MyLog(value = "删除分组")
    @Override
    public Map<String, Object> deleteById(String id) {
        if (this.yxGroupDao.deleteById(id) > 0) {
            map = queryAllByLimit(5, 1);
            map.put("del", "SUCCESS");
        } else {
            map.put("del", "error");
        }
        return map;
    }
}