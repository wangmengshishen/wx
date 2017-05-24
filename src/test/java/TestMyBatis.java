/*import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wm.project.bean.SysUser;
import com.wm.project.dao.SysUserDao;
import com.wm.project.dao.SysUserDaoImpl;


public class TestMyBatis {
	@Test
public void test(){
	String conf="spring/applicationContext-dao.xml";
    ApplicationContext ctx=new ClassPathXmlApplicationContext(conf); 
    System.out.println(ctx);
    SysUserDao sysUserDaoImpl=ctx.getBean("sysUserDao",SysUserDao.class);
    List<SysUser>list=sysUserDaoImpl.queryList("queryUserInfo", new HashMap<String, Object>());
    System.out.println(list.size());
}
}
*/