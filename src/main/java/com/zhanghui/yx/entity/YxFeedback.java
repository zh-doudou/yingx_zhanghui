package com.zhanghui.yx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (YxFeedback)实体类
 *
 * @author makejava
 * @since 2020-07-02 09:45:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YxFeedback implements Serializable {
    private static final long serialVersionUID = 666553536247940332L;
    /**
     * 反馈表唯一id
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 反馈时间
     */
    /**
     * @DateTimeFormat ：接收格式设定
     * @JsonFormat ：响应格式设置
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date feedbackDate;
    /**
     * 用户实体类
     *
     * @javax.persistence.Transient :忽略此字段
     */
    @javax.persistence.Transient
    private YxUser yxUser;
}