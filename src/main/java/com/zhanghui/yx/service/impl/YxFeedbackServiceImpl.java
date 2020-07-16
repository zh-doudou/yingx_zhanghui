package com.zhanghui.yx.service.impl;

import com.zhanghui.yx.aspect.MyLog;
import com.zhanghui.yx.dao.YxFeedbackDao;
import com.zhanghui.yx.entity.YxFeedback;
import com.zhanghui.yx.service.YxFeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * (YxFeedback)表服务实现类
 *
 * @author makejava
 * @since 2020-07-02 09:45:49
 */
@Service("yxFeedbackService")
@Transactional
public class YxFeedbackServiceImpl implements YxFeedbackService {
    @Resource
    private YxFeedbackDao yxFeedbackDao;

    private Map<String, Object> map = new HashMap<>();

    /**
     * 查询多条数据
     *
     * @param rows 查询条数
     * @param page 起始页
     * @return 对象列表
     */
    @Override
    public Map<String, Object> queryAllByLimit(int rows, int page) {
        List<YxFeedback> yxFeedbacks = this.yxFeedbackDao.queryAllByLimit((page - 1) * rows, rows);
        int i = yxFeedbackDao.selectAllCount();
        //设置当前页
        map.put("page", page);
        //设置数据
        map.put("rows", yxFeedbacks);
        //设置总页数
        map.put("totalPage", (int) Math.ceil(1.0 * i / rows));
        return map;
    }

    /**
     * 新增数据
     *
     * @param yxFeedback 实例对象
     * @return 实例对象
     */
    @MyLog(value = "新增反馈")
    @Override
    public Map<String, Object> insert(YxFeedback yxFeedback) {
        yxFeedback.setId(UUID.randomUUID().toString().replace("-", ""));
        yxFeedback.setFeedbackDate(new Date());
        if (this.yxFeedbackDao.insert(yxFeedback) > 0) {
            map = queryAllByLimit(5, 1);
            map.put("add", "SUCCESS");
        } else {
            map.put("add", "error");
        }
        return map;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @MyLog(value ="通过id删除反馈")
    @Override
    public Map<String, Object> deleteById(String id) {
        if (this.yxFeedbackDao.deleteById(id) > 0) {
            map = queryAllByLimit(5, 1);
            map.put("del", "SUCCESS");
        } else {
            map.put("del", "error");
        }
        return map;
    }
}