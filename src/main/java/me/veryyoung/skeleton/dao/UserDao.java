package me.veryyoung.skeleton.dao;


import me.veryyoung.skeleton.entity.User;
import org.springframework.stereotype.Repository;


/**
 * Created by veryyoung on 2015/3/3.
 */

@Repository
public class UserDao extends BaseDao<User> {

    public UserDao() {
        super(User.class);
    }

}
