package util;
/**
 * 队列简称队 (Queue)
 * 同堆栈一样是一种运算受限制的线性表
 * 表的一端进行插入，表的另一端进行删除
 * 队尾( rear ) : 插入数据元素的一端
 * 队首( front ) : 删除数据元素的一端
 * 
 * 进队
 * 出队
 * 
 * 先进先出表(FIFO): First In First Out
 * @author dolphin
 *
 * 2012-1-30 下午2:40:52
 */
public interface Queue {
	/**
	 * 获取队列的大小
	 * @return
	 */
	int size() ;
	/**
	 * 判断队列是否为空
	 * @return
	 */
	boolean isEmpty();
	/**
	 * 将元素入队列，排列在队尾
	 * @param obj
	 * @return
	 */
	boolean enqueue( final Object obj );
	/**
	 * 将元素出队,队首的元素删除 
	 * @return
	 */
	Object dequeue();
	
	/**
	 * 返回队首的元素 但是不删除元素
	 * @return
	 */
	Object peek();
}
