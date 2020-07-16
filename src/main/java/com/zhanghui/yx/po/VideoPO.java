package com.zhanghui.yx.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoPO {
    /**
     * 唯一id
     */
    private String id;
    /**
     * 标题
     */
    private String videoTitle;
    /**
     * 截图路径
     */
    private String cover;
    /**
     * 保存路径
     */
    private String path;
    /**
     * 上传时间
     */
    private String uploadTime;
    /**
     * 描述
     */
    private String description;
    /**
     * 点赞数
     *
     * @javax.persistence.Transient 无需再mapper中映射关系
     */

    @javax.persistence.Transient
    private Integer likeCount;

    private String userId;

    private String userName;
    /**
     * 类别id
     */
    private String categoryId;
    /**
     * 类别名字
     */
    private String cateName;
    /**
     * 用户头像
     */
    private String userPhoto;

    private List<VideoPO> videoList;
}
