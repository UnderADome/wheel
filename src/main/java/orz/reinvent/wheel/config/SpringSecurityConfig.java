package orz.reinvent.wheel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import orz.reinvent.wheel.service.MyUserService;

/**
 * @ClassName SpringSecurityConfig
 * @Description TODO
 * @Author mushi
 * @Date 2019/4/816:04
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity //这个注解启用SpringSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private AccessDeniedHandler accessDeniedHandler;
//
//    @Autowired
//    private CustAuthenticationProvider custAuthenticationProvider;

    @Autowired
    private MyUserService MyUserService;

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //
                .csrf().disable()
                .authorizeRequests()
                //在这里不配置"/"路径下的访问权限会有一个access is denied的报错。
                //直接设置为allpermit的话，需要事先设置好"/"对应的页面

                //测试专用
                .antMatchers("/predict").permitAll()
                .antMatchers("/model").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/upload/loadMultiPic").permitAll()

                //测试用
                //经过测试，"/save"可以表示"save?xxx="，"/save/*"则不是
                .antMatchers("/register").permitAll()
                .antMatchers("/register/save").permitAll()
                .antMatchers("/register/save/*").permitAll()
                .antMatchers("/upload/load_test_pic").permitAll()

                .antMatchers("/", "/about", "/album", "/details", "/index"
                        , "/leacots", "/test_index", "/whisper").permitAll()

                .antMatchers("/", "/home", "/about", "/img/*", "/login/*").permitAll()//允许所有人访问
                .antMatchers("/admin/**", "/upload/**").hasAnyRole("ADMIN")//只允许admin访问
                .antMatchers("/order/**").hasAnyRole("USER","ADMIN")//允许两种身份访问
                .antMatchers("/room/**").hasAnyRole("USER","ADMIN")//允许两种身份访问
                //所有请求页面都需要登陆才能访问
                .anyRequest().authenticated()

                .and()
                //默认的登录页，允许所有人登陆
                .formLogin().loginPage("/login")
                .successForwardUrl("/home")
                .failureForwardUrl("/login?error")
                .permitAll()


                .and()
                .logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/?logout=true")
                //登出
                //按顺序，第一个等处url，框架会自动拦截url进行处理，不需要具体的实现
                //第二个url是实际的等处url，logout告知登陆状态

//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)//？？？做什么用的？？？？

                .and()
                .rememberMe()
                .tokenValiditySeconds(604800)//"记住我"功能，cookies有限期是一周
                .rememberMeParameter("remember-me")//登陆时是否激活记住我功能的参数名字，再登陆页面有展示
                .rememberMeCookieName("wheel-user-cookie");//cookies的名字，登陆后可以通过浏览器查看cookies名字


    }

    /**
     * 通过重载，配置user-detail服务
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
         * @Auther 李韬
         * @Date 2019/4/11 14:11
         *
         * @Description 启用内存用户存储，直接从内存中定义超级用户和一些一般用户
         * 在调试和测试阶段中使用该方式
         **/
        auth.inMemoryAuthentication()
                .withUser("user").password("123456").roles("USER")
                .and()
                .withUser("admin").password("123456").roles("USER", "ADMIN");

        /**
         * @Auther 李韬
         * @Date 2019/4/17 16:28
         *
         * @Description 配置密码的加密模式
         * 指定了密码加密模式之后，SpringSecurity会自动将密码加密后与数据库比对
         **/
        auth.userDetailsService(MyUserService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 配置Spring Security的Filter链
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
        web.ignoring()
                //在上面http方法中也可以实现
                //过滤一些静态资源和api请求
                .antMatchers("/api/**", "/**/*.css", "/**/*.jpg", "/**/*.png", "/**/*.js", "/**/*.ico");
    }
}
