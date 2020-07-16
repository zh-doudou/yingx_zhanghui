package com.zhanghui.yx;


import com.zhanghui.yx.EsDao.CustomVideoRepsoitory;
import com.zhanghui.yx.EsDao.TPoemEs;
import com.zhanghui.yx.EsDao.VideoRepository;
import com.zhanghui.yx.dao.YxVideoDao;
import com.zhanghui.yx.entity.YxVideo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = YxApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class esTest {
    @Resource
    YxVideoDao yxVideoDao;
    @Resource
    VideoRepository videoRepository;
    @Resource
    CustomVideoRepsoitory customVideoRepsoitory;

    @Test
    public void goueasysendmassage() {
/*        List list = yxVideoDao.queryByAllVideoS();
        System.out.println(list);
        videoRepository.saveAll(list);*/
       /* List<YxVideo> a = videoRepository.findByTitle("仲夏来临咯");
        System.out.println(a);*/
        TPoemEs tPoemEs = new TPoemEs();
        tPoemEs.setType("title");
        tPoemEs.setTypevalue("仲夏");
        System.out.println(tPoemEs);
        List<YxVideo> contentTermTitle = customVideoRepsoitory.findContentTermTitle(tPoemEs);
        System.out.println(contentTermTitle);
    }
}