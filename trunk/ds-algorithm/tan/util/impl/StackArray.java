package util.impl;

import util.Stack;
import dsa.exception.StackEmptyException;

/**
 * ∂—’ªÀ≥–Ú±Ì µœ÷
 * @author dolphin
 *
 * 2012-1-13 …œŒÁ9:11:59
 */
public class StackArray implements Stack {
	private final int LEN = 8;
	private Object[] elements;
	private int top;
	
	public StackArray() {
		top = -1;
		elements = new Object[ LEN ];
	}
	
	@Override
	public int getSize() {
		return top + 1;
	}

	@Override
	public boolean isEmpty() {
		return top < 0;
	}

	@Override
	public void push(Object e) {
		if ( getSize() > elements.length ) {
			expandDouble();
		}
		elements[ ++top ] = e;
	}

	private void expandDouble() {
		Object[] newObjs = new Object[ elements.length * 2 ];
		// copy elements
		System.arraycopy(elements, 0, newObjs, 0, elements.length);
		elements = newObjs;
	}

	@Override
	public Object pop() {
		if ( getSize() < 1 ) {
			throw new StackEmptyException("¥ÌŒÛ£¨ ∂—’ªŒ™ø’");
		}
		Object ret = elements[ top ];
		elements[ top-- ] =  null;
		return ret;
	}

	@Override
	public Object peek() {
		if ( getSize() < 1 ) {
			throw new StackEmptyException("¥ÌŒÛ£¨ ∂—’ªŒ™ø’");
		}
		return elements[ top ];
	}

}
