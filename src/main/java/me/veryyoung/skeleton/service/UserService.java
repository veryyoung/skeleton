package me.veryyoung.skeleton.service;

import me.veryyoung.skeleton.entity.User;

import java.util.List;

/**
 * Created by veryyoung on 2015/3/3.
 */
public interface UserService {

    public boolean create(User user);

    public List<User> findAll();

    public boolean checkUserName(String userName);

    public User findByUserName(String userName);
}
