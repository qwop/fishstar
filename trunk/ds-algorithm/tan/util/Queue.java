package util;
/**
 * ���м�ƶ� (Queue)
 * ͬ��ջһ����һ�����������Ƶ����Ա�
 * ���һ�˽��в��룬�����һ�˽���ɾ��
 * ��β( rear ) : ��������Ԫ�ص�һ��
 * ����( front ) : ɾ������Ԫ�ص�һ��
 * 
 * ����
 * ����
 * 
 * �Ƚ��ȳ���(FIFO): First In First Out
 * @author dolphin
 *
 * 2012-1-30 ����2:40:52
 */
public interface Queue {
	/**
	 * ��ȡ���еĴ�С
	 * @return
	 */
	int size() ;
	/**
	 * �ж϶����Ƿ�Ϊ��
	 * @return
	 */
	boolean isEmpty();
	/**
	 * ��Ԫ������У������ڶ�β
	 * @param obj
	 * @return
	 */
	boolean enqueue( final Object obj );
	/**
	 * ��Ԫ�س���,���׵�Ԫ��ɾ�� 
	 * @return
	 */
	Object dequeue();
	
	/**
	 * ���ض��׵�Ԫ�� ���ǲ�ɾ��Ԫ��
	 * @return
	 */
	Object peek();
}
