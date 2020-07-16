package com.zhanghui.yx.controller;

import com.zhanghui.yx.entity.YxGroup;
import com.zhanghui.yx.service.YxGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (YxGroup)表控制层
 *
 * @author makejava
 * @since 2020-07-05 19:58:20
 */
@RestController
@RequestMapping("yxGroup")
@Slf4j
public class YxGroupController {
    /**
     * 服务对象
     */
    @Resource
    private YxGroupService yxGroupService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("selectOne")
    public YxGroup selectOne(String id) {
        return this.yxGroupService.queryById(id);
    }

    @RequestMapping("queryAll")
    public Map<String, Object> queryAll(Integer rows, Integer page) {
        log.info("rows:{}", rows);
        log.info("page:{}", page);
        Map<String, Object> map = yxGroupService.queryAllByLimit(rows, page);
        return map;
    }

    /**
     * 添加数据
     *
     * @param yxGroup 实例对象
     */
    @RequestMapping("addGroup")
    public Map<String, Object> addGroup(YxGroup yxGroup) {
        Map<String, Object> map = yxGroupService.insert(yxGroup);
        return map;

    }

    @RequestMapping("updateGroup")
    public Map<String, Object> updateGroup(YxGroup yxGroup) {
        Map<String, Object> map = yxGroupService.update(yxGroup);
        return map;
    }

    @RequestMapping("delGroupById")
    public Map<String, Object> delGroupById(String id) {
        Map<String, Object> map = yxGroupService.deleteById(id);
        return map;
    }
}