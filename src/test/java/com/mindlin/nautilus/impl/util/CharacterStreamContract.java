package com.mindlin.nautilus.impl.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.mindlin.nautilus.util.CharacterStream;

@SuppressWarnings("resource")
interface CharacterStreamContract {

	CharacterStream wrap(String text);
	
	@Test
	default void testNext() {
		CharacterStream chars = wrap("123456789");
		assertEquals('1', chars.next());
		assertEquals(0, chars.position());
		
		assertEquals('2', chars.next());
		assertEquals(1, chars.position());
		
		assertEquals('5', chars.next(3));
		assertEquals(4, chars.position());
	}
	
	@Test
	default void testHasNext() {
		CharacterStream chars = wrap("123");
		assertTrue(chars.hasNext());
		assertTrue(chars.hasNext(1));
		assertTrue(chars.hasNext(3));
		assertFalse(chars.hasNext(4));
		assertFalse(chars.hasNext(0xffffffffffL));
		assertThrows(IllegalArgumentException.class, () -> chars.hasNext(-1));
	}
	
	@Test
	default void testPeek() {
		CharacterStream chars = wrap("123456789");
		assertEquals('1', chars.peek());
		assertEquals('1', chars.peek(1));
		assertEquals('2', chars.peek(2));
		assertThrows(IndexOutOfBoundsException.class, () -> chars.peek(10));
		assertThrows(IndexOutOfBoundsException.class, () -> chars.peek(0xffffffffffL));
		assertThrows(IllegalArgumentException.class, () -> chars.peek(-1));
		
		assertEquals('1', chars.next());
		assertEquals(0, chars.position());
		
		assertEquals('2', chars.peek());
		assertEquals('1', chars.peek(0));
		assertEquals('2', chars.peek(1));
		assertEquals('3', chars.peek(2));
		assertEquals(0, chars.position());
	}

	@Test
	@SuppressWarnings("deprecation")
	default void testNBSP_1() {
		CharacterStream chars = wrap("\u00a0 \n\r1");
		
		chars.skipWhitespace();
		assertEquals('1', chars.next());
	}

	@Test
	@SuppressWarnings("deprecation")
	default void testNBSP_2() {
		CharacterStream chars = wrap("\u00a0 \n\r1");
		
		chars.skipWhitespace(true);
		assertEquals('1', chars.next());
	}

	@Test
	@SuppressWarnings("deprecation")
	default void testNBSP_3() {
		CharacterStream chars = wrap("\u00a0 \n\r1");
		
		chars.skipWhitespace(false);
		assertEquals('\r', chars.next());
	}
	
	@Test
	default void testNextCurrent() {
		CharacterStream chars = wrap("123456789");
		
		assertThrows(IllegalStateException.class, chars::current);
		
		assertEquals('1', chars.next());
		assertEquals(0, chars.position());
		
		assertEquals('1', chars.current());
		assertEquals(0, chars.position());
		
		
	}
	
	@Test
	default void testSkipAndGet() {
		CharacterStream chars = wrap("abcde");
		assertEquals('a', chars.next());
		assertNotNull(chars.skip(1));
		assertEquals('b', chars.current());
		assertEquals('c', chars.next());
	}
	
	@Test
	default void testEOF() {
		CharacterStream chars = wrap("x");
		assertTrue(chars.hasNext());
		assertEquals('x', chars.next());
		assertFalse(chars.hasNext());
	}
	
	@Test
	@Tag("current")
	default void testCopy() {
		CharacterStream chars = wrap("123ABC456");
		chars.mark();
		assertEquals("123", chars.copyNext(3));
		assertEquals("ABC456", chars.copyNext(6));
		assertFalse(chars.hasNext());
		chars.resetToMark();
		chars.mark();
		chars.skip(6);
		assertEquals("123ABC", chars.copyFromMark());
		assertEquals(5, chars.position());
	}
	
	@Test
	default void testMarkSkip() {
		CharacterStream chars = wrap("123abc456");
		chars.mark();
		chars.mark();
		chars.skip(4);
		assertEquals('a', chars.current());
		assertEquals('c', chars.next(2));
		assertEquals(5, chars.position());
		assertEquals("123abc", chars.copyFromMark());
		chars.resetToMark();
		assertEquals(-1, chars.position());
		chars.mark();
		chars.skip(3);
		assertEquals("123", chars.copyFromMark());
	}
	
	@Test
	@SuppressWarnings("deprecation")
	default void testSkipWhitespace() {
		CharacterStream chars = wrap("1  23  4\r5");
		assertEquals('1', chars.skipWhitespace().next());
		assertEquals('2', chars.skipWhitespace().next());
		assertEquals('3', chars.skipWhitespace().next());
		assertEquals('4', chars.skipWhitespace().next());
		assertEquals('5', chars.skipWhitespace().next());
	}
}
