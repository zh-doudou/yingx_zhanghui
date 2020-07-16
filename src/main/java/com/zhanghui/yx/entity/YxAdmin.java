package com.zhanghui.yx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (YxAdmin)实体类
 *
 * @author makejava
 * @since 2020-06-30 14:43:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YxAdmin implements Serializable {

    private String id;

    private String username;

    private String password;

    private String status;


}