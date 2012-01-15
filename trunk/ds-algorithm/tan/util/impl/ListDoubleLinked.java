package util.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 双向链表
 * 
 * 
 * 单链表的优点是结构简单，
 * 但是缺点就是只能通过一个节点的引用访问后续结点 
 * 无法直接访问其前驱结点
 * 
 * @author dolphin
 * 
 *         2012-1-11 下午3:54:07
 */
public class ListDoubleLinked implements List {
	// 哨兵结点,前驱 和 后驱都为空,数据也为空
	private transient Entry header = new Entry(null/* 数据 */, null/* 后驱结点 */,
			null/* 前驱结点 */);
	private transient int size = 0;
	private transient int modCount = 0;
	
	
	public ListDoubleLinked() {
		header.next = header.previous = header;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return 0 == size;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@Override
	public Iterator iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		return null;
	}

	@Override
	public boolean add(Object e) {
		addBefore( e, header );
		return true;
	}
	
	/**
	 * 添加一个结点
	 * entry1 entry2
	 * entry1 newEntry entry2
	 * 1. 构造 数据 的新结点 后驱:entry2   前驱:entry1
	 * 2. 重置后驱 与 前驱 结点.
	 * 		- 前驱 entry1 的后驱 新结点
	 * 		- 后驱 entry2 的前驱 新结点
	 * 3. 记录操作
	 * 		size + 1  大小
	 * 		modCount + 1	操作次数
	 * @param e
	 * @param entry
	 */
	private void addBefore(Object e, Entry entry) {
		/*
		 * oldEntry lastEntry
		 * oldEntry newEntry lastEntry
		 *  make a new entry { next: lastEntry, previous: oldEntry( lastEntry.previous )}
		 */
		Entry newEntry = new Entry( e, entry /*next*/, entry.previous /*previous*/);
		
		// reset the old previous's next to the new entry
		newEntry.previous.next = newEntry;
		// reset the last entry's previous to the new entry.
		newEntry.next.previous = newEntry;
		
		size++;
		modCount++;
	}
	
	/**
	 * 删除 数据为 Object o 的 Entry
	 * 1. 当参数为空
	 */
	@Override
	public boolean remove(Object o) {
		// old code 
/*
		if ( o == null ) { // case 1.
			for ( Entry e = header.next; e != header; e = e.next ) {
				if ( e.element == null ) {
					remove( e );
					return true;
				}
			}
		} else {
			for ( Entry e = header.next; e != header; e = e.next ) {
				if ( o.equals( e.element ) ) {
					remove( e );
					return true;
				}
			}
		}
*/
		// my code 
		for ( Entry e = header.next; e != header; e = e.next ) {
			if ( o == null ? ( e.element == null ) : ( o.equals( e.element ) )  ) {
				remove( e );
				return true;
			}
		}
		return false;
	}
	
	private Object remove(Entry e) {
		if ( e == header ) {
			throw new NoSuchElementException();
		}
		/**
		 * entry entry  entry
		 */
		Object result = e.element;
		e.previous.next = e.next;
		e.next.previous = e.previous;
		
		e.next = e.previous = null;
		e.element = null;
		
		size--;
		modCount++;
		return result;
	}
	
	
	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 1. 遍历 双链表，清除 Entry 的前驱 后驱，以及 数据
	 * 2. 重置 header 的双环
	 * 3. size modCount 操作
	 */
	@Override
	public void clear() {
		// old code 
		Entry e = header.next;
		while ( e != header ) {
			Entry next = e.next;
			e.next = e.previous = null;
			e.element = null;
			e = next;
		}
	
/*		// my code
		for ( Entry e = header.next, next ; e != header; next = e.next, e.next = null, e = next.next) {
			e.previous = null;
			e.element = null;
		}
*/			
		header.next = header.previous = header;
		size = 0;
		modCount++;
	}

	@Override
	public Object get(int index) {
		return entry( index ).element;
	}

	private Entry entry(int index) {
		if ( index < 0  || index >= size ) {
			throw new IndexOutOfBoundsException( "Index: " + index + ", Size: " + size );
		}
		Entry e = header;
		if ( index < ( size >> 1 ) ) { // 从前一半去查找
			for ( int i = 0; i <= index; i++ ) {
				e = e.next;
			}
		} else {
			for ( int i = size; i > index; i-- 	) {
				e = e.previous;
			}
		}
		return e;
	}

	@Override
	public Object set(int index, Object element) {
		Entry e = entry( index );
		Object oldVal = e.element;
		e.element = element;
		return oldVal;
	}
	
	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position ( if any) and any subsequent elements
	 * to the right ( adds one to their indices).
	 */
	@Override
	public void add(int index, Object element) {
		addBefore( element, ( index == size ) ? header : ( entry( index ) ) );
	}

	@Override
	public Object remove(int index) {
		return remove( entry( index ) );
	}

	@Override
	public int indexOf(Object o) {
		int index = 0;
		if ( o == null ) { 
			for ( Entry e = header.next; e !=  header; e = e.next ) {
				if ( e.element == null ) {
					return index;
				}
				index++;
			}
		} else {
			for ( Entry e = header.next; e != header; e = e.next ) {
				if ( o.equals( e.element ) ) {
					return index;
				}
				index++;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		int index = size;
		if ( o == null ) {
			for ( Entry e = header.previous; e != header; e = e.previous ) {
				index--;
				
				if ( e.element == null ) {
					return index;
				}
			}
		} else {
			for ( Entry e = header.previous; e != header; e = e.previous ) {
				index--;
				
				if ( o.equals( e.element ) ) {
					return index;
				}
			}
		}
		return -1;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	private static class Entry<E> {
		E element;
		Entry<E> next;
		Entry<E> previous;

		Entry(E element, Entry<E> next, Entry<E> previous) {
			this.element = element;
			this.next = next;
			this.previous = previous;
		}
	}
}
