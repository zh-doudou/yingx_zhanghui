package com.zhanghui.yx.controller;

import com.zhanghui.yx.entity.YxFeedback;
import com.zhanghui.yx.service.YxFeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (YxFeedback)表控制层
 *
 * @author makejava
 * @since 2020-07-02 09:45:49
 */
@RestController
@RequestMapping("yxFeedback")
@Slf4j
public class YxFeedbackController {
    /**
     * 服务对象
     */
    @Resource
    private YxFeedbackService yxFeedbackService;

    @RequestMapping("queryAll")
    public Map<String, Object> queryAll(Integer rows, Integer page) {
        Map<String, Object> map = yxFeedbackService.queryAllByLimit(rows, page);

        return map;
    }

    /**
     * 添加新反馈
     */
    @RequestMapping("addFeedBack")
    public Map<String, Object> addFeedBack(YxFeedback yxFeedback) {
        log.info("添加{}", yxFeedback);

        Map<String, Object> map = yxFeedbackService.insert(yxFeedback);
        return map;
    }

    @RequestMapping("delById")
    public Map<String, Object> delById(String id) {
        Map<String, Object> map = yxFeedbackService.deleteById(id);
        return map;
    }

}