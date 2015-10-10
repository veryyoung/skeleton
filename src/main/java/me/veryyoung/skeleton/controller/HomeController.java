package me.veryyoung.skeleton.controller;

import me.veryyoung.skeleton.entity.User;
import me.veryyoung.skeleton.rest.RestData;
import me.veryyoung.skeleton.security.LoginRequired;
import me.veryyoung.skeleton.service.UserService;
import me.veryyoung.skeleton.utils.ContextUtils;
import me.veryyoung.skeleton.utils.WebUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by veryyoung on 2015/3/2.
 */
@Controller
@RequestMapping("")
public class HomeController extends BaseController {

    @Autowired
    private UserService userService;


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

        userService.addUser(user);


        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/checkUserName", method = RequestMethod.GET)
    @ResponseBody
    public RestData checkUserName(String userName) {
        RestData restData = new RestData();
        if (userService.checkUserName(userName)) {
            restData.setSuccess(1);
        } else {
            restData.setComment("该用户名已存在");
        }
        return restData;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin(String redirect) {
        ModelAndView modelAndView = new ModelAndView("/login");
        if (StringUtils.isNotEmpty(redirect)) {
            modelAndView.addObject("error", "您需要先登录!");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(String userName, String password, String captcha) {
        ModelAndView modelAndView = new ModelAndView("/login");
        if (!WebUtils.checkCaptcha(request, captcha)) {
            modelAndView.addObject("error", "验证码错误");
            return modelAndView;
        }

        User user = userService.findByUserName(userName);

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

    @RequestMapping("logout")
    public String logout() {
        request.getSession().invalidate();
        return "redirect:/";
    }

}
