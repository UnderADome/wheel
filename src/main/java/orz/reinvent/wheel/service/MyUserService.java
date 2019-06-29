package orz.reinvent.wheel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orz.reinvent.wheel.dao.UserDao;
import orz.reinvent.wheel.entity.UserEntity;

/**
 * @ClassName MyUserService
 * @Description TODO
 * 自定义的UserDetailsService查询数据库中用户的信息
 *
 * 这里必须要实现框架提供的UserDetailsService接口
 * 这个接口里面定义的LoadUserByUsername也是必须实现的（框架提供的获取用户的服务）
 * @Author 李韬
 * @Date 2019/4/1515:15
 * @Version 1.0
 **/
@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    /**
     * 这里要返回的是UserDetails类型，UserEntity已经实现了UserDetails
     * 故，可以直接返回查询的结果
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*
        正如前面的UserEntity注释里面说的问题：
        一般来说，role和user是分为两张表进行存储的，同时还需要一个sysuser来同时集合role与user
        由于我这里写的是user里面直接用字符串代替的role，故：
        本函数中最终返回的就是userEntity
         */


        UserEntity userEntity = userDao.findAllByUsername(username);
        if (userEntity == null){
            throw new UsernameNotFoundException(username);
        }

        return userEntity;
    }


    /**
     * 插入一个新的用户
     */
    @Transactional
    public void insert(UserEntity userEntity) {
        //对密码进行加密处理
        String password = userEntity.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        //加密后的密码重新放入Entity中存储到数据库
        System.out.println("产生的新密码："+password);
        userEntity.setPassword(password);

        userDao.save(userEntity);

    }
}
