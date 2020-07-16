package com.zhanghui.yx.util;

import com.zhanghui.yx.eChartsVO.MonthAndCountVO;
import com.zhanghui.yx.eChartsVO.StatisticsVO;

import java.util.ArrayList;
import java.util.List;

public class MonthToCountsUtil {
    public static List<Integer> getMonthCounts(List<MonthAndCountVO> list) {
        StatisticsVO statisticsVO = new StatisticsVO();
        //得到所有月份集合
        List<String> allmonth = statisticsVO.getAllmonth();
        //创建一个新的集合
        List<Integer> newlist = new ArrayList<>();
        int i = 0;
        for (String month : allmonth) {
            int boylistSize = list.size();
            //内层计数器
            int j = 1;
            for (MonthAndCountVO monthAndCountVO : list) {

                //遍历所有月份集合
                if (monthAndCountVO.getMonth().equals(month)) {
                    //如果集合中和月份集合相同-->直接添加个数 并且结束集合 外层开始循环
                    newlist.add(i, monthAndCountVO.getCount());
                    break;
                }
                //内层循环计数器循环次数和集合的长度一样  并且没有相同的 直接添加0
                if (j == list.size() && !monthAndCountVO.getMonth().equals(month)) {
                    newlist.add(i, 0);
                }
                j++;
            }

            i++;
        }
        System.out.println("新的集合为：" + newlist);
        return newlist;
    }
}
