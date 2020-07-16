package com.zhanghui.yx.service.impl;

import com.zhanghui.yx.aspect.MyLog;
import com.zhanghui.yx.dao.YxLogDao;
import com.zhanghui.yx.entity.YxLog;
import com.zhanghui.yx.service.YxLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (YxLog)表服务实现类
 *
 * @author makejava
 * @since 2020-07-07 16:41:29
 */
@Service("yxLogService")
public class YxLogServiceImpl implements YxLogService {
    @Resource
    private YxLogDao yxLogDao;
    private Map<String, Object> map = new HashMap<>();

    /**
     * 查询多条数据
     *
     * @param page 查询起始位置
     * @param rows 查询条数
     * @return 对象列表
     */
    @Override
    public Map<String, Object> queryAllByLimit(Integer rows, Integer page) {
        List<YxLog> yxLogs = this.yxLogDao.queryAllByLimit((page - 1) * rows, rows);
        map.put("page", page);
        map.put("rows", yxLogs);
        int i = yxLogDao.queryAllCounts();
        map.put("records", i);
        map.put("totalPage", (int) Math.ceil(1.0 * i / rows));
        return map;
    }

    /**
     * 新增数据
     *
     * @param yxLog 实例对象
     * @return 实例对象
     */
    @Override
    public YxLog insert(YxLog yxLog) {
        this.yxLogDao.insert(yxLog);
        return yxLog;
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @MyLog(value = "通过id删除行日志")
    @Override
    public Map<String, Object> deleteById(String id) {
        if (this.yxLogDao.deleteById(id) > 0) {
            map = queryAllByLimit(5, 1);
            map.put("del", "SUCCESS");
        }
        return map;
    }
}