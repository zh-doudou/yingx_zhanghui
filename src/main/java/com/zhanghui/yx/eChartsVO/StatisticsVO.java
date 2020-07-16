package com.zhanghui.yx.eChartsVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsVO {
    /**
     * 性别
     */
    private String sex;
    /**
     * 所有月份集合
     */
    private List<String> allMonth;
    /**
     * 所有月份和用户数量
     */
    private List<MonthAndCountVO> monthAndCountVO;

    /**
     * 封装查询数量集合
     */
    private List<Integer> data;

    public List<String> getAllmonth() {
        List<String> allMonth = new ArrayList<>();
        allMonth.add("1月");
        allMonth.add("2月");
        allMonth.add("3月");
        allMonth.add("4月");
        allMonth.add("5月");
        allMonth.add("6月");
        allMonth.add("7月");
        allMonth.add("8月");
        allMonth.add("9月");
        allMonth.add("10月");
        allMonth.add("11月");
        allMonth.add("12月");
        return allMonth;
    }
}
