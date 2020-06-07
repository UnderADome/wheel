package orz.reinvent.wheel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import orz.reinvent.wheel.entity.ArticleEntity.Test_User;
import orz.reinvent.wheel.service.ArticleService.Test_UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
/**
 * @RestController注解是@Controller和@ResponseBody的合集,
 * 表示这是个控制器bean,并且是将函数的返回值，直接填入HTTP响应体中,是REST风格的控制器。
 */
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.site.blog.Service"}) //有什么用？？
public class Test_UserQueryController {

    //依赖注入
    @Autowired
    private Test_UserService userService;

    /**
     * @RestController代表这个类是用Restful风格来访问的，如果是普通的WEB页面访问跳转时，我们通常会使用@Controller
    value = "/users/{username}" 代表访问的URL是"http://host:PORT/users/实际的用户名"
    method = RequestMethod.GET 代表这个HTTP请求必须是以GET方式访问
    consumes="application/json" 代表数据传输格式是json
     @PathVariable将某个动态参数放到URL请求路径中
     @RequestParam指定了请求参数名称
     */

    @RequestMapping(value = "queryU/{username}",method = RequestMethod.GET)
    public List<Test_User> queryProduct(@PathVariable String username, HttpServletResponse httpServletResponse){
        System.out.println("username:"+username);
        List<Test_User> ulist = userService.queryUserByUserName(username);
        return ulist;
    }
}
