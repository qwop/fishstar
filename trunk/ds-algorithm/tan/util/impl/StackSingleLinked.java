package util.impl;

import dsa.exception.StackEmptyException;
import util.Stack;

/**
 * ��ջ ��ջ�������ʵ��
 * 
 * @author dolphin
 * 
 *         2012-1-13 ����9:11:59
 */
public class StackSingleLinked implements Stack {

	static class SSNode {
		Object element;
		SSNode next;

		public SSNode() {
			this(null, null);
		}

		public SSNode(Object ele, SSNode node) {
			element = ele;
			next = node;
		}
	}

	private SSNode top; // ͷ���Ϊ��Ԫ��
	private int size;

	public StackSingleLinked() {
		top = null;
		size = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.Stack#getSize()
	 */
	@Override
	public int getSize() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.Stack#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return 0 == size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.Stack#push(java.lang.Object)
	 */
	@Override
	public void push(Object e) {
		SSNode node = new SSNode(e, top);
		top = node;
		size++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.Stack#pop()
	 */
	@Override
	public Object pop() {
		if (getSize() < 1) {
			throw new StackEmptyException("���� ��ջΪ��");
		}
		Object ret = top.element;
		top.element = null;

		SSNode next = top.next;
		top.next = null;
		top = next;

		size--;
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.Stack#peek()
	 */
	@Override
	public Object peek() {
		if (getSize() < 1) {
			throw new StackEmptyException("���� ��ջΪ��");
		}
		return top.element;
	}
}
