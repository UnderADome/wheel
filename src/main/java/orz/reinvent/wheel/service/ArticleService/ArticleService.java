package orz.reinvent.wheel.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import orz.reinvent.wheel.dao.ArticleDao.ArticleQueryDao;
import orz.reinvent.wheel.entity.ArticleEntity.Article;
import orz.reinvent.wheel.entity.Response.RArticle;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleQueryDao articleQueryDao;

    public List<RArticle> articleQueryAll(){
        List<RArticle> result = articleQueryDao.selectAllArticle();
        return result;
    }

    /**
     * @Auther 李韬
     * @Date 2020/4/9 16:02
     *
     * @Description 新添pageable完成分页。jpa分页与mybatis有所区别
     * @Param [page, pagesize]
     * @return java.util.List<orz.reinvent.wheel.entity.Response.RArticle>
     **/
    public List<RArticle> selectArticleByPage(int page, int pagesize){
//        Pageable pageable = new PageRequest(page, pagesize); //写法过时
        Pageable pageable = PageRequest.of(page, pagesize);
        Page<RArticle> page_RArticle = articleQueryDao.selectArticleByPage(pageable);
        List<RArticle> list_RArticle = page_RArticle.getContent();

        return list_RArticle;
    }

    public RArticle selectArticleById(int id){
        RArticle result = articleQueryDao.selectArticleById(id);
        return result;
    }

}
