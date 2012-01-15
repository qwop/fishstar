package util;
/**
 * ��ջ ( Stack ) �ֳƶ�ջ�� ���������޵����Ա�
 * ջ�� ( Top )
 * ջ�� ( Bottom )
 * ��ջ ()
 * ��ջ/��ջ
 * ��ջ/��ջ
 * LIFO: Last In First Out
 * 
 * @author dolphin
 *
 * 2012-1-12 ����5:22:05
 */
public interface Stack {
	/**
	 * ��ȡջ �Ĵ�С
	 * @return
	 */
	int getSize();
	
	/**
	 * �Ƿ�Ϊ��
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * ��ջ
	 * @param e
	 */
	void push( Object e );
	
	/**
	 * ��ջ
	 * @return
	 */
	Object pop();
	
	/**
	 * ȡջ��Ԫ��
	 * @return
	 */
	Object peek();
}
