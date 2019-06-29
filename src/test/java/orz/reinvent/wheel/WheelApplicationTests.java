//package orz.reinvent.wheel;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import orz.reinvent.wheel.dao.T_UserDao;
//import orz.reinvent.wheel.entity.T_UserEntity;
//import orz.reinvent.wheel.service.MyUserService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class WheelApplicationTests {
//
//	@Test
//	public void contextLoads() {
//	}
//
//
//	@Autowired
//	private T_UserDao t_userDao;
//
//	@Test
//	public void test(){
//		Integer a = 1;
//		Long id = a.longValue();
//		System.out.println(t_userDao.findUser(id));
//		t_userDao.ToDo();
//
//
//		T_UserEntity t_userEntity = new T_UserEntity();
//
//		t_userEntity.setId(null);
//		t_userEntity.setAccount("1233212");
//		t_userEntity.setName("刘德华");
//		t_userEntity.setPwd("123");
////		t_userDao.save(new T_UserEntity(id, "litao", "1001", "123456"));
//		t_userDao.save(t_userEntity);
//
//	}
//
//	@Test
//	public void test1(){
//		System.out.println(t_userDao.findAll());
//		System.out.println(t_userDao.findByAccount("1233212"));
//	}
//
//	@Test
//	public void test2(){
//		Integer a = 1;
//		Long id = a.longValue();
//		System.out.println(t_userDao.findByIdAndName(id, "刘德华"));
//	}
//
//
//
//	@Autowired
//	private MyUserService myUserService;
//	@Test
//	public void testMyUserService(){
//		System.out.println(myUserService.loadUserByUsername("user"));
//	}
//
//
//
//
//}
