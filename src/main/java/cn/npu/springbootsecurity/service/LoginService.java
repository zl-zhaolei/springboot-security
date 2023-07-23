package cn.npu.springbootsecurity.service;

import cn.npu.springbootsecurity.domain.ResponseResult;
import cn.npu.springbootsecurity.domain.User;

/**
 * @descriptions:
 * @author: zlei
 * @date: 2023-7-20 21:42
 * @version: 1.0
 */

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
