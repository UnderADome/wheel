package orz.reinvent.wheel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import orz.reinvent.wheel.entity.UserEntity;
import orz.reinvent.wheel.service.MyUserService;

/**
 * @ClassName LoginController
 * @Description 登陆注册
 * @Author litao
 * @Date 2019/4/216:18
 * @Version 1.0
 **/
@Controller
public class LoginController {

    /*
     * @Auther 李韬
     * @Date 2019/4/17 18:07
     *
     * @Description
     * 配置登录页信息
     **/
    @RequestMapping(value = {"/login"})  //去掉了"/"
    public String LoginHTML(){


        return "login.html";
    }
    @RequestMapping(value = {"/register"})
    public String RegisterHTML(){


        return "register.html";
    }

    /*
     * @Auther 李韬
     * @Date 2019/4/17 18:07
     *
     * @Description
     * 登陆控制
     **/
    @RequestMapping(value = "/login/doLogin", method = RequestMethod.POST)
    public String doLogin(@RequestParam String username, @RequestParam String password){
        System.out.println(username+" "+password);

        return "home.html";
    }





    @Autowired
    private MyUserService myUserService;
    /*
     * @Auther 李韬
     * @Date 2019/4/17 18:08
     *
     * @Description
     * 注册并保存用户
     **/
    @RequestMapping(value = "register/save", method = RequestMethod.POST)
    //public String isSaved(@RequestParam UserEntity userEntity){
    public String isSaved(@RequestParam String username, @RequestParam String password){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(password);
        userEntity.setUsername(username);
        try{
            myUserService.insert(userEntity);
        }catch (Exception e){
            e.printStackTrace();
            return "未插入成功";
        }
        return "home.html";
    }

}
