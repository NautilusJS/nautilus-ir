package com.mindlin.nautilus.impl.util;

import java.util.Arrays;
import java.util.EmptyStackException;

public class LongStack extends AbstractPrimitiveStack {
	private static final long serialVersionUID = -2309377622807194378L;
	
	protected long[] elements;
	
	public LongStack() {
		this(DEFAULT_CAPACITY);
	}
	
	public LongStack(int initialCapacity) {
		this(initialCapacity, true);
	}
	
	public LongStack(LongStack src) {
		super(src.elements.length, src.autoShrink);
		
		this.elements = new long[src.elements.length];
		synchronized (src) {
			System.arraycopy(src.elements, 0, this.elements, 0, src.getSize());
			this.size = src.getSize();
		}
	}
	
	public LongStack(int initialCapacity, boolean autoShrink) {
		super(initialCapacity, autoShrink);
		this.elements = new long[initialCapacity];
		this.updateShrinkSize();
	}
	
	public long get(int idx) throws IndexOutOfBoundsException {
		if (0 > idx || idx >= this.getSize())
			throw new IndexOutOfBoundsException(String.format("Stack size: %d/%d", idx, getSize()));
		return this.elements[idx];
	}
	
	@Override
	protected void resize(int newCapacity) {
		this.elements = Arrays.copyOf(this.elements, newCapacity);
	}
	
	@Override
	protected int getCapacity() {
		return this.elements.length;
	}
	
	/**
	 * Add an element to the top of the stack
	 * @param value
	 */
	public void push(long value) {
		ensureSpace(1);
		this.elements[this.size++] = value;
	}
	
	/**
	 * Return the element at the top of the stack
	 * @return Element at the top of this stack
	 * @throws EmptyStackException If there are no values on the stack
	 */
	public long peek() throws EmptyStackException {
		if (this.size < 1)
			throw new EmptyStackException();
		return this.elements[this.size - 1];
	}
	
	/**
	 * Remove & return the value at the top of the stack
	 * @return Value popped of the top of the stack
	 * @throws EmptyStackException If there are no values on the stack
	 */
	public long pop() throws EmptyStackException {
		if (this.size < 1)
			throw new EmptyStackException();
		
		long result = this.elements[--this.size];
		if (this.size < this.shrinkSize)
			shrinkABit();
		return result;
	}
	
	/**
	 * Get an array of the values on this stack. The returned array should be a
	 * copy, such that any changes to it aren't reflected in this object.
	 * 
	 * @return Array
	 */
	public long[] toArray() {
		return Arrays.copyOf(this.elements, this.size);
	}
	
	@Override
	protected LongStack clone() {
		return new LongStack(this);
	}
	
	@Override
	public int hashCode() {
		//TODO cache?
		long[] elements = this.elements;
		int len = this.size;
		//Mostly for JIT assertion stuff
		//Should always be false.
		if (len > elements.length)
			len = elements.length;
		
		int result = len;
		for (int i = 0; i < len; i++) {
			long e = elements[i];
			result *= 31;
			result += (int) (e ^ (e >>> 32));
		}
		
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof LongStack))
			return false;
		
		LongStack other = (LongStack) o;
		if(this.size != other.size)
			return false;
		
		
		final int len = this.size;
		long[] e1 = this.elements, e2 = other.elements;
		
		if (e1.length < len || e2.length < len)
			return false;//Bail here, rather than throwing an error
		
		for (int i = 0; i < len; i++)
			if (e1[i] != e2[i])
				return false;
		return true;
	}
}