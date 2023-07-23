package cn.npu.springbootsecurity.filter;

import cn.npu.springbootsecurity.dao.UserDao;
import cn.npu.springbootsecurity.domain.LoginUser;
import cn.npu.springbootsecurity.domain.ResponseResult;
import cn.npu.springbootsecurity.service.impl.UserDetailsServiceImpl;
import cn.npu.springbootsecurity.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @descriptions:
 * @author: zlei
 * @date: 2023-7-20 23:23
 * @version: 1.0
 */
@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDao userDao;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(userDao.getByUserID(Long.parseLong(userid)));
        loginUser.setPermissions(userDao.getPerms(Long.parseLong(userid)));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,
                null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
