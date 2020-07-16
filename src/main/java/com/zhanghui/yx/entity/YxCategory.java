package com.zhanghui.yx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * (YxCategory)实体类
 *
 * @author makejava
 * @since 2020-06-30 20:41:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YxCategory implements Serializable {
    /**
     * 类别id
     */
    private String id;
    /**
     * 类别名字
     */
    private String categoryName;
    /**
     * 级别
     */
    private String levels;
    /**
     * 所属级别id
     */
    private String parentId;

    /**
     * 级联查询
     */
    @javax.persistence.Transient
    private List<YxCategory> twoCateGoryS;

    /**
     * 2级类别下封装所属视频
     */
    @javax.persistence.Transient
    private List<YxVideo> yxVideos;


}