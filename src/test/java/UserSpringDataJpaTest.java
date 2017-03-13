import com.spring.jpa.model.Address;
import com.spring.jpa.model.User;
import com.spring.jpa.repository.AddressRepository;
import com.spring.jpa.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * UserSpringDataJpaTest
 *
 * @author guisheng.deng
 * @date 2017年03月10日
 * @reviewer
 * @description
 * @see
 */
@RunWith(SpringJUnit4ClassRunner.class)
/** 声明spring主配置文件位置，注意：以当前测试类的位置为基准,有多个配置文件以字符数组声明 **/
@ContextConfiguration(locations = "classpath:applicationContext.xml")
/** 声明使用事务，不声明spring会使用默认事务管理 **/
public class UserSpringDataJpaTest {
    /*@Resource
  private pageervice pageervice;

  @Test
  public  void add(){
      User user=new User();
      user.setPassword("12131231");
      user.setUsername("adminafefafefsefsefs");
      user.setAge(34);
      user.setEmail("23423@qq.com.cn");
      user.setSex("男");

  }
  @Test
  public void getAll(){
     pageervice.showUserList("admin","12131231");
  }
  @Test
  public void testPage(){
      PageRequest pageRequest=new PageRequest(1,2);
      Page<User> page=pageervice.getAllUserByPage(pageRequest);
      System.out.println("查询结果：共"+page.getTotalElements()+"条数据，每页显示"+page.getSize()+"条，"
              +"共"+page.getTotalPages()+"页，当前第"+(page.getNumber()+1)+"页！");
  }
  @Test
  public void testUser(){
      User user=pageervice.getUser(34);
      System.out.println(user);

  }*/
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testAddUser() {

        User user = new User();
        user.setPassword("12131231");
        user.setUsername("dgs");
        user.setAge(23);
        user.setEmail("23423@qq.com.cn");
        user.setSex("男");
        Address address = new Address();
        address.setCountry("China");
        address.setState("四川省");
        address.setStreet("中和下街");
        user.setAddress(address);
        addressRepository.save(address);
        userRepository.save(user);
    }

    @Test
    public void testFindUser() {
        User user = userRepository.findUserByUsername("afweffsfes");
        System.out.println(user);
        System.out.println(user.getAddress().getCountry());

    }

    @Test
    public void testLong() {
        long count = userRepository.countByUsername("admin");
        System.out.println(count);
    }

    @Test
    @Transactional
    public void testDeleteLong() {
        long count = userRepository.deleteByUsername("adminafefafefsefsefs");
        System.out.println(count);
    }

    @Test
    public void testFirst2Results() {
        List<User> lists = userRepository.queryFirst2ByUsername("admin");
        for (User user : lists) {
            System.out.println(user);
        }
    }

    @Test
    public void testFirst() {
        User user = userRepository.queryTopByOrderByAge();
        System.out.println(user);
    }

    @Test
    public void testPage() {
        Page<User> users = userRepository.queryTop3ByUsername("admin", new PageRequest(2, 3));
        System.out.println("查询结果：共" + users.getTotalElements() + "条数据，每页显示" + users.getSize() + "条，"
                + "共" + users.getTotalPages() + "页，当前第" + (users.getNumber() + 1) + "页");//page参数：表示当前页数
    }

    @Test
    public void testSort() {
        String[] str = {"username", "email"};
        List<User> lists = userRepository.findFirst3ByUsername("admin", new Sort(str));
        for (User user : lists) {
            System.out.println(user);
        }
    }

    @Test
    public void testSortAsc() {
        List<User> lists = userRepository.findTopThree("admin");
        for (User user : lists) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindOne() {
        Future<User> users = userRepository.findByUsername("dgs");
        System.out.println(users);
        //Pageable pageable=new PageRequest(1,5);
    }

    @Transactional
    @Test
    public void setUpdateUsername() {
        int influence = userRepository.setFixedUsernameFor("wangwu", 5l);
        if (influence > 0) {
            System.out.println("修改成功");
        }
    }
}