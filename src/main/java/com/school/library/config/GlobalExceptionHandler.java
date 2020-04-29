package com.school.library.config;

import com.alibaba.fastjson.JSON;
import com.school.library.bean.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Auther: shadow
 * @Date: 2018/9/29 16:04
 * @Description:
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e,
                                            HandlerMethod method) throws Exception {
        ModelAndView mav = null;
        boolean isApi = method != null && (method.hasMethodAnnotation(ResponseBody.class)
                || method.getBeanType().isAnnotationPresent(RestController.class));
        //如果是ajax调用，则返回json串
        e.printStackTrace();
        if (isApi)
        {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(Result.failJsonResp(e.getMessage())));
            writer.flush();
        }
        else
        {//form表单，则返回页面
            mav = new ModelAndView();

           String msg = "未知异常";
            if(e instanceof UnauthorizedException){
                    msg = "您没有访问权限";
            }
            mav.addObject("exception", msg);
            mav.addObject("url", request.getRequestURL());
            mav.setViewName(DEFAULT_ERROR_VIEW);
        }
        return mav;
    }
}
