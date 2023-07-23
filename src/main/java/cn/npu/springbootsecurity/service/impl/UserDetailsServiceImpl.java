package cn.npu.springbootsecurity.service.impl;

import cn.npu.springbootsecurity.dao.UserDao;
import cn.npu.springbootsecurity.domain.LoginUser;
import cn.npu.springbootsecurity.domain.User;
import com.sun.org.apache.xml.internal.security.utils.UnsyncByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @descriptions:
 * @author: zlei
 * @date: 2023-7-20 23:59
 * @version: 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getByUsername(s);
        if (Objects.isNull(user)){
            throw  new RuntimeException("用户名或者密码错误");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        loginUser.setPermissions(userDao.getPerms(user.getId()));
//        loginUser.getAuthorities().forEach(System.out::println);
//        loginUser.getAuthorities().forEach(System.out::println);
        return loginUser;

    }

}
