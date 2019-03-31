package orz.reinvent.wheel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import orz.reinvent.wheel.entity.T_UserEntity;

import java.util.List;


/**
 * @Author 李韬
 * @Date 2019/3/5 9:36
 *
 * @Description 测试是否可以自动更新表格，
 *              测试是否可以连接数据库成功
 *
 *              在这里做测试用，写了dao就不改了，
 *              以后再创建其他的数据库操作文件的时候，就再后面加上后缀Repository
 * @Param
 * @return
 **/
@Repository
//@Transactional(readOnly = true)
//@NoRepositoryBean  这个注解是消掉@Repositorty的作用
public interface T_UserDao extends JpaRepository<T_UserEntity, Integer> {

    /**
     * 即使在这里没有写代码，也已经继承了
     * JapRepositoty里面的大量可以操作数据库的函数
     */


    default String ToDo(){
        System.out.println("测试Java8新特性->接口默认方法");
        return "测试Java8新特性->接口默认方法";
    }


    /**
     * ###在这里必须要注意一点：###
     * from table，这里的table必须是我们自己定义的Entity的全称
     * 即使我们已经在Entity中定义了别名，也仍然要写Entity的全名
     * 不然会报错is not mapped
     *
     * 而且如果需要数据库表格自动更新并保持数据，需要使用
     * spring.jpa.hibernate.ddl-auto=update
     * 否则，表不会自动生成，同样会报错。需要自己手动创建表格
     *
     * @param id
     * @return
     */
    @Query("from T_UserEntity t where t.id = id")
    T_UserEntity findUser(@Param("id")Long id);

    /**
     * 使用原生的SQL语句进行查询
     * @param id
     * @return
     */
    @Query(value = "select * from T_UserEntity t where t.id = id", nativeQuery = true)
    T_UserEntity findUser_1(@Param("id")Long id);

    /**
     * 接口中原生的方法，没有经过任何处理
     * @return
     */
    @Override
    List findAll();


    /**
     * 在使用findBy方法的时候，
     * 要使用数组的方式去获取数据库中的数据。
     * 因为findBy从数据库中查询到多条数据的时候，
     * 当接受的对象时单个对象则会报错。
     *
     * @param account
     * @return
     */
    List<T_UserEntity>[] findByAccount(String account);


    /**
     *
     * @param id
     * @param name
     * @return
     */
    T_UserEntity findByIdAndName(Long id, String name);

}
