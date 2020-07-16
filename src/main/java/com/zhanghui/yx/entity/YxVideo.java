package com.zhanghui.yx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (YxVideo)实体类
 *
 * @author makejava
 * @since 2020-07-03 12:46:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "yxvideo", type = "video")
public class YxVideo implements Serializable {
    /**
     * 视频表唯一id
     *
     * @Id： 映射_id
     */
    @Id
    private String id;
    /**
     * 视频标题
     */

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;
    /**
     * 视频描述
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String brief;
    /**
     * 视频上传时间
     *
     * @DateTimeFormat ：接收格式设定
     * @JsonFormat ：响应格式设置
     */
    @Field(type = FieldType.Date)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date videoDate;
    /**
     * 视频所属类别
     */
    @Field(type = FieldType.Keyword)
    private String categoryId;
    /**
     * 视频所属分组
     */
    @Field(type = FieldType.Keyword)
    private String groupId;
    /**
     * 视频上传作者
     */
    @Field(type = FieldType.Keyword)
    private String userId;
    /**
     * 视频路径链接
     */
    @Field(type = FieldType.Keyword)
    private String videoPath;
    /**
     * 图片路径链接
     */
    @Field(type = FieldType.Keyword)
    private String coverPath;
    /**
     * 封装类别表
     */
    @javax.persistence.Transient
    private YxCategory yxCategory;
    /**
     * 用户
     **/
    @javax.persistence.Transient
    private YxUser yxUser;


}