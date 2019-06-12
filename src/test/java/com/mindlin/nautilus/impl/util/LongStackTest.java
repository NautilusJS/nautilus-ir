package com.mindlin.nautilus.impl.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

class LongStackTest {
	
	protected LongStack stack(long...values) {
		LongStack result = new LongStack();
		for (long value : values)
			result.push(value);
		return result;
	}
	
	@Test
	void testResize() {
		LongStack stack = new LongStack(2);
		assertEquals(2, stack.getCapacity());
		stack.push(0);
		stack.push(0);
		stack.push(0);
		assertThat("capacity", stack.getCapacity(), greaterThanOrEqualTo(3));
	}
	
	@Test
	void testShrink() {
		LongStack stack = stack(0, 1, 2);
		
		assertThat("capacity", stack.getCapacity(), lessThan(300));
		stack.shrinkABit();
		
		stack.ensureCapacity(300);
		assertEquals(3, stack.getSize());
		assertThat("capacity", stack.getCapacity(), greaterThanOrEqualTo(300));
		
		stack.push(3);
		assertEquals(4, stack.getSize());
		assertThat("capacity", stack.getCapacity(), greaterThanOrEqualTo(300));
		
		stack.pop();
		stack.shrinkABit();
		assertThat("capacity", stack.getCapacity(), lessThan(300));
	}
	
	@Test
	void testNegativeInitialCap() {
		assertThrows(IllegalArgumentException.class, () -> new LongStack(-1));
	}
	
	@Test
	void testZeroInitialCap() {
		LongStack stack = new LongStack(0);
		assertTrue(stack.isEmpty());
		assertEquals(0, stack.getCapacity());
		
		stack.push(0);
	}
	
	@Test
	void testEmptyExceptions() {
		LongStack stack = new LongStack();
		assertTrue(stack.isEmpty());
		
		assertThrows(EmptyStackException.class, stack::peek);
		assertThrows(EmptyStackException.class, stack::pop);

		assertThrows(IndexOutOfBoundsException.class, () -> stack.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> stack.get(0));
	}
	
	@Test
	void testPushPop() {
		LongStack stack = stack(0, 1, 2);
		
		assertEquals(3, stack.getSize());
		assertFalse(stack.isEmpty());
		
		assertArrayEquals(new long[] {0, 1, 2}, stack.toArray());
		assertEquals(2, stack.peek());
		
		assertEquals(2, stack.pop());
		assertEquals(1, stack.pop());
		assertEquals(0, stack.pop());
		
		assertTrue(stack.isEmpty());
		assertThrows(EmptyStackException.class, stack::pop);
	}
	
	@Test
	void testGet() {
		LongStack stack = stack(0, 2, 4);
		
		assertEquals(0, stack.get(0));
		assertEquals(2, stack.get(1));
		assertEquals(4, stack.get(2));
		
		assertThrows(IndexOutOfBoundsException.class, () -> stack.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> stack.get(3));
	}
	
	@Test
	void testEquals() {
		LongStack stack123 = stack(1, 2, 3);
		LongStack stack024 = stack(0, 2, 4);
		assertEquals(stack024, stack(0, 2, 4));
		assertNotEquals(stack024, stack123);
		assertNotEquals(stack123, null);
		assertNotEquals(stack123, new Object());
		
		LongStack customCap = new LongStack(3);
		customCap.push(1);
		customCap.push(2);
		customCap.push(3);
		LongStack defaultCap = stack(1, 2, 3);
		assertNotEquals(defaultCap.getCapacity(), customCap.getCapacity());
		assertEquals(customCap, customCap);
		assertEquals(defaultCap, customCap);
	}
	
	@Test
	void testHashCode() {
		LongStack stack123 = stack(1, 2, 3);
		LongStack stack024 = stack(0, 2, 4);
		assertEquals(stack024.hashCode(), stack(0, 2, 4).hashCode());
		assertNotEquals(stack024.hashCode(), stack123.hashCode());
		
		LongStack customCap = new LongStack(3);
		customCap.push(1);
		customCap.push(2);
		customCap.push(3);
		LongStack defaultCap = stack(1, 2, 3);
		assertNotEquals(defaultCap.getCapacity(), customCap.getCapacity());
		assertEquals(customCap.hashCode(), customCap.hashCode());
		assertEquals(defaultCap.hashCode(), customCap.hashCode());
	}
	
	@Test
	void testClone() {
		LongStack stackA = stack(1, 2, 3);
		LongStack stackB = new LongStack(stackA);
		LongStack stackC = stackA.clone();
		
		assertNotSame(stackA, stackB);
		assertNotSame(stackA, stackC);
		assertNotSame(stackB, stackC);
		
		assertEquals(stackA, stackB);
		assertEquals(stackA, stackC);
		assertEquals(stackB, stackC);
		
		assertEquals(stackA.getCapacity(), stackB.getCapacity());
	}
}
