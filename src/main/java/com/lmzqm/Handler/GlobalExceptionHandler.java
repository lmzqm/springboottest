package com.lmzqm.Handler;

import com.lmzqm.Extention.MyExcention;
import com.lmzqm.Model.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lmzqm on 2017/4/24.
 */
//全局异常处理操作
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req,Exception e) throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.addObject("excepttion",e);
        mav.addObject("url",req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

//     throw new MyException("发生错误2");
    @ExceptionHandler(value = MyExcention.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req,MyExcention e) throws Exception{
        ErrorInfo<String> info = new ErrorInfo<>();
        info.setCode(ErrorInfo.ERROR);
        info.setData("Some Data");
        info.setMessage("发生错误");
        info.setUrl(req.getRequestURL().toString());
        return info;
    }
}
