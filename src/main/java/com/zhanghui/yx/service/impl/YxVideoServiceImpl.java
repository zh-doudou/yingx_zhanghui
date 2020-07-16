package com.zhanghui.yx.service.impl;

import com.zhanghui.yx.EsDao.VideoRepository;
import com.zhanghui.yx.aliyunutil.ALiYunOssUtil;
import com.zhanghui.yx.aliyunutil.AliYunVO;
import com.zhanghui.yx.aspect.AddCache;
import com.zhanghui.yx.aspect.DelCache;
import com.zhanghui.yx.aspect.MyLog;
import com.zhanghui.yx.dao.YxCategoryDao;
import com.zhanghui.yx.dao.YxVideoDao;
import com.zhanghui.yx.entity.YxCategory;
import com.zhanghui.yx.entity.YxVideo;
import com.zhanghui.yx.po.VideoPO;
import com.zhanghui.yx.service.YxVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * (YxVideo)表服务实现类
 *
 * @author makejava
 * @since 2020-07-03 19:23:58
 */
@Service("yxVideoService")
@Slf4j
@Transactional
public class YxVideoServiceImpl implements YxVideoService {
    @Resource
    private YxVideoDao yxVideoDao;
    @Resource
    YxCategoryDao yxCategoryDao;
    @Resource
    VideoRepository videoRepository;
    private Map<String, Object> map = new HashMap<>();


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @AddCache
    @Override
    public YxVideo queryById(String id) {
        return this.yxVideoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param rows 查询起始位置
     * @param page 查询条数
     * @return 对象列表
     */
    @AddCache
    @Override
    public Map<String, Object> queryAllByLimit(int rows, int page) {
        HashMap<String, Object> map = new HashMap<>();
        //查询所有2级类别
        List<YxCategory> yxCategories = yxCategoryDao.queryAllByLevelS("2");
        List<YxVideo> yxVideos = yxVideoDao.queryAllByLimit((page - 1) * rows, rows);
        map.put("twoClass", yxCategories);
        //设置当前页数
        map.put("page", page);
        //设置数据
        map.put("rows", yxVideos);
        //设置总条数
        int i = yxVideoDao.selectAllCount();
        map.put("records", i);
        //设置总页数
        map.put("total", (int) Math.ceil(1.0 * i / rows));
        return map;
    }

    /**
     * 新增数据
     *
     * @param yxVideo 实例对象
     * @return 实例对象
     */
    @DelCache
    @MyLog(value = "新增video数据")
    @Override
    public Map<String, Object> insert(YxVideo yxVideo) {
        HashMap<String, Object> map = new HashMap<>();
        //设置id
        String uuid = UUID.randomUUID().toString().replace("-", "");
        yxVideo.setId(uuid);
        //设置上传时间
        yxVideo.setVideoDate(new Date());
        if (this.yxVideoDao.insert(yxVideo) > 0) {
            map.put("add", "SUCCESS");
            map.put("newId", uuid);
        }
        return map;
    }

    /**
     * 文件上传
     **/
    @Override
    @DelCache
    @MyLog(value = "视频文件上传")
    public Map<String, Object> videoUpLoad(AliYunVO aliYunVO) {
        //1.根据id获取目前的地址信息
        YxVideo yxVideo1 = yxVideoDao.queryById(aliYunVO.getId());
        String oldVideoPath = yxVideo1.getVideoPath();
        String oldCoverPath = yxVideo1.getCoverPath();
        log.info("原来的截图地址:{}", oldCoverPath);
        if (oldVideoPath != null && oldCoverPath != null) {
            //根据地址删除再重新上传
            ALiYunOssUtil.deleteFile(aliYunVO, oldVideoPath);
            //删除此视频的截图
            ALiYunOssUtil.deleteFile(aliYunVO, oldCoverPath);
        }
        //2.进行文件上传返回网络地址
        String netAddress = ALiYunOssUtil.UpLoadByByte(aliYunVO, "video");
        //将视频i地址放入实体类
        aliYunVO.setNetAddress(netAddress);
        //调用截帧方法将截帧后图片网络地址返回
        String coverPath = ALiYunOssUtil.videoCapture(aliYunVO);
        //3.调用网络路径上传流 将网络地址图片上传到阿里云oss目录
        aliYunVO.setNetAddress(coverPath);
        //返回上传图片后路径
        String newCoverPath = ALiYunOssUtil.UplodeByNetPath(aliYunVO, "cover");
        //4.根据用户id查询用户信息,修改视频地址
        //YxVideo yxVideo = new YxVideo();
        YxVideo yxVideo = yxVideoDao.queryById(aliYunVO.getId());
        //设置用户修改的id
        //yxVideo.setId(aliYunVO.getId());
        //设置视频网络地址
        yxVideo.setVideoPath(netAddress);
        //.设置快照地址
        yxVideo.setCoverPath(newCoverPath);
        log.info("修改之后的结果：{}" + yxVideo);
        if (yxVideoDao.update(yxVideo) > 0) {
            //如果修改成功 就把这个对象更新到es检索库
            videoRepository.save(yxVideo);
            map = queryAllByLimit(2, 1);
            map.put("upLode", "SUCCESS");
        } else {
            map.put("upLode", "error");
        }
        return map;
    }

    /**
     * 修改数据
     *
     * @param yxVideo 实例对象
     * @return 实例对象
     */
    @DelCache
    @MyLog(value = "根据id修改信息")
    @Override
    public Map<String, Object> update(YxVideo yxVideo) {
        if (this.yxVideoDao.update(yxVideo) > 0) {
            //如果修改成功再根据id查询并更新索引
            videoRepository.save(yxVideo);
            videoRepository.save(yxVideoDao.queryById(yxVideo.getId()));
            map = queryAllByLimit(2, 1);
            map.put("update", "SUCCESS");
            map.put("id", yxVideo.getId());
        }
        return this.map;
    }

    /**
     * 通过主键删除数据
     *
     * @param aliYunVO 实例对象
     * @return 是否成功
     */
    @DelCache
    @MyLog(value = "根据id删除行数据·")
    @Override
    public Map<String, Object> deleteById(AliYunVO aliYunVO) {
        YxVideo yxVideo = yxVideoDao.queryById(aliYunVO.getId());
        ALiYunOssUtil.deleteFile(aliYunVO, yxVideo.getVideoPath());
        ALiYunOssUtil.deleteFile(aliYunVO, yxVideo.getCoverPath());
        if (this.yxVideoDao.deleteById(aliYunVO.getId()) > 0) {
            //如果删除成功es索引中根据id删除这条数据
            log.info("需要删除视频的id：{}", aliYunVO.getId());
            videoRepository.deleteById(aliYunVO.getId());
            map = queryAllByLimit(2, 1);
            map.put("del", "SUCCESS");
        } else {
            map.put("del", "error");
        }
        return map;
    }

    /**
     * 查询所有视频-类别-用户
     **/
    @AddCache
    @Override
    public List<VideoPO> queryByReleaseTime() {
        List<VideoPO> videoPOS = yxVideoDao.queryByReleaseTime();
        HashMap<String, Integer> map = new HashMap<>();

        //模拟redis中添加点赞数量
        int i = 0;
        for (VideoPO videoPO : videoPOS) {

            i = new Random().nextInt(100);
            System.out.println("循环添加随机数" + i);
            map.put(videoPO.getId(), i);
        }

        //取值

        for (VideoPO videoPO : videoPOS) {
            Integer integer = null;
            for (String s : map.keySet()) {
                integer = map.get(s);
                System.out.println("反遍历键" + s + "值为：" + integer);
                if (videoPO.getId().equals(s)) {
                    videoPO.setLikeCount(integer);
                }
            }

        }
        return videoPOS;
    }

    @AddCache
    @Override
    public List<VideoPO> queryCateVideoList(String cateId) {
        List<VideoPO> videoPOS = yxVideoDao.queryAllByCategoryId(cateId);
        return videoPOS;
    }

    /**
     * 浏览视频信息 y
     */
    @AddCache
    @Override
    public VideoPO queryByVideoDetail(String videoId, String cateId, String userId) {
        log.info("条件查询视频videoId:{}", videoId);
        log.info("条件查询了类别cateId:{}", cateId);
        log.info("条件查询用户userId:{}", userId);
        VideoPO videoPO = yxVideoDao.queryAllByIdAndCategoryIdAndUserId(videoId, cateId, userId);
        List<VideoPO> list = yxVideoDao.queryAllByCategoryId(cateId);
        videoPO.setVideoList(list);
        return videoPO;
    }
}