package com.zhanghui.yx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult {
    private Object data;
    private String message;
    private String stauts;

    /**
     *
     */
    public CommonResult success(Object data, String message, String stauts) {
        CommonResult commonResult = new CommonResult();
        commonResult.setStauts(stauts);
        commonResult.setData(data);
        commonResult.setMessage(message);
        return commonResult;
    }

    public CommonResult error(String message, Object data) {
        CommonResult commonResult = new CommonResult();
        commonResult.setStauts("104");
        commonResult.setMessage(message);
        commonResult.setData(data);
        return commonResult;
    }

    public CommonResult queryByReleaseTime(Object data) {
        CommonResult commonResult = new CommonResult();
        commonResult.setData(data);
        commonResult.setMessage("查询成功");
        commonResult.setStauts("100");
        return commonResult;
    }
}