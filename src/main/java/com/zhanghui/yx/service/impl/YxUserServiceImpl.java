package com.zhanghui.yx.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.zhanghui.yx.aliyunutil.ALiYunOssUtil;
import com.zhanghui.yx.aliyunutil.AliYunVO;
import com.zhanghui.yx.aspect.AddCache;
import com.zhanghui.yx.aspect.DelCache;
import com.zhanghui.yx.aspect.MyLog;
import com.zhanghui.yx.aspect.MyPush;
import com.zhanghui.yx.dao.YxProvinceDao;
import com.zhanghui.yx.dao.YxUserDao;
import com.zhanghui.yx.eChartsVO.*;
import com.zhanghui.yx.entity.YxProvince;
import com.zhanghui.yx.entity.YxUser;
import com.zhanghui.yx.service.YxUserService;
import com.zhanghui.yx.util.MonthToCountsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * (YxUser)表服务实现类
 *
 * @author makejava
 * @since 2020-07-01 11:40:05
 */
@Service("yxUserService")
@Transactional
@Slf4j
public class YxUserServiceImpl implements YxUserService {
    @Resource
    private YxUserDao yxUserDao;

    private Map<String, Object> map = null;
    @Resource
    HttpSession session;
    @Resource
    private YxProvinceDao yxProvinceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YxUser queryById(String id) {
        return this.yxUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param rows 每页展示条数
     * @param page 当前页数
     * @return 对象列表
     */
    @AddCache
    @Override
    public Map<String, Object> queryAllByLimit(int rows, int page) {
        /**
         * 当前页数
         * */
        HashMap<String, Object> map = new HashMap<>();
        List<YxUser> yxUsers = yxUserDao.queryAllByLimit((page - 1) * rows, rows);
        List<YxProvince> citys = yxProvinceDao.queryAll();
        //设置所有城市
        map.put("citys", citys);
        //设置数据
        map.put("rows", yxUsers);
        //设置当前页数
        map.put("page", page);
        int i = yxUserDao.selectIdCount();
        //设置总页数
        map.put("totalpage", (int) Math.ceil(1.0 * i / rows));
        return map;
    }

    /**
     * 新增数据
     *
     * @param yxUser 实例对象
     * @return 实例对象
     */
    @DelCache
    @MyPush(value = "添加用户后进行推送")
    @MyLog(value = "新增数据")
    @Override
    public Map<String, Object> insert(YxUser yxUser) {
        HashMap<String, Object> map = new HashMap<>();
        String replace = UUID.randomUUID().toString().replace("-", "");
        yxUser.setId(replace);
        yxUser.setStatus("冻结");
        yxUser.setCreateDate(new Date());
        if (this.yxUserDao.insert(yxUser) > 0) {
            map.put("add", "ok");
            map.put("id", replace);
        } else {
            map.put("add", "error");
        }

        return map;
    }

    /**
     * 文件上传
     */
    @DelCache
    @MyLog(value = "文件上传")
    @Override
    public Map<String, Object> upLode(AliYunVO aliYunVO) {
        String cccc = ALiYunOssUtil.UpLoadByByte(aliYunVO, "cccc");
        log.info("文件上最新网络地址为：{}", cccc);
        if (cccc != null) {
            YxUser yxUser = new YxUser();
            yxUser.setId(aliYunVO.getId());
            yxUser.setHeadImg(cccc);
            yxUser.setStatus("激活");
            log.info("执行修改之前用户信息为：{}", yxUser);
            if (this.yxUserDao.update(yxUser) > 0) {
                //执行查询所有
                map = queryAllByLimit(5, 1);
                map.put("update", "ok");
            } else {
                map.put("update", "error");
            }
        } else {
            map.put("update", "error");
        }
        return map;
    }

    /**
     * 修改数据
     *
     * @param yxUser 实例对象
     * @return 实例对象
     */
    @DelCache
    @MyLog(value = "根据id修改用户信息")
    @Override
    public Map<String, Object> update(YxUser yxUser) {
        if (this.yxUserDao.update(yxUser) > 0) {
            //执行查询所有
            map = queryAllByLimit(5, 1);
            map.put("update", "ok");
        }
        map.put("update", "error");
        return map;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DelCache
    @MyLog(value = "根据id删除用户信息")
    @Override
    public Map<String, Object> deleteById(AliYunVO aliYunVO, String id) {
        YxUser yxUser = yxUserDao.queryById(id);
        log.info("查询得到的用户为：{}", yxUser);
        if (this.yxUserDao.deleteById(id) > 0) {
            //执行查询所有
            map = queryAllByLimit(5, 1);
            map.put("del", "ok");
            ALiYunOssUtil.deleteFile(aliYunVO, yxUser.getHeadImg());
        } else {
            map.put("del", "error");
        }
        return map;
    }

    /**
     * 数据导出
     */
    @Override
    public Map<String, Object> derivedInformation(AliYunVO aliYunVO, String drive, String folderLocation) throws IOException {
        //根据相对路径获取绝对路径
        String realPath = drive + ":/" + folderLocation;
        File file = new File(realPath);
        //判断文件夹是否存在
        if (!file.exists()) {
            //创建一个
            file.mkdirs();
        }
        List<YxUser> yxUsers = yxUserDao.queryAllByLimit(0, 10);
        //遍历数据
        for (YxUser yxUser : yxUsers) {
            String headImg = yxUser.getHeadImg();
            //根据网络地址下载到本地
            String s = ALiYunOssUtil.DownloadLocal(aliYunVO, headImg);
            //从本地重新上传
            yxUser.setHeadImg(s);
        }
        //log.info("查询数据为：{}", yxUsers);
        //随机数
        int i = new Random().nextInt(100);
        ExportParams exportParams = new ExportParams("用户信息表", "用户");
        String newname = "用户信息表" + (i + 1) + ".xls";
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, YxUser.class, yxUsers);
        workbook.write(new FileOutputStream(new File(file, newname)));
        String newPath = file + "\\" + newname;
        workbook.close();
        map = new HashMap<>();
        map.put("path", newPath);
        map.put("down", "SUCCESS");
        return map;
    }


    /**
     * 根据性别查询
     */
    @Override
    public ArrayList<SexVO> selectAllBySexGroupByCity() {
        SexVO sexVO = new SexVO();
        List<CityVO> boyCity = yxUserDao.selectAllBySexGroupByCity("男");
        List<CityVO> girlCity = yxUserDao.selectAllBySexGroupByCity("女");
        ArrayList<SexVO> sexVOlist = new ArrayList<>();
        sexVOlist.add(new SexVO("小男孩", boyCity));
        sexVOlist.add(new SexVO("小女孩", girlCity));
        return sexVOlist;
    }

    @Override
    public List<PushStatVO> selectAllBySexAndCreateDate() {
        List<MonthAndCountVO> boylist = yxUserDao.selectAllBySexAndCreateDate("男");
        List<Integer> boyMonthCounts = MonthToCountsUtil.getMonthCounts(boylist);
        List<MonthAndCountVO> girllist = yxUserDao.selectAllBySexAndCreateDate("女");
        List<Integer> girlMonthCounts = MonthToCountsUtil.getMonthCounts(girllist);
        List<String> allmonth1 = new StatisticsVO().getAllmonth();
        List<PushStatVO> lists = new ArrayList<>();
        lists.add(new PushStatVO("男孩", allmonth1, boyMonthCounts));
        lists.add(new PushStatVO("女孩", allmonth1, girlMonthCounts));
        return lists;
    }
}