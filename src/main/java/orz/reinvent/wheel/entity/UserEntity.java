package orz.reinvent.wheel.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Collection;

/**
 * @ClassName UserEntity
 * @Description TODO
 * 实际的用户表，加上了权限控制
 *
 * 其中pic字段为保留字段，用来加上用户的图标什么的
 * @Author mushi
 * @Date 2019/4/915:38
 * @Version 1.0
 **/
@Entity
@Table(name="user")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
    private Long id;

    @Column(length = 32)
    private String username;

    @Column(columnDefinition = "varchar(255)")
    private String password;

    @Column(length = 32)
    private String roles;

    //用户头像
    @Column(length = 32)
    private String user_pic;

    //表明用户是否在线
    @Column(length = 1)
    private int isOn;

    /**
     * 根据一些博客的建议，不使用UserEntity作为UserDetails的实现类
     * 原因：
     * Entity原本只是用来形成ORM映射，
     * 如果使用Entity实现UserDetails接口，则要实现里面的一些功能，耦合度较高
     * 故，一般会重新写一个UserDetailsImpl类来实现UserDetails中的一些功能
     */

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", user_pic='" + user_pic + '\'' +
                ", isOn=" + isOn +
                '}';
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 从数据库中取出roles字符串后，进行分解，构成一个GrantedAuthority的List返回
     * 返回用户角色的封装，一个Role对应一个GrantedAuthority
     *
     * 在这里我采用的是单一授权，所以先暂时不管这个函数
     * @return
     */

//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        String[] authorities = roles.split(",");
//        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
//        for (String role : authorities){
//            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(role));
//        }
//        return simpleGrantedAuthorityList;
//    }

    //账户是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户是否冻结
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //账户密码是否过期，
    //一般有的密码要求性高的系统会使用到，比较每隔一段时间就要求用户重置密码
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账户是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }




    /**
     * 一个测试函数，用来生成加密后的密码
     * @param password
     * @return
     */
    public static String generatePassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
    public static void main(String[] args){
        System.out.println(generatePassword("123"));
    }
}
