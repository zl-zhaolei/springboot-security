package cn.npu.springbootsecurity.dao;

import cn.npu.springbootsecurity.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @descriptions:
 * @author: zlei
 * @date: 2023-7-21 11:25
 * @version: 1.0
 */
@Repository
public class UserDao {
    private static List<User> users = new ArrayList<>();

    static {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("zlei");
        user1.setPassword("$2a$10$erELB4CohTN.ihshCinRdeK9a.FTcfUPH4j1P6NuATJ8DhfhtTPwS");
        User user2 = new User();
        user2.setId(2l);
        user2.setUsername("lisi");
        user2.setPassword("$2a$10$7PxTETpDDgDLjs28REdaROaa2BpeAn0aZdHEFGtgcTUFqXYSYbPLO");
        users.add(user1);
        users.add(user2);
    }

    private static Map<Long, List<String>> perms = new HashMap<>();

    static {
        List<String> p1 = new ArrayList<>();
        p1.add("z");
        p1.add("s");
        p1.add("g");
        p1.add("c");
        perms.put(1l, p1);
        List<String> p2 = new ArrayList<>();
        p2.add("c");
        perms.put(2l, p2);
    }

    public User getByUserID(Long id) {
        for (User user : users) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    public List<String> getPerms(Long aLong) {
        if (perms.containsKey(aLong)) {
            return perms.get(aLong);
        }
        return null;
    }

    public User getByUsername(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
    }
}
