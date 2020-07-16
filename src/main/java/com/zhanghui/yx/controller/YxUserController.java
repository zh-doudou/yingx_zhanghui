package com.zhanghui.yx.controller;

import com.zhanghui.yx.aliyunutil.AliYunVO;
import com.zhanghui.yx.aspect.MyPush;
import com.zhanghui.yx.eChartsVO.PushStatVO;
import com.zhanghui.yx.eChartsVO.SexVO;
import com.zhanghui.yx.entity.YxUser;
import com.zhanghui.yx.service.YxUserService;
import com.zhanghui.yx.util.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (YxUser)表控制层
 *
 * @author makejava
 * @since 2020-07-01 11:40:05
 */
@RestController
@RequestMapping("yxUser")
@Slf4j
public class YxUserController {
    /**
     * 服务对象
     */
    @Resource
    private YxUserService yxUserService;

    @Resource
    LoginVo loginVo;
    @Resource
    AliYunVO aliYunVO;


    /**
     * 查询所有
     **/
    @RequestMapping("queryAllUser")
    public Map<String, Object> queryAllUser(Integer rows, Integer page) {
        Map<String, Object> map = yxUserService.queryAllByLimit(rows, page);
        return map;
    }

    /**
     * 添加用户信息
     *
     * @param yxUser 实例对象
     */
    @RequestMapping("addUser")
    public Map<String, Object> addUser(YxUser yxUser) {
        log.info("添加的对象数据为：{}", yxUser);
        Map<String, Object> map = yxUserService.insert(yxUser);
        return map;
    }

    /**
     * 用户头像文件上传
     *
     * @param
     * @param
     * @return
     **/
    @RequestMapping("upLode")
    public Map<String, Object> upLode(MultipartFile headImg, String id) {
        aliYunVO.setHeadImg(headImg);
        aliYunVO.setId(id);
        Map<String, Object> map = yxUserService.upLode(aliYunVO);
        return map;
    }

    /**
     * @param id 通过主键删除用户
     * @return 返回分页查询map
     **/

    @RequestMapping("delById")
    public Map<String, Object> delById(String id) {
        Map<String, Object> map = yxUserService.deleteById(aliYunVO, id);
        return map;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public YxUser selectOne(String id) {
        log.info("查询的id为：{}", id);
        return this.yxUserService.queryById(id);
    }

    /**
     * 修改数据
     *
     * @param yxUser 实例化对象
     **/
    @RequestMapping("update")
    public Map<String, Object> update(YxUser yxUser) {
        log.info("修改信息为：{}", yxUser);
        Map<String, Object> map = yxUserService.update(yxUser);
        return map;
    }

    /**
     * 用户手机验证登录/注册
     * 主要是通过手机号获取验证码
     **/
    @RequestMapping("userInsert")
    public Map<String, Object> userInsert(String phone) {

        return null;
    }

    @RequestMapping("derivedInformation")
    public Map<String, Object> derivedInformation(String drive, String folderLocation) throws IOException {
        //导出信息
        Map<String, Object> map = yxUserService.derivedInformation(aliYunVO, drive, folderLocation);
        return map;
    }


    @RequestMapping("selectAllBySexGroupByCity")
    public ArrayList<SexVO> selectAllBySexGroupByCity() {
        ArrayList<SexVO> sexVOS = yxUserService.selectAllBySexGroupByCity();
        return sexVOS;
    }

    @RequestMapping("selectAllBySexAndCreateDate")
    public List<PushStatVO> selectAllBySexAndCreateDate() {
        List<PushStatVO> pushStatVOS = yxUserService.selectAllBySexAndCreateDate();
        return pushStatVOS;
    }

    @MyPush("dsa")
    @RequestMapping("selectOneGoEasy")
    public String selectOne() {
        return "ok";
    }

}
