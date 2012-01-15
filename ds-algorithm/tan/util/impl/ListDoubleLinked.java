package util.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * ˫������
 * 
 * 
 * ��������ŵ��ǽṹ�򵥣�
 * ����ȱ�����ֻ��ͨ��һ���ڵ�����÷��ʺ������ 
 * �޷�ֱ�ӷ�����ǰ�����
 * 
 * @author dolphin
 * 
 *         2012-1-11 ����3:54:07
 */
public class ListDoubleLinked implements List {
	// �ڱ����,ǰ�� �� ������Ϊ��,����ҲΪ��
	private transient Entry header = new Entry(null/* ���� */, null/* ������� */,
			null/* ǰ����� */);
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
	 * ���һ�����
	 * entry1 entry2
	 * entry1 newEntry entry2
	 * 1. ���� ���� ���½�� ����:entry2   ǰ��:entry1
	 * 2. ���ú��� �� ǰ�� ���.
	 * 		- ǰ�� entry1 �ĺ��� �½��
	 * 		- ���� entry2 ��ǰ�� �½��
	 * 3. ��¼����
	 * 		size + 1  ��С
	 * 		modCount + 1	��������
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
	 * ɾ�� ����Ϊ Object o �� Entry
	 * 1. ������Ϊ��
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
	 * 1. ���� ˫������� Entry ��ǰ�� �������Լ� ����
	 * 2. ���� header ��˫��
	 * 3. size modCount ����
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
		if ( index < ( size >> 1 ) ) { // ��ǰһ��ȥ����
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
