package orz.reinvent.wheel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orz.reinvent.wheel.entity.UserEntity;

/*
 * @Auther 李韬
 * @Date 2019/4/10 22:11
 *
 * @Description
 * @Param 
 * @return
 **/
@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {


    //通过用户名查找用户的全部信息
    UserEntity findAllByUsername(String username);

    //通过用户名查找用户的密码
    String findPasswordByUsername(String username);

    //插入一条新的用户信息
    //直接调用save方法即可



}
