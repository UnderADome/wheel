package orz.reinvent.wheel.entity;

import javax.persistence.*;

/**
 * @ClassName SvmModelEntity
 * @Description SVM_HOG分类模型的存储
 * @Author mushi
 * @Date 2019/6/289:24
 * @Version 1.0
 **/
@Entity
@Table(name="svmmodel")
public class SvmModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
    private int id;

    @Column(length = 32)
    private String name;

    @Column(length = 64)
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SvmModelEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
