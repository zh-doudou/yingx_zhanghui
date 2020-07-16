package com.zhanghui.yx.EsDao;

import com.zhanghui.yx.entity.YxVideo;

import java.util.List;

public interface CustomVideoRepsoitory {
    /**
     * 使用原始的term查询  content
     **/
    List<YxVideo> findContentTermTitle(TPoemEs tPoemEs);
}
