package orz.reinvent.wheel.dao.ArticleDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import orz.reinvent.wheel.entity.ArticleEntity.Test_User;
import orz.reinvent.wheel.entity.SvmModelEntity;

import java.util.List;

@Repository  //这个注解必须用不用则service报错。大概是用来作为bean注入的
public interface Test_UserQueryAll extends JpaRepository<SvmModelEntity, Integer> {

    @Query(value = "SELECT * FROM test_user WHERE username = :username", nativeQuery = true)
    List<Test_User> selectUser(@Param("username") String username);

}
