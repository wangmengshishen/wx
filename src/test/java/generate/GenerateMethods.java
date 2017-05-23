package generate;
public class GenerateMethods {
	/*
	 * 要定义泛型方法要将泛型参数列表置于返回值之前，如用类泛化泛型参数的类型不需要。
	 * */
	public <T>void getClassName(T t) {
		System.out.println(t.getClass().getName());
	}
	public static void main(String[] args) {
		GenerateMethods cm=new GenerateMethods();
		cm.getClassName("111");
	}
}
