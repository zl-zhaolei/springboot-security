package cn.npu.springbootsecurity.handler;

import cn.npu.springbootsecurity.domain.ResponseResult;
import cn.npu.springbootsecurity.utils.WebUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.alibaba.fastjson.JSON;

/**
 * @descriptions:
 * @author: zlei
 * @date: 2023-7-23 15:25
 * @version: 1.0
 */
@Component
public class AuthEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //处理异常
        ResponseResult result = new ResponseResult(401,"用户名认证失败，请重新登录");
        String json = JSON.toJSONString(result);
        WebUtil.renderString(httpServletResponse,json);
    }
}
