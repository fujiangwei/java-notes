import com.notes.JavaNotesApplication;
import com.notes.service.UserService;
import com.notes.utils.RedisDistributeLock;
import com.notes.utils.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 文件描述 测试类
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JavaNotesApplication.class)
public class JavaNotesApplicationTest {

    @Autowired
    UserService userService;

    @Autowired
    private RedisDistributeLock redisDistributeLock;

    @Test
    public void test() {
        // 通过SpringContextUtil获取上下文内容（即spring托管的）
        UserService userService2 = SpringContextUtil.getBean("userService");
        UserService userService3 = SpringContextUtil.getBean("userService", UserService.class);
        UserService userService4 = SpringContextUtil.getBean(UserService.class);
        System.out.println(userService2.getUser() + userService3.getUser() + userService4.getUser());
    }

    @Test
    public void redisDistributeLockTest() {
        boolean tryLock = redisDistributeLock.tryLock();
        if (tryLock) {
            System.out.println("获取锁成功");
        } else {
            System.out.println("获取锁失败");
        }

        boolean releaseLock = redisDistributeLock.releaseLock();
        if (releaseLock) {
            System.out.println("释放锁成功");
        } else {
            System.out.println("释放锁失败");
        }
    }
}