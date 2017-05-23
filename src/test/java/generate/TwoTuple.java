package generate;
import com.wm.project.bean.SysUser;

/**
 * 元组类型
 * @author wang
 *
 * @param <A>
 * @param <B>
 */
public class TwoTuple<A,B> {
private final A a;
private final B b;
public TwoTuple(A a, B b) {
	this.a = a;
	this.b = b;
};
/**
 * 返回元组类型
 * @return
 */
public static TwoTuple<SysUser,String> getTuple(){
	SysUser user=new SysUser();
	user.setGender("rose");
	user.setAge(10);
	TwoTuple<SysUser,String> teop=new TwoTuple<SysUser,String>(user ,"111");
	return teop;
}
public static void main(String[] args) {
	TwoTuple<SysUser,String> teop=TwoTuple.getTuple();
System.out.println(teop.a.getGender());
System.out.println(teop.b);
}
}
