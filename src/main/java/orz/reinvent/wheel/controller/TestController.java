package orz.reinvent.wheel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName TestController
 * @Description 测试专用controller
 * @Author mushi
 * @Date 2019/6/2710:20
 * @Version 1.0
 **/
@Controller
public class TestController {

    @RequestMapping(value = {"/test"})
    public String LoginHTML(){


        return "loadpic.html";
    }



}
