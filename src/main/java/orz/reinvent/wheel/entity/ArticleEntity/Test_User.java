package orz.reinvent.wheel.entity.ArticleEntity;
import javax.persistence.*;
@Entity
@Table(name = "test_user")
public class Test_User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
    private Integer id;
    private String username;
    private Integer age;
    private Integer customerid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }


    //继承toString方法，方便调试打印输出
    @Override
    public String toString() {
        return "Test_User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", customerid=" + customerid +
                '}';
    }
}
