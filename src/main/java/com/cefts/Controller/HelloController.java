package com.cefts.Controller;

import com.cefts.Bean.User;
import com.cefts.Entity.Demo;
import com.cefts.Service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class HelloController {
    @Resource
    private UserService userService;


    @RequestMapping("/hello")
    public String hello(){
        return "hello word!";
    }

    @RequestMapping(value = "/getDemo",produces = "application/json;charset=UTF-8")
    public Demo getDemo(){
        Demo demo = new Demo();
        demo.setId(1);
        demo.setName("张三丰");
        demo.setCreateTime(new Date());
        demo.setRemark("这里是备注信息");
        return demo;
    }
    @RequestMapping("/savaUser")
    public String saveUser(){
        User user = new User();
        user.setUserName("tom");
        user.setPassWord("111");
        userService.save(user);
        return "保存成功";
    }
    @RequestMapping(value = "/selUserByName",produces = "application/json;charset=UTF-8")
    public User selUserByName(String userName){
        return userService.selUserByName(userName);
    }

    @RequestMapping(value = "/test",produces = "application/json;charset=UTF-8")
    public String test(){
        String s="";
        try {
            int a = 1/0;
            s="xu";
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }
}
