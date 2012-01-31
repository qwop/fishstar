package util.impl;

import util.Queue;


public class QueueArray implements Queue {
	// ���е�Ĭ�ϵĴ�С
	private static final int CAP = 7;
	
	// ����Ԫ������
	private Object[] elements;
	
	// ����Ĵ�С elements.length
	private int capacity;
	
	// ����ָ�룬ָ�����
	private int front;
	
	// ��βָ�룬ָ���β
	private int rear;
	
	public QueueArray() {
		this( CAP );
	}
	
	public QueueArray( int cap ) {
		capacity = cap + 1;
		
		elements = new Object[ capacity ];
		
		front = rear = 0;
	}
	
	
	/* (non-Javadoc)
	 * @see util.Queue#size()
	 */
	@Override
	public int size() {
		return ( rear - front + capacity ) % capacity ;
	}

	/* (non-Javadoc)
	 * @see util.Queue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return front == rear;
	}

	/* (non-Javadoc)
	 * @see util.Queue#enqueue(java.lang.Object)
	 */
	@Override
	public boolean enqueue(Object e) {
		if ( size() == capacity - 1 ) {
			expandSpace();
		}
		
		elements[ rear ] = e;
		
		rear = ( rear + 1 ) % capacity;
		
		return true;
	}

	private void expandSpace() {
		Object[] temps = new Object[ elements.length * 2 ];
		int i = front; int j = 0;
		while ( i != rear ) {
			temps[ j++ ] = elements[ i ];
			i = ( i + 1 ) % capacity;
		}
		elements = temps;
		capacity = elements.length;
		front = 0; rear = j;// �����µĶ��ס���βָ��
	}

	/* (non-Javadoc)
	 * @see util.Queue#dequeue()
	 */
	@Override
	public Object dequeue() {
		if ( isEmpty() ) {
			throw new RuntimeException("���󣺶���Ϊ�գ�");
		}
		Object obj = elements[ front ];
		elements[ front ] = null;
		front = ( front + 1 ) % capacity;
		return obj;
	}

	/* (non-Javadoc)
	 * @see util.Queue#peek()
	 */
	@Override
	public Object peek() {
		if ( isEmpty() ) {
			throw new RuntimeException("���󣺶���Ϊ�գ�");
		}
		return elements[ front ];
	}
	
}
