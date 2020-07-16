package com.zhanghui.yx.controller;

import com.zhanghui.yx.entity.YxCategory;
import com.zhanghui.yx.service.YxCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * (YxCategory)表控制层
 *
 * @author makejava
 * @since 2020-06-30 20:41:15
 */
@RestController
@RequestMapping("yxCategory")
@Slf4j
public class YxCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private YxCategoryService yxCategoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public YxCategory selectOne(String id) {
        return this.yxCategoryService.queryById(id);
    }

    /**
     * 查询所有类别
     */
    @RequestMapping("queryAllClass")
    public Map<String, Object> queryAllClass(Integer rows, Integer page) {
        log.info("查询数据:{}", rows);
        log.info("查询数据:{}", page);
        Map<String, Object> map = yxCategoryService.selectAllClass(rows, page);
        return map;
    }

    /**
     * 添加1级/2级类别
     *
     * @param yxCategory 实例对象
     * @return 自定义查询数据map
     */
    @RequestMapping("insert")
    public Map<String, Object> insert(YxCategory yxCategory) {
        yxCategory.setId(UUID.randomUUID().toString().replace("-", ""));
        Map<String, Object> map = yxCategoryService.insert(yxCategory);
        log.info("添加类别为：{}", yxCategory);
        return map;
    }

    @RequestMapping("deleteById")
    public Map<String, Object> deleteById(String id) {
        Map<String, Object> map = yxCategoryService.deleteById(id);
        return map;
    }
}