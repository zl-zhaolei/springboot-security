package cn.npu.springbootsecurity.handler;

import cn.npu.springbootsecurity.domain.ResponseResult;
import cn.npu.springbootsecurity.utils.WebUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @descriptions:
 * @author: zlei
 * @date: 2023-7-23 15:36
 * @version: 1.0
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(403, "您的权限不足");
        String json = JSON.toJSONString(result);
        WebUtil.renderString(httpServletResponse, json);

    }
}
