package me.veryyoung.skeleton.entity;

import lombok.Data;
import me.veryyoung.skeleton.validator.constraints.UserName;

import java.util.Date;

/**
 * Created by veryyoung on 2015/3/2.
 */

@Data
public class User {

    private String id;

    @UserName
    private String userName;

    private String password;

    private Date createTime;

    //是否为管理员
    private boolean admin;


}
