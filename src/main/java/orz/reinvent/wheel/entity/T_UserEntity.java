package orz.reinvent.wheel.entity;

import javax.persistence.*;

/**
 * @Entity声明这个类对应了一个数据库表
 *
 * @Table是一个可选的注解，声明了数据库实体对应的表信息
 * （包括表名、索引信息登）
 *
 * @GeneratedValue(strategy = GenerationType.AUTO)
 * 用以标注主键自增的模式
 *
 * 表注解不能加在方法上面，只能加在属性上面。
 */

//@Repository  //不能再这里用
@Entity
@Table(name="t_user")
public class T_UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
    private Long id;

    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String account;

    @Column(length = 64)
    private String pwd;


    /**
     * 使用lombok插件引入lombok中的注解，可以进一步缩减代码
     * @Getter @Setter @ToString可以省区以下代码
     */
/*
#######################################
#######################################
 */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "T_UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}