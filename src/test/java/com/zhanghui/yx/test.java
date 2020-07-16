/*
package com.zhanghui.yx;


import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zhanghui.yx.eChartsVO.MonthAndCountVO;
import com.zhanghui.yx.eChartsVO.StatisticsVO;

import com.zhanghui.yx.service.YxUserService;
import com.zhanghui.yx.service.impl.YxUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = YxApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class test {


    @Resource
    private YxUserService yxUserService;

    @Test
    public List<Integer> MonthToCountsUtil() {
        StatisticsVO statisticsVO = new StatisticsVO();
        //得到所有月份集合
        List<String> allmonth = statisticsVO.getAllmonth();
        List<MonthAndCountVO> boylist = null;
        System.out.println("初步查询结果：" + boylist);
        //创建一个新的集合
        List<Integer> newlist = new ArrayList<>();
        boylist = yxUserService.selectAllBySexAndCreateDate("男");
        //遍历查询所有对象的集合
        //外层计数器
        int i = 0;
        for (String month : allmonth) {
            //System.out.println("第" + i + "次遍历的月份为:" + month);
            int boylistSize = boylist.size();
            //内层计数器
            int j = 1;
            for (MonthAndCountVO monthAndCountVO : boylist) {
                //System.out.println("内层第" + j + "次遍历得到的对象数据:" + monthAndCountVO);
                //遍历所有月份集合
                if (monthAndCountVO.getMonth().equals(month)) {
                    //如果集合中和月份集合相同-->直接添加个数 并且结束集合 外层开始循环
                    newlist.add(i, monthAndCountVO.getCount());
                    break;
                }
                //内层循环计数器循环次数和集合的长度一样  并且没有相同的 直接添加0
                if (j == boylist.size() && !monthAndCountVO.getMonth().equals(month)) {
                    newlist.add(i, 0);
                }
                j++;
            }

            i++;
        }
        System.out.println("新的集合为：" + newlist);

        return;
    }

}
*/
