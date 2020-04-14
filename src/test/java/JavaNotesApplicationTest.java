import com.notes.JavaNotesApplication;
import com.notes.service.UserService;
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

    @Test
    public void test() {
        // 通过SpringContextUtil获取上下文内容（即spring托管的）
//        UserService userService = (UserService) SpringContextUtil.getBean("userService");
        System.out.println(userService.getUser());
        System.out.println("yes");
    }
}