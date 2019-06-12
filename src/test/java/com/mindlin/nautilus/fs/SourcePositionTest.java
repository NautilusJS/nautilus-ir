package com.mindlin.nautilus.fs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SourcePositionTest {
	protected SourceFile SOURCE = new FakeSourceFile("foo.js", new long[] { 0, 10, 20 });
	
	@Test
	void testGetters() {
		SourcePosition pos = new SourcePosition(SOURCE, 50, 10, 15);
		assertEquals(SOURCE, pos.getSource());
		assertEquals(50, pos.getOffset());
		assertEquals(10, pos.getLine());
		assertEquals(15, pos.getCol());
	}
	
	@Test
	void testInvalid() {
		SourcePosition pos = SourcePosition.invalid();
		assertNull(pos.getSource());
		assertEquals(-1, pos.getOffset());
		assertEquals(-1, pos.getLine());
		assertEquals(-1, pos.getCol());
	}
	
	@Test
	void testToString() {
		assertEquals("unknown(???)", SourcePosition.invalid().toString());
		assertEquals("unknown(50)", new SourcePosition(null, 50).toString());
		assertEquals("unknown(10:-1)", new SourcePosition(null, -1, 10, -1).toString());
		assertEquals("unknown(10:15)", new SourcePosition(null, -1, 10, 15).toString());
		
		assertEquals("foo.js(50)", new SourcePosition(SOURCE, 50).toString());
		assertEquals("foo.js(50)", new SourcePosition(SOURCE, 50, -1, 15).toString());
		assertEquals("foo.js(10:-1)", new SourcePosition(SOURCE, 50, 10, -1).toString());
		assertEquals("foo.js(10:15)", new SourcePosition(SOURCE, 50, 10, 15).toString());
		assertEquals("foo.js(10:15)", new SourcePosition(SOURCE, -1, 10, 15).toString());
		assertEquals("foo.js(???)", new SourcePosition(SOURCE, -1, -1, 15).toString());
		assertEquals("foo.js(10:-1)", new SourcePosition(SOURCE, -1, 10, -1).toString());
	}
	
	@Test
	void testEquals() {
		assertEquals(SourcePosition.invalid(), SourcePosition.invalid());
		
		SourcePosition posA = new SourcePosition(SOURCE, 50);
		assertEquals(posA, new SourcePosition(SOURCE, 50));
		assertEquals(posA, new SourcePosition(SOURCE, 50, -1, -1));
		
		SourcePosition posB = new SourcePosition(SOURCE, 50, 10, 15);
		assertEquals(posB, new SourcePosition(SOURCE, 50, 10, 15));
		assertNotEquals(posB, new SourcePosition(SOURCE, 0, 10, 15));
		assertNotEquals(posB, new SourcePosition(SOURCE, -1, 10, 15));
		assertNotEquals(posB, new SourcePosition(SOURCE, 50, -1, -1));
		assertNotEquals(posB, new SourcePosition(SOURCE, 0, -1, 15));
		assertNotEquals(posB, new SourcePosition(null, 50, 10, 15));
		assertNotEquals(new SourcePosition(SOURCE, 50, -1, 15), new SourcePosition(SOURCE, 0, -1, -1));
		assertNotEquals(new SourcePosition(null, 50, 10, 15), new SourcePosition(null, 0, 10, 15));
		assertNotEquals(new SourcePosition(null, 50, 10, 15), new SourcePosition(null, 50, 10, -1));
		assertNotEquals(posA, null);
		assertNotEquals(posB, null);
		assertNotEquals(posB, new Object());
	}
	
	@Test
	void testHashCode() {
		assertEquals(SourcePosition.invalid().hashCode(), SourcePosition.invalid().hashCode());
		
		SourcePosition posA = new SourcePosition(SOURCE, 50);
		assertEquals(posA.hashCode(), new SourcePosition(SOURCE, 50).hashCode());
		assertEquals(posA.hashCode(), new SourcePosition(SOURCE, 50, -1, -1).hashCode());
		
		SourcePosition posB = new SourcePosition(SOURCE, 50, 10, 15);
		assertEquals(posB.hashCode(), new SourcePosition(SOURCE, 50, 10, 15).hashCode());
	}
}
