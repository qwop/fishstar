package util.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Queue;

public class QueueSingleLinkedTest {
	private Queue queue;
	
	@Before
	public void setUp() throws Exception {
		queue = new QueueSingleLinked();
	}

	@After
	public void tearDown() throws Exception {
		queue = null;
	}

	@Test
	public void testSize() {
		
		assertEquals( "未加入元素之前", 0 , queue.size() );
		
		queue.enqueue( 1 );
		queue.enqueue( 2 );
		queue.enqueue( 3 );
		
		assertEquals( "加入了三个元素", 3 , queue.size() );
	}

	@Test
	public void testIsEmpty() {
		assertTrue( "未加入元素之前",  queue.isEmpty() );
		
		queue.enqueue( 1 );
		
		assertFalse( "未加入元素之前",  queue.isEmpty() );
	}

	@Test
	public void testEnqueue() {
		queue.enqueue( 1 );
		queue.enqueue( "fuck" );
		
		Object obj = queue.peek();
		assertEquals( "队首的元素", new Integer( 1 ), obj );
		
		queue.dequeue();
		
		
		queue.enqueue( "you" );
		queue.dequeue();
		
		queue.enqueue( "fuck" );
		queue.dequeue();
		
		
		obj = queue.peek();
		
		assertEquals( "队首的元素", "fuck", obj );
	}

	@Test
	public void testDequeue() {
		for ( int i = 0; i < 100; i++ ) {
			queue.enqueue( i );
		}
		
		assertEquals( "队首的元素", new Integer( 0 ), queue.dequeue() );
		assertEquals( "队首的元素", new Integer( 1 ), queue.dequeue() );
		assertEquals( "队首的元素", new Integer( 2 ), queue.dequeue() );
		assertEquals( "队首的元素", new Integer( 3 ), queue.dequeue() );
	}

	@Test
	public void testPeek() {
		for ( int i = 0; i < 10; i++ ) {
			queue.enqueue( i );
		}
		
		
		assertEquals( "队首的元素", new Integer( 0 ), queue.peek() );
		assertEquals( "队首的元素", new Integer( 0 ), queue.peek() );
		assertEquals( "队首的元素", new Integer( 0 ), queue.peek() );
		assertEquals( "队首的元素", new Integer( 0 ), queue.peek() );
		assertEquals( "队首的元素", new Integer( 0 ), queue.peek() );
	}

}
