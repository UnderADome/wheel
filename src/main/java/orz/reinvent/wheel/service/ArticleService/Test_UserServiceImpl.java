package orz.reinvent.wheel.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import orz.reinvent.wheel.dao.ArticleDao.Test_UserQueryAll;
import orz.reinvent.wheel.entity.ArticleEntity.Test_User;

import java.util.List;


@Service
public class Test_UserServiceImpl implements  Test_UserService {

    @Autowired
    private Test_UserQueryAll test_userQueryAll;
    @Override
    public List<Test_User> queryUserByUserName(String username) {
        List<Test_User> list = test_userQueryAll.selectUser(username);
        System.out.println(list);
        return list;
    }
}
