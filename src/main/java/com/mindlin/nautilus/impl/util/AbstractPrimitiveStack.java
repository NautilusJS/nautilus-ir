package com.mindlin.nautilus.impl.util;

import java.io.Serializable;
import java.util.RandomAccess;

public abstract class AbstractPrimitiveStack implements RandomAccess, Serializable {
	private static final long serialVersionUID = -6936480326247612589L;
	protected static final int DEFAULT_CAPACITY = 16;

	protected int size = 0;
	protected final boolean autoShrink;
	protected int shrinkSize = 0;
	
	public AbstractPrimitiveStack(int initialCapacity, boolean autoShrink) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
		this.autoShrink = autoShrink;
		this.shrinkSize = initialCapacity >> 16;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.getSize() == 0;
	}
	
	protected abstract int getCapacity();
	
	protected abstract void resize(int newCapacity);
	
	protected void updateShrinkSize() {
		// Trigger shrink at 1/4 of the current capacity
		// However, shrinks won't be triggered when under 32 elements (Mb<Mc)
		int capacity = this.getCapacity();
		this.shrinkSize = (this.autoShrink && capacity >> 5 != 0) ? capacity >> 2 : -1;
	}
	
	public void ensureSpace(int space) {
		this.ensureCapacity(this.size + space);
	}
	
	/**
	 * The maximum size of array to allocate. Some VMs reserve some header words
	 * in an array. Attempts to allocate larger arrays may result in
	 * OutOfMemoryError: Requested array size exceeds VM limit (From
	 * {@link java.util.ArrayList#MAX_ARRAY_SIZE})
	 */
	protected static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	
	public void ensureCapacity(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("Illegal capacity: " + capacity);
		final int oldCapacity = this.getCapacity();
		if (oldCapacity >= capacity)
			return;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity - capacity < 0)
			// Integer overflow
			newCapacity = capacity;
		if (newCapacity - MAX_ARRAY_SIZE > 0)
			newCapacity = MAX_ARRAY_SIZE;
		this.resize(newCapacity);
		this.updateShrinkSize();
	}
	
	public void shrinkABit() {
		final int oldCapacity = this.getCapacity();
		if (oldCapacity - 22 < 0)
			return;
		// Target 3/4 of the current capacity
		int newCapacity = oldCapacity - oldCapacity >> 2;
		if (newCapacity < DEFAULT_CAPACITY)
			newCapacity = DEFAULT_CAPACITY;
		if (newCapacity - this.size < 0)
			newCapacity = this.size;
		if (newCapacity - oldCapacity > 0 || newCapacity - MAX_ARRAY_SIZE > 0 || newCapacity < 0)
			// Something clearly has gone very wrong
			return;
		this.resize(newCapacity);
		this.updateShrinkSize();
	}
}
