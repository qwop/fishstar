package util.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import util.Strategy;

import dsa.exception.OutOfBoundaryException;

/**
 * 单向链表
 * 
 * 只能通过直接前驱找到后续节点 
 * 无法从后续节点找到 前驱节点 查找 插入 删除 操作
 * 			时间比较	空间比较
 * 链式表	
 * 顺序表
 * 
 * @author dolphin
 * 
 *         2012-1-11 下午3:26:24
 */
public class ListSingleLinked implements List {
	static class Node {
		private Object data;

		/**
		 * @param data
		 *            the data to set
		 */
		public void setData(Object data) {
			this.data = data;
		}

		/**
		 * @return the data
		 */
		public Object getData() {
			return data;
		}

		private Node next;

		/**
		 * @param next
		 *            the next to set
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		public Node() {
			this(null, null);
		}

		public Node(Object e, Node n) {
			data = e;
			next = n;
		}

		public Node getNext() {
			return next;
		}
	}

	private Strategy strategy;
	private Node head;
	private int size;

	// ////////// Constructor Method ////////////
	public ListSingleLinked() {
		this(new StrategyDefault());
	}

	public ListSingleLinked(Strategy strategy) {
		this.strategy = strategy;
		head = new Node();
		size = 0;
	}

	// ////////// Implement Method ////////////
	/*
	 * @see java.util.List#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return 0 == size;
	}

	/*
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object e) {
		Node p = head.getNext();
		while (p != null)
			if (strategy.equal(p.getData(), e))
				return true;
			else
				p = p.getNext();
		return false;
	}

	/*
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator iterator() {
		return null;
	}

	/*
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() {
		return null;
	}

	/*
	 * @see java.util.List#toArray(T[])
	 */
	@Override
	public Object[] toArray(Object[] a) {
		return null;
	}

	/*
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(Object e) {
		Node p = head;
		while (null != p) {
			if (p.getNext() == null) {
				Node n = new Node();
				n.setData(e);
				p.setNext(n);
				size++;
				return true;
			} else {
				p = p.getNext();
			}
		}
		return false;
	}

	/*
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object o) {
		Node p = head;
		while (null != p) {
			if (p.getNext() != null) {
				if (strategy.equal(p.getNext().getData(), o)) {
					p.setNext(p.getNext().getNext());
					size--;
					return true;
				}
			}
			p = p.getNext();
		}
		return false;
	}

	/*
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection c) {
		return false;
	}

	/*
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection c) {
		return false;
	}

	/*
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection c) {
		return false;
	}

	/*
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection c) {
		return false;
	}

	/*
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection c) {
		return false;
	}

	/*
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear() {
		head = new Node();
		size = 0;
	}

	/*
	 * 单链表的查找操作 从链表的首结点开始，通过每个节点的next引用来一次访问链表中的每一个节点完成相应的查找操作
	 * 
	 * @see java.util.List#get(int)
	 */
	@Override
	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new OutOfBoundaryException("错误，指定的插入序号越界。");
		}
		Node p = head;
		int count = 0;
		while (null != p) {
			if (p.getNext() != null) {
				if (count == index) {
					return p.getNext().getData();
				}
			}
			p = p.getNext();
			count++;
		}
		return null;
	}

	/*
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public Object set(int index, Object element) {
		if (index < 0 || index > size) {
			throw new OutOfBoundaryException("错误，指定的插入序号越界。");
		}
		Node p = head;

		int count = 0;
		while (null != p) {
			if (count == index) {
				if (p.getNext() != null) {
					Node oldNext = p.getNext();
					Node node = new Node();
					node.setData(element);
					node.setNext(oldNext);
					p.setNext(node);
					size++;
					return element;
				} else {
					Node node = new Node();
					node.setData(element);
					p.setNext(node);
					size++;
					return element;
				}
			}
			p = p.getNext();
			count++;
		}
		return null;
	}

	/*
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, Object element) {
		if (index < 0 || index > size) {
			throw new OutOfBoundaryException("错误，指定的插入序号越界。");
		}
		Node p = head;
		int count = 0;
		while (null != p) {
			if (count == index) {
				if (p.getNext() != null) {
					Node oldNext = p.getNext();
					Node node = new Node();
					node.setData(element);
					node.setNext(oldNext);
					p.setNext(node);
					size++;
					return;
				} else {
					Node node = new Node();
					node.setData(element);
					p.setNext(node);
					size++;
					return;
				}
			}
			p = p.getNext();
			count++;
		}
	}

	/*
	 * @see java.util.List#remove(int)
	 */
	@Override
	public Object remove(int index) {
		if (index < 0 || index > size) {
			throw new OutOfBoundaryException("错误，指定的插入序号越界。");
		}
		Node p = head;
		int count = 0;
		while (null != p) {
			if (p.getNext() != null) {
				if (index == count) {
					p.setNext(p.getNext().getNext());
					size--;
					return true;
				}
			}
			p = p.getNext();
			count++;
		}
		return false;
	}

	/*
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		if (size == 0) {
			return -1;
		}
		Node p = head;
		int count = 0;
		while (p != null) {
			if (strategy.equal(p.next.getData(), o)) {
				return count;
			}
			count++;
			p = p.next;
		}
		return -1;
	}

	/*
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator listIterator(int index) {
		return null;
	}

	/*
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List subList(int fromIndex, int toIndex) {
		return null;
	}

}
