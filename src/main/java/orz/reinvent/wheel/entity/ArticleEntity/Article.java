package orz.reinvent.wheel.entity.ArticleEntity;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
    private Integer id;

    private Integer item;

    private String title;

    private String article_content;

    private String pic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", item=" + item +
                ", title='" + title + '\'' +
                ", article_content='" + article_content + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
