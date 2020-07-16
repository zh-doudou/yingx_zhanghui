package com.zhanghui.yx.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CateGoryPO {
    private String id;
    private String cateName;
    private String levels;
    private String parentId;
    private List<CateGoryPO> categoryList;
}
