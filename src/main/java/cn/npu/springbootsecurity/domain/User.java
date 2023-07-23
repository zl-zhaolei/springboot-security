package cn.npu.springbootsecurity.domain;

import lombok.Data;

/**
 * @descriptions:
 * @author: zlei
 * @date: 2023-7-20 22:18
 * @version: 1.0
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer delFlag;

}
