package orz.reinvent.wheel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import orz.reinvent.wheel.entity.PicEntity;

/**
 * @ClassName PicDao
 * @Description TODO
 * @Author mushi
 * @Date 2019/6/280:07
 * @Version 1.0
 **/
@Repository
public interface PicDao extends JpaRepository<PicEntity, Integer> {


}
