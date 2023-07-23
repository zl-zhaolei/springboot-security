package cn.npu.springbootsecurity.service.impl;

import cn.npu.springbootsecurity.domain.LoginUser;
import cn.npu.springbootsecurity.domain.ResponseResult;
import cn.npu.springbootsecurity.domain.User;
import cn.npu.springbootsecurity.service.LoginService;
import cn.npu.springbootsecurity.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @descriptions:
 * @author: zlei
 * @date: 2023-7-20 22:43
 * @version: 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登陆失败");
        }
        //如果认证通过，使用userid生成一个jwt 存入responseresult并返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new ResponseResult<>(200, "登陆成功", map);

    }

    @Override
    public ResponseResult logout() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();

        return null;
    }
}
