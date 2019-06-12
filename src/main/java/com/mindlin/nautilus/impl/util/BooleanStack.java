package com.mindlin.nautilus.impl.util;

import java.util.Arrays;

public class BooleanStack extends AbstractPrimitiveStack implements Cloneable {
	private static final long serialVersionUID = -2309377622807194378L;
	protected boolean[] elements;
	
	public BooleanStack() {
		this(DEFAULT_CAPACITY);
	}
	
	public BooleanStack(int initialCapacity) {
		this(initialCapacity, true);
	}
	
	public BooleanStack(BooleanStack src) {
		super(src.elements.length, src.autoShrink);
		synchronized (src) {
			this.size = src.getSize();
			this.shrinkSize = src.shrinkSize;
			this.elements = new boolean[src.elements.length];
			System.arraycopy(src.elements, 0, this.elements, 0, this.size);
		}
	}
	
	public BooleanStack(int initialCapacity, boolean autoShrink) {
		super(initialCapacity, autoShrink);
		this.elements = new boolean[initialCapacity];
	}
	
	@Override
	protected int getCapacity() {
		return this.elements.length;
	}
	
	@Override
	protected void resize(int newCapacity) {
		this.elements = Arrays.copyOf(this.elements, newCapacity);
	}
	
	/**
	 * Add an element to the top of the stack
	 * @param value
	 */
	public void push(boolean value) {
		ensureSpace(1);
		this.elements[this.size++] = value;
	}
	
	/**
	 * Return the element at the top of the stack
	 * @return top element
	 */
	public boolean peek() {
		return this.elements[this.size - 1];
	}
	
	/**
	 * Remove & return the value at the top of the stack
	 * @return top element
	 */
	public boolean pop() {
		boolean result = this.elements[--this.size];
		if (this.size < this.shrinkSize)
			shrinkABit();
		return result;
	}
	
	/**
	 * Get an array of the values on this stack. The returned array should be a
	 * copy, such that any changes to it aren't reflected in this object.
	 * 
	 * @return copy of elements
	 */
	public boolean[] toArray() {
		return Arrays.copyOf(this.elements, this.size);
	}
	
	@Override
	protected BooleanStack clone() {
		return new BooleanStack(this);
	}
	
	@Override
	public int hashCode() {
		//TODO cache?
		boolean[] elements = this.elements;
		int len = this.size;
		//Mostly for JIT assertion stuff
		//Should always be false.
		if (len > elements.length)
			len = elements.length;
		
		long result = len;
		long tmp = 0;
		for (int i = 0; i < len; i++) {
			tmp <<= 1;
			if (elements[i])
				tmp |= 1;
			if (i % 63 == 0) {
				result = result * 31 + tmp;
				tmp = 0;
			}
		}
		result = result * 51 + tmp;
		return (int) (result ^ (result >>> 32));
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof BooleanStack))
			return false;
		
		BooleanStack other = (BooleanStack) o;
		if(this.size != other.size)
			return false;
		
		
		final int len = this.size;
		boolean[] e1 = this.elements, e2 = other.elements;
		
		if (e1.length < len || e2.length < len)
			return false;//Bail here, rather than throwing an error
		
		for (int i = 0; i < len; i++)
			if (e1[i] != e2[i])
				return false;
		return true;
	}
}
