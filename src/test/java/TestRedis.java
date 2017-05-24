/*import org.junit.Test;

import com.wm.project.bean.SysUser;
import com.wm.project.common.redis.cluster.JedisClusterClient;
import com.wm.project.common.redis.cluster.ObjectsTranscoder;


public class TestRedis {
    @Test
	public void test(){
	SysUser user=new SysUser();
	user.setName("jack");
	user.setAge(30);
	user.setGender("F");
	user.setId(1);
	String result=JedisClusterClient.setex("user".getBytes(), 100, new ObjectsTranscoder<SysUser>().serialize(user));
	System.out.println(result);
	System.out.println(new ObjectsTranscoder<SysUser>().deserialize(JedisClusterClient.get("user".getBytes())).getName());
}
}
*/