package com.zhanghui.yx.controller;

import com.zhanghui.yx.service.YxLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (YxLog)表控制层
 *
 * @author makejava
 * @since 2020-07-07 16:41:29
 */
@RestController
@RequestMapping("yxLog")
public class YxLogController {
    /**
     * 服务对象
     */
    @Resource
    private YxLogService yxLogService;

    @RequestMapping("queryAll")
    public Map<String, Object> queryAll(Integer rows, Integer page) {
        Map<String, Object> map = yxLogService.queryAllByLimit(rows, page);
        return map;
    }

    @RequestMapping("delById")
    public Map<String, Object> delById(String id) {
        Map<String, Object> map = yxLogService.deleteById(id);
        return map;
    }

}