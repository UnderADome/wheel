package orz.reinvent.wheel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import orz.reinvent.wheel.entity.SvmModelEntity;

/**
 * @ClassName SvmModelDao
 * @Description TODO
 * @Author mushi
 * @Date 2019/6/289:28
 * @Version 1.0
 **/
@Repository
public interface SvmModelDao extends JpaRepository<SvmModelEntity, Integer> {

     /**
      * @Auther 李韬
      * @Date 2019/6/29 12:31
      *
      * @Description
      * @Param [id]
      * @return java.lang.String
      **/
     @Query(value = "select url from svmmodel t where t.id = id", nativeQuery = true)
    String findUrlById(int id);
}
