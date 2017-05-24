/*import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wm.project.bean.SysUser;
import com.wm.project.service.SenderService;
public class TestActiveMQ {
    @Test
	public void test(){
	String conf="spring/activityMq.xml";
	ApplicationContext ctx=new ClassPathXmlApplicationContext(conf);
	SenderService sendService=ctx.getBean("senderServiceImpl",SenderService.class);
	SysUser user=new SysUser();
	user.setName("jack");
	user.setAge(20);
	user.setGender("F");
	sendService.addQueue(user);
}
}
*/