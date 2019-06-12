package com.mindlin.nautilus.impl.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.mindlin.nautilus.impl.util.Characters;

class CharactersTest {
	
	@Test
	public void testWhitespace() {
		assertTrue(Characters.isJsWhitespace(' '));
	}
	
	@Test
	void testCanStartNumber() {
		assertTrue(Characters.canStartNumber('0'));
		assertTrue(Characters.canStartNumber('5'));
		assertTrue(Characters.canStartNumber('-'));
		assertTrue(Characters.canStartNumber('.'));
	}
	
	@Test
	void testAsHexDigit() {
		assertEquals(0x0, Characters.asHexDigit('0'));
		assertEquals(0x5, Characters.asHexDigit('5'));
		assertEquals(0x9, Characters.asHexDigit('9'));
		assertEquals(0xA, Characters.asHexDigit('a'));
		assertEquals(0xA, Characters.asHexDigit('A'));
		assertEquals(0xF, Characters.asHexDigit('f'));
		assertEquals(0xF, Characters.asHexDigit('F'));
		
		assertEquals(-1, Characters.asHexDigit('\t'));
		assertEquals(-1, Characters.asHexDigit('g'));
		assertEquals(-1, Characters.asHexDigit('G'));
	}
}
