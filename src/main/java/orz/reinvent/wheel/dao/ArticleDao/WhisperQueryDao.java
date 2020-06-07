package orz.reinvent.wheel.dao.ArticleDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import orz.reinvent.wheel.entity.Response.RWhisper;
import orz.reinvent.wheel.entity.SvmModelEntity;

import java.util.List;

@Repository
public interface WhisperQueryDao extends JpaRepository<SvmModelEntity, Integer> {

//    @Query(value = "select id, create_time, comment_times, thumbsup, content, img " +
//            "from whisper limit :page, :pagesize", nativeQuery = true)
//    List<RWhisper> selectWhisperByPage(@Param("page") int page, @Param("pagesize") int pagesize);
    @Query(value = "select id, create_time, comment_times, thumbsup, content, img " +
            "from whisper limit :page, :pagesize", nativeQuery = true)
    List<Object[]> selectWhisperByPage(@Param("page") int page, @Param("pagesize") int pagesize);
}
