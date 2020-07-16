package com.zhanghui.yx.EsDao;

import com.zhanghui.yx.entity.YxVideo;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 这是因为该注解的作用不只是将类识别为Bean，
 * 同时它还能将所标注的类中抛出的数据访问异常封装为 Spring 的数据访问异常类型。
 * Spring本身提供了一个丰富的并且是与具体的数据访问技术无关的数据访问异常结构，
 * 用于封装不同的持久层框架抛出的异常，使得异常独立于底层的框架。
 */
@Repository
public class CustomerVideoRepositoryAImpl implements CustomVideoRepsoitory {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Override
    public List<YxVideo> findContentTermTitle(TPoemEs tPoemEs) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withIndices(tPoemEs.getIndex())
                .withTypes(tPoemEs.get_type())
                .withQuery(QueryBuilders.termQuery(tPoemEs.getType(), tPoemEs.getTypevalue()))
                .build();
        List<YxVideo> yxVideos = elasticsearchTemplate.queryForList(nativeSearchQuery, YxVideo.class);
        return yxVideos;
    }
}
