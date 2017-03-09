import com.spring.jpa.model.User;
import com.spring.jpa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * UserSpringDataJpaTest
 *
 * @author guisheng.deng
 * @date 2017年03月08日
 * @reviewer
 * @see
 */
/** 声明用的是Spring的测试类 **/
@RunWith(SpringJUnit4ClassRunner.class)
/** 声明spring主配置文件位置，注意：以当前测试类的位置为基准,有多个配置文件以字符数组声明 **/
@ContextConfiguration(locations = "classpath:applicationContext.xml")
/** 声明使用事务，不声明spring会使用默认事务管理 **/

public class UserSpringDataJpaTest {
    @Resource
    protected UserService userService;

    @Test
    public  void add(){
        User user=new User();
        user.setPassword("12131231");
        user.setUsername("admin");
        user.setAge(34);
        user.setEmail("23423@qq.com.cn");
        user.setSex("男");

    }
    @Test
    public void getAll(){
       userService.showUserList("admin","12131231");
    }
}