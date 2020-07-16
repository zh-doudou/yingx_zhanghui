package com.zhanghui.yx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (YxGroup)实体类
 *
 * @author makejava
 * @since 2020-07-05 19:58:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YxGroup implements Serializable {
    private static final long serialVersionUID = -31773036934319045L;
    /**
     * 分组表唯一id
     */
    private String id;
    /**
     * 分组的名字
     */
    private String name;
    /**
     * @DateTimeFormat ：接收格式设定
     * @JsonFormat ：响应格式设置
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gropDate;
    /**
     * 所属用户id
     */
    private String userId;
    @javax.persistence.Transient
    private YxUser yxUser;


}