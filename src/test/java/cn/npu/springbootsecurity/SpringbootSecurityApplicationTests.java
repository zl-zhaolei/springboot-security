package cn.npu.springbootsecurity;

import cn.npu.springbootsecurity.dao.UserDao;
import cn.npu.springbootsecurity.domain.User;
import cn.npu.springbootsecurity.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.security.auth.Subject;

@SpringBootTest
class SpringbootSecurityApplicationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void TestPasswordEncoder(){
        System.out.println(passwordEncoder.encode("lisi"));
    }
    @Test
    public void testJwt() throws Exception {
        Claims claims = JwtUtil.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiYzIzZGNlMjgzNTM0YzM4ODk0ZmQ0YzdmODUwOGU2MCIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY4OTkzMTU4MSwiZXhwIjoxNjg5OTM1MTgxfQ.A9_sFSCZWEF3d2gl-EsfwW1IRuQNzNbdoGGZwFIUM_g");
        String userid = claims.getSubject();
        System.out.println(userid);

    }
    @Autowired
    private UserDao userDao;
    @Test
    public void testUserDao() {
        String userid = "1";
        User user = userDao.getByUserID(Long.parseLong(userid));
        System.out.println(user.getUsername());
    }

}
