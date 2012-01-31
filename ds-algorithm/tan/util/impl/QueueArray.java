package util.impl;

import util.Queue;


public class QueueArray implements Queue {
	// 队列的默认的大小
	private static final int CAP = 7;
	
	// 数据元素数组
	private Object[] elements;
	
	// 数组的大小 elements.length
	private int capacity;
	
	// 队首指针，指向队首
	private int front;
	
	// 队尾指针，指向队尾
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
		front = 0; rear = j;// 设置新的队首、队尾指针
	}

	/* (non-Javadoc)
	 * @see util.Queue#dequeue()
	 */
	@Override
	public Object dequeue() {
		if ( isEmpty() ) {
			throw new RuntimeException("错误：队列为空！");
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
			throw new RuntimeException("错误：队列为空！");
		}
		return elements[ front ];
	}
	
}
