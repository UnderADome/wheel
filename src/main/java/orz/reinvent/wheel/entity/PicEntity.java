package orz.reinvent.wheel.entity;

import javax.persistence.*;

/**
 * @ClassName PicEntity
 * @Description TODO
 * @Author mushi
 * @Date 2019/6/280:07
 * @Version 1.0
 **/
@Entity
@Table(name="pic")
public class PicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
    private int id;

    @Column(length = 32)
    private String modelName;

    @Column(length = 32)
    private String picUrl;

    @Column(length = 32)
    private int picLabel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getPicLabel() {
        return picLabel;
    }

    public void setPicLabel(int picLabel) {
        this.picLabel = picLabel;
    }

    @Override
    public String toString() {
        return "PicEntity{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", picLabel=" + picLabel +
                '}';
    }
}
