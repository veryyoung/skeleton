package me.veryyoung.skeleton.dao;


import me.veryyoung.skeleton.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by veryyoung on 2015/3/3.
 */

@Repository
public interface UserDao {

    void create(User user);

    int checkUserName(String userName);

    User findByUserName(String userName);

    List<User> findAll();


}
