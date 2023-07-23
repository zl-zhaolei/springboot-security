package cn.npu.springbootsecurity.controller;

import cn.npu.springbootsecurity.domain.ResponseResult;
import cn.npu.springbootsecurity.domain.User;
import cn.npu.springbootsecurity.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @descriptions:
 * @author: zlei
 * @date: 2023-7-20 21:46
 * @version: 1.0
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @RequestMapping(method = RequestMethod.POST,value = "/user/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }
    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
