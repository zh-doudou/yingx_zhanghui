package com.zhanghui.yx.EsDao;


import com.zhanghui.yx.entity.YxVideo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author 张辉
 * 泛型   <操作对象类型,序列化主键的类型>
 */
public interface VideoRepository extends ElasticsearchRepository<YxVideo, String> {
    List<YxVideo> findByTitle(String title);
}
