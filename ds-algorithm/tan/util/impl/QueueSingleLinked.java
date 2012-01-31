package util.impl;

import util.Queue;

/**
 * 单链表实现 队列
 * @author dolphin
 *
 * 2012-1-31 下午5:03:13
 */
public class QueueSingleLinked implements Queue {
	private SNode front;
	private SNode rear;
	
	private int size;
	
	public QueueSingleLinked() {
		front = new SNode();
		
		rear = front;
		
		size = 0;
	}
	/* (non-Javadoc)
	 * @see util.Queue#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see util.Queue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/* (non-Javadoc)
	 * @see util.Queue#enqueue(java.lang.Object)
	 */
	@Override
	public boolean enqueue(Object e) {
		SNode p = new SNode( e, null );
		rear.next = p;
		
		rear = p;
		size++;
		return false;
	}

	/* (non-Javadoc)
	 * @see util.Queue#dequeue()
	 */
	@Override
	public Object dequeue() {
		if ( size() < 1 ) {
			throw new RuntimeException( "错误: 队列为空！" );
		}
		
		SNode p = front.next;
		front.next = p.next;
		size--;
		
		if ( size < 1 ) {
			rear = front;
		}
		
		return p.element;
	}

	/* (non-Javadoc)
	 * @see util.Queue#peek()
	 */
	@Override
	public Object peek() {
		if ( size() < 1 ) {
			throw new RuntimeException( "错误: 队列为空！" );
		}
		
		return front.next.element;
	}
	
	static class SNode {
		public SNode() {
		}
		
		public SNode( Object e , SNode next ) {
			this.element = e;
			this.next = next;
		}
		
		Object element;
		SNode next;
	}
}
