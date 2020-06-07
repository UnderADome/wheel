package orz.reinvent.wheel.service.ArticleService;

import orz.reinvent.wheel.entity.ArticleEntity.Test_User;

import java.util.List;

public interface Test_UserService {

    //为什么这里用的是接口？？
    //@Autowired
    public List<Test_User> queryUserByUserName(String username);


}
