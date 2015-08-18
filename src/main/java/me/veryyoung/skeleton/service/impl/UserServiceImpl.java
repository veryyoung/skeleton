package me.veryyoung.skeleton.service.impl;

import me.veryyoung.skeleton.dao.UserDao;
import me.veryyoung.skeleton.entity.User;
import me.veryyoung.skeleton.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by veryyoung on 2015/3/3.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean checkUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return false;
        }
        return userDao.checkUserName(userName);
    }

    @Override
    public User findByUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        return userDao.findByUserName(userName);
    }
}
