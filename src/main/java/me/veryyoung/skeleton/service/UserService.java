package me.veryyoung.skeleton.service;

import me.veryyoung.skeleton.entity.User;

import java.util.List;

/**
 * Created by veryyoung on 2015/3/3.
 */
public interface UserService {

    public void create(User user);

    public List<User> findAll();
}
