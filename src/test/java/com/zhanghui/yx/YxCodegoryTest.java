package com.zhanghui.yx;

import com.zhanghui.yx.aliyunutil.AliYunVO;
import com.zhanghui.yx.dao.YxCategoryDao;
import com.zhanghui.yx.entity.YxCategory;
import com.zhanghui.yx.entity.YxVideo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = YxApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class YxCodegoryTest {
    @Autowired
    YxCategoryDao yxCategoryDao;
    @Autowired
    AliYunVO aliYunVO;

    @Test
    public void select() {
        List<YxCategory> yxCategories = yxCategoryDao.queryAllClass(0, 10);
        for (YxCategory yxCategory : yxCategories) {

            log.info("一级类别名字为--------{}", yxCategory.getCategoryName());

            log.info("2级类别{}", yxCategory.getTwoCateGoryS());
            for (YxCategory twoCateGory : yxCategory.getTwoCateGoryS()) {
                log.info("2级类别名字----{}", twoCateGory.getCategoryName());
                for (YxVideo yxVideo : twoCateGory.getYxVideos()) {
                    log.info("2级类别下的视频名字-----{}", yxVideo.getTitle());
                }
            }

        }
    }
    @Test
    public void  add(){
        System.out.println(aliYunVO);
    }
}
