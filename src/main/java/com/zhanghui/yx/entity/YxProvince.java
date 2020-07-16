package com.zhanghui.yx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (YxProvince)实体类
 *
 * @author makejava
 * @since 2020-07-11 19:43:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YxProvince implements Serializable {
    private static final long serialVersionUID = -51910702971356533L;
    /**
     * 省份主键
     */
    private String id;
    /**
     * 邮编
     */
    private String code;
    /**
     * 省份名字
     */
    private String name;

}