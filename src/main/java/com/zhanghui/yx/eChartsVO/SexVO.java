package com.zhanghui.yx.eChartsVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SexVO {
    private String sex;
    private List<CityVO> citys;
}
