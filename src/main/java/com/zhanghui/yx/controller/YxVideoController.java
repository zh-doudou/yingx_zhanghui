package com.zhanghui.yx.controller;

import com.zhanghui.yx.EsDao.CustomVideoRepsoitory;
import com.zhanghui.yx.EsDao.EsUtile;
import com.zhanghui.yx.EsDao.TPoemEs;
import com.zhanghui.yx.aliyunutil.AliYunVO;
import com.zhanghui.yx.entity.YxVideo;
import com.zhanghui.yx.service.YxVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (YxVideo)表控制层
 *
 * @author makejava
 * @since 2020-07-03 19:23:58
 */
@RestController
@RequestMapping("yxVideo")
@Slf4j
public class YxVideoController {
    /**
     * 服务对象
     */
    @Resource
    private YxVideoService yxVideoService;
    @Resource
    AliYunVO aliYunVO;
    @Autowired
    CustomVideoRepsoitory customVideoRepsoitory;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public YxVideo selectOne(String id) {
        return this.yxVideoService.queryById(id);
    }

    @GetMapping("queryByPage")
    public Map<String, Object> queryAllByPage(Integer rows, Integer page) {
        Map<String, Object> map = this.yxVideoService.queryAllByLimit(rows, page);
        return map;
    }

    @GetMapping("addVideo")
    public Map<String, Object> addVideo(YxVideo yxVideo, String id) {
        log.info("上传数据为：{}", yxVideo);
        log.info("上传用户id为：{}", id);
        Map<String, Object> map = this.yxVideoService.insert(yxVideo);
        return map;
    }

    @RequestMapping("uplodeVideo")
    public Map<String, Object> uplodeVideo(MultipartFile videoFile, String id) {
        log.info("上传文件信息:{}", videoFile.getOriginalFilename());
        log.info("新建视频id：{}", id);
        aliYunVO.setId(id);
        aliYunVO.setHeadImg(videoFile);
        Map<String, Object> map = yxVideoService.videoUpLoad(aliYunVO);
        return map;
    }

    @RequestMapping("delById")
    public Map<String, Object> delById(String id) {
        aliYunVO.setId(id);
        Map<String, Object> map = yxVideoService.deleteById(aliYunVO);
        return map;
    }

    @RequestMapping("upDateVideo")
    public Map<String, Object> upDateVideo(YxVideo yxVideo) {
        log.info("修改传递的数据:{}", yxVideo);
        Map<String, Object> map = yxVideoService.update(yxVideo);
        return map;
    }

    /**
     * 模糊匹配查询
     */
    @CrossOrigin
    @PostMapping("searchByName")
    public List<YxVideo> searchByName(TPoemEs tPoemEs) {
        System.out.println("查询条件:" + tPoemEs);
        List<YxVideo> searchVideos = customVideoRepsoitory.findContentTermTitle(tPoemEs);
        System.out.println("查询结果：" + searchVideos);
        return searchVideos;
    }

    /**
     * 高亮查询
     */
    @CrossOrigin
    @PostMapping("highlightingQuery")
    public List<YxVideo> highlightingQuery(TPoemEs tPoemEs) {
        List<YxVideo> yxVideos = EsUtile.queryInfo(YxVideo.class, elasticsearchTemplate, tPoemEs);
        for (YxVideo yxVideo : yxVideos) {
            System.out.println("高亮查询" + yxVideo);
        }
        return yxVideos;
    }
}

