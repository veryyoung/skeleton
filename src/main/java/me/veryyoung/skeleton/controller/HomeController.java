package me.veryyoung.skeleton.controller;

import me.veryyoung.skeleton.dao.UserDao;
import me.veryyoung.skeleton.entity.User;
import me.veryyoung.skeleton.rest.RestData;
import me.veryyoung.skeleton.security.LoginRequired;
import me.veryyoung.skeleton.service.UserService;
import me.veryyoung.skeleton.utils.ContextUtils;
import me.veryyoung.skeleton.utils.WebUtils;
import me.veryyoung.skeleton.validator.InvalidException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by veryyoung on 2015/3/2.
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;


    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister() {
        return "/register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(User user, String captcha) {
        ModelAndView modelAndView = new ModelAndView("/register");
        if (!WebUtils.checkCaptcha(request, captcha)) {
            modelAndView.addObject("error", "验证码错误");
            return modelAndView;
        }

        logger.debug("user:{}", user);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setCreateTime(new Date());
        try {
            getValidatorWrapper().tryValidate(user);
            userService.create(user);
        } catch (InvalidException ex) {
            logger.error("Invalid User Object: {}", user.toString(), ex);
            modelAndView.addObject("error", ex.getMessage());
            return modelAndView;
        }


        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = "/checkUserName", method = RequestMethod.GET)
    @ResponseBody
    public RestData checkUserName(String userName) {
        RestData restData = new RestData();
        if (userDao.checkUserName(userName)) {
            restData.setSuccess(1);
        } else {
            restData.setComment("该用户名已存在");
        }
        return restData;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin() {
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(String userName, String password, String captcha) {
        ModelAndView modelAndView = new ModelAndView("/login");
        if (!WebUtils.checkCaptcha(request, captcha)) {
            modelAndView.addObject("error", "验证码错误");
            return modelAndView;
        }

        User user = userDao.findByUserName(userName);

        if (null != user && user.getPassword().equals(DigestUtils.md5Hex(password))) {
            ContextUtils.getSessionUtils(request).setUser(user);
            return new ModelAndView("redirect:/account");
        } else {
            modelAndView.addObject("error", "用户名或密码错误");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @LoginRequired
    public String getAccount() {
        return "/account";
    }


}
