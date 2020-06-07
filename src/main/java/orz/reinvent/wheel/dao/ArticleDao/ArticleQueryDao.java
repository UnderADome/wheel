package orz.reinvent.wheel.dao.ArticleDao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import orz.reinvent.wheel.entity.ArticleEntity.Article;
import orz.reinvent.wheel.entity.Response.RArticle;
import orz.reinvent.wheel.entity.SvmModelEntity;

import java.util.List;

@Repository
public interface ArticleQueryDao extends CrudRepository<Article, Integer> {

    @Query(value = "select article.id, article_item.item, article.title, article.article_content, article.pic from " +
            "article, article_item where article.item = article_item.id", nativeQuery=true)
    List<RArticle> selectAllArticle();

//    @Select("select article.id, article_item.item, article.title, article.article_content, article.pic from " +
//            "article, article_item where article.item = article_item.id")
//    Page<RArticle> selectArticleByPage();

    /**
     * JPA中的sql其实是jpql，不支持limit函数，只有原生的sql才支持。
     * 因此，要加上nativeQuery=true来支持原生sql
     *
     * 另外，limit仅存在于少数数据库中（mysql等），不支持Oracle；
     * 在其他数据库环境下可能抛出QuerySyntaxException unexpected token: limit
     *
     * 在JPA中限制返回的记录数，案例：
     * Query query = entityManager.createQuery("select g from Entity g where g.codeUrl = :codeUrl ORDER BY g.createTime DESC");
     * 		query.setParameter("codeUrl", codeUrl);
     * 		return (Entity) query.setMaxResults(1).getSingleResult();// 仅返回一条记录
     *                 // query.setMaxResults(5).getResultList(); // 返回多条
     */

//    List更换为Page
//    @Query(value = "select article.id, article_item.item, article.title, article.article_content, article.pic from " +
//        "article, article_item where article.item = article_item.id limit :page, :pagesize", nativeQuery=true)
//    List<RArticle> selectArticleByPage(@Param("page") int page, @Param("pagesize") int pagesize);

    /*
     * @Auther 李韬
     * @Date 2020/4/9 17:46
     *
     * @Description 被这种方式蠢到了。。。
     * Article Article_item两个Entity不能直接注入进来
     * @Param [pageable]
     * @return org.springframework.data.domain.Page<orz.reinvent.wheel.entity.Response.RArticle>
     **/
    @Query("select new orz.reinvent.wheel.entity.Response.RArticle" +
            "(a.id, ai.item, a.title, a.article_content, a.pic) " +
            "from orz.reinvent.wheel.entity.ArticleEntity.Article a, " +
            "orz.reinvent.wheel.entity.ArticleEntity.Article_item ai where a.item = ai.id")
    Page<RArticle> selectArticleByPage(Pageable pageable);

    @Query(value = "select article.id, article_item.item, article.title, " +
            "article.article_content, article.pic, article.user_id, article.create_time " +
            "from article, article_item  where article.item = article_item.id and article.id = :id", nativeQuery=true)
    RArticle selectArticleById(int id);
    //RArticle
}
