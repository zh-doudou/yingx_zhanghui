package com.zhanghui.yx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (YxLog)实体类
 *
 * @author makejava
 * @since 2020-07-07 16:41:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YxLog implements Serializable {
    /**
     * 日志id
     */
    private String id;
    /**
     * 操作的用户
     */
    private String name;

    /**
     * @DateTimeFormat ：接收格式设定
     * @JsonFormat ：响应格式设置
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dates;
    /**
     * 操作的内容
     */
    private String operation;
    /**
     * 操作状态
     */
    private String stauts;
}