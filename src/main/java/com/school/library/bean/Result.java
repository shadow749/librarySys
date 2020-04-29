package com.school.library.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: shadow
 * @Date: 2018/10/6 14:59
 * @Description: 最后的返回结果集
 */
public class Result {
    public static Map<String, Object> sucJsonResp(Object data) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", 1);
        resultMap.put("data", data);
        return resultMap;
    }

    public static Map<String, Object>  failJsonResp(String msg) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", 0);
        resultMap.put("msg", msg);
        return resultMap;
    }
}
