package com.zhanghui.yx;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.zhanghui.yx.aliyunutil.ALiYunOssUtil;
import com.zhanghui.yx.aliyunutil.AliYunVO;
import com.zhanghui.yx.dao.YxUserDao;
import com.zhanghui.yx.entity.YxUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@SpringBootTest(classes = YxApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class EasyOI {
    @Autowired
    YxUserDao yxUserDao;
    @Autowired
    AliYunVO aliYunVO;
    @Resource
    HttpServletResponse response;

    @Test
    public void easyPoi() throws IOException {
        List<YxUser> yxUsers = yxUserDao.queryAllByLimit(0, 10);

        //遍历数据
        for (YxUser yxUser : yxUsers) {
            String headImg = yxUser.getHeadImg();
            //根据网络地址下载到本地
            String s = ALiYunOssUtil.DownloadLocal(aliYunVO, headImg);
            //从本地重新上传
            yxUser.setHeadImg(s);
        }
        log.info("查询数据为：{}", yxUsers);
        ExportParams exportParams = new ExportParams("用户信息表", "用户");

        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, YxUser.class, yxUsers);
        System.out.println(workbook);
        downLoadExcel("用户信息表", response, workbook);

        //workbook.write(new FileOutputStream(new File("E:/user.xls")));
        workbook.close();
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
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void uploadlocal() {
        String s = "https://yingx-zh.oss-cn-beijing.aliyuncs.com/cccc/1593823371138图片1.png";
        ALiYunOssUtil.DownloadLocal(aliYunVO, s);
    }
}
