package com.zhanghui.yx;

import com.zhanghui.yx.dao.YxFeedbackDao;
import com.zhanghui.yx.entity.YxFeedback;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = YxApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class PoiTest {
    @Autowired
    YxFeedbackDao yxFeedbackDao;

    @Test
    public void row() {


        //创建一个Excel文档
        Workbook workbook = new HSSFWorkbook();
        //构建字体
        Font font = workbook.createFont();
        //创建文档中的工作表
        Sheet sheet = workbook.createSheet("192信息");
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("反馈表");
        //设置行高
        row0.setHeight((short) 800);
        //合并行   参数：起始行,结束行,起始单元格,结束单元格
        CellRangeAddress addresses = new CellRangeAddress(0, 0, 0, 4);
        sheet.addMergedRegion(addresses);
        //设置列宽  参数：列索引，列宽(注意：单位为1/256)
        sheet.setColumnWidth(4,20*256);

        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("id");
        row1.createCell(1).setCellValue("反馈标题");
        row1.createCell(2).setCellValue("反馈内容");
        row1.createCell(3).setCellValue("反馈人id");
        row1.createCell(4).setCellValue("反馈时间");

        List<YxFeedback> yxFeedbacks = yxFeedbackDao.queryAllByLimit(0, 10);
        log.info("反馈结果：{}", yxFeedbacks);
        int i = 2;

        //创建一个日期格式对象
        DataFormat dataFormat = workbook.createDataFormat();
        //创建一个样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        //将日期格式放入样式对象中
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy年MM月dd日"));

        for (YxFeedback yxFeedback : yxFeedbacks) {
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(yxFeedback.getId());
            row.createCell(1).setCellValue(yxFeedback.getTitle());
            row.createCell(2).setCellValue(yxFeedback.getContent());
            row.createCell(3).setCellValue(yxFeedback.getUserId());
            Cell cell4 = row.createCell(4);


            cell4.setCellStyle(cellStyle);
            cell4.setCellValue(yxFeedback.getFeedbackDate());
            i++;

        }
        //创建一行   参数：行下标（下标从0开始）

        try {
            workbook.write(new FileOutputStream(new File("D://192测试.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
