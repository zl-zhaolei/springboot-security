package cn.npu.springbootsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @descriptions:
 * @author: zlei
 * @date: 2023-7-20 21:39
 * @version: 1.0
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('g')")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/world")
    @PreAuthorize("hasAuthority('c')")
    public String world(){
         return "world";
    }
}
