package util;
/**
 * 堆栈 ( Stack ) 又称堆栈， 是运算受限的线性表
 * 栈顶 ( Top )
 * 栈底 ( Bottom )
 * 空栈 ()
 * 进栈/入栈
 * 出栈/退栈
 * LIFO: Last In First Out
 * 
 * @author dolphin
 *
 * 2012-1-12 下午5:22:05
 */
public interface Stack {
	/**
	 * 获取栈 的大小
	 * @return
	 */
	int getSize();
	
	/**
	 * 是否为空
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * 入栈
	 * @param e
	 */
	void push( Object e );
	
	/**
	 * 出栈
	 * @return
	 */
	Object pop();
	
	/**
	 * 取栈顶元素
	 * @return
	 */
	Object peek();
}
