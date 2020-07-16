package com.zhanghui.yx.eChartsVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushStatVO {
    private String sex;
    private List<String> months;
    private List<Integer> couts;

}
