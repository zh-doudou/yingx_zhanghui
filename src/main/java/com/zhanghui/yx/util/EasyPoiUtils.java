package com.zhanghui.yx.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.zhanghui.yx.aliyunutil.ALiYunOssUtil;
import com.zhanghui.yx.aliyunutil.AliYunVO;
import com.zhanghui.yx.entity.YxUser;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class EasyPoiUtils {

    public static void easyPoi(List<YxUser> yxUsers, AliYunVO aliYunVO, HttpServletResponse response) throws IOException {

        for (YxUser yxUser : yxUsers) {
            String headImg = yxUser.getHeadImg();
            //根据网络地址下载到本地
            String s = ALiYunOssUtil.DownloadLocal(aliYunVO, headImg);
            //从本地重新上传
            yxUser.setHeadImg(s);
        }
        ExportParams exportParams = new ExportParams("用户信息表", "用户");

        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, YxUser.class, yxUsers);
        System.out.println(workbook);
        downLoadExcel("用户信息表", response, workbook);

        //workbook.write(new FileOutputStream(new File("E:/user.xls")));

    }


    /**
     * 功能描述：Excel导出
     *
     * @param fileName 文件名称
     * @param response
     * @param workbook Excel对象
     * @return
     */
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            System.out.println("开始写出");
            //workbook.write(new FileOutputStream(new File("E:/user.xls")));
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.close();
            outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


