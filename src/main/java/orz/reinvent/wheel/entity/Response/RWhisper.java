package orz.reinvent.wheel.entity.Response;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
@Repository
public class RWhisper {
    private Integer id;
    private Timestamp create_time;
    private Integer comment_times;
    private Integer thumbsup;
    private String content;
    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Integer getComment_times() {
        return comment_times;
    }

    public void setComment_times(Integer comment_times) {
        this.comment_times = comment_times;
    }

    public Integer getThumbsup() {
        return thumbsup;
    }

    public void setThumbsup(Integer thumbsup) {
        this.thumbsup = thumbsup;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "RWhisper{" +
                "id=" + id +
                ", create_time=" + create_time +
                ", comment_times=" + comment_times +
                ", thumbsup=" + thumbsup +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
