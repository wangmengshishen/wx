package generate;

public class LinkedStack<T> {
	/**
	 * ，static作为静态成员变量和成员函数的修饰符，意味着它为该类的所有实例所共享，
	 * 也就是说当某个类的实例修改了该静态成员变量，其修改值为该类的其它所有实例所见。
	 *  如果一个类要被声明为static的，只有一种情况，就是静态内部类。如果在外部类声明为static，程序会编译都不会过。
	 *  1.静态内部类跟静态方法一样，只能访问静态的成员变量和方法，不能访问非静态的方法和属性，但是普通内部类可以访问任意外部类的成员变量和方法
     *  2.静态内部类可以声明普通成员变量和方法，而普通内部类不能声明static成员变量和方法。
	 * @author wang
	 *
	 * @param <U>
	 */
private static class Node<U>{
	U item;
	Node<U>next;
	Node(){
		item=null;
		next=null;
	}
	Node(U item,Node<U>next){
		this.item=item;
		this.next=next;
	}
	boolean end(){return item==null&&next==null;}
}
	private Node<T>top=new Node<T>();
	public void push(T item){
		top=new Node<T>(item,top);
	}
	public T pop(){
		T result=top.item;
		if(!top.end()){
			top=top.next;
		}
		return result;
	}
	public static void main(String[] args) {
		LinkedStack<String>lss=new LinkedStack<String>();
				for(String s:"abc defg hijk 111".split(" ")){
					lss.push(s);
				}
				String s;
				while((s=lss.pop())!=null){
					System.out.println(s);
				}
	}
}
