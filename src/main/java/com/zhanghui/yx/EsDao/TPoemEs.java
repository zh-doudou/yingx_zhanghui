package com.zhanghui.yx.EsDao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TPoemEs {
    /**
     * 索引名字  唯一
     */
    private String index = "yxvideo";
    /**
     * 索引的类型   唯一
     */
    private String _type = "video";
    /**
     * 查询的类型
     */
    private String type;
    /**
     * 类型的值
     */
    private String typevalue;
}
