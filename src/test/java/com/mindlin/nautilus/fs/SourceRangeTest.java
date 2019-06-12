package com.mindlin.nautilus.fs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SourceRangeTest {
	protected SourceFile SOURCE_A = new FakeSourceFile("foo.js", new long[] { 0, 10, 20, 30 });
	protected SourceFile SOURCE_B = new FakeSourceFile("bar.js", new long[] { 0, 20, 40, 60 });
	
	@Test
	void testGetters() {
		SourcePosition start = new SourcePosition(SOURCE_A, 50, 10, 15);
		SourcePosition end = new SourcePosition(SOURCE_A, 60, 10, 25);
		SourceRange range = new SourceRange(start, end);
		
		assertEquals(start, range.getStart());
		assertEquals(end, range.getEnd());
		assertTrue(range.isFullyDefined());
	}
	
	@Test
	void testStartingFrom() {
		SourcePosition pos = new SourcePosition(SOURCE_A, 50, 10, 15);
		SourceRange sf = SourceRange.startingFrom(pos);
		
		assertEquals(pos, sf.getStart());
		assertEquals(SourcePosition.invalid(), sf.getEnd());
	}
	
	@Test
	void testEndingAt() {
		SourcePosition pos = new SourcePosition(SOURCE_A, 50, 10, 15);
		SourceRange ea = SourceRange.endingAt(pos);
		
		assertEquals(SourcePosition.invalid(), ea.getStart());
		assertEquals(pos, ea.getEnd());
	}
	
	@Test
	void testInvalid() {
		SourcePosition pos = new SourcePosition(SOURCE_A, 50, 10, 15);
		
		assertFalse(SourceRange.invalid().isFullyDefined());
		assertFalse(SourceRange.startingFrom(pos).isFullyDefined());
		assertFalse(SourceRange.endingAt(pos).isFullyDefined());
	}
	
	@Test
	void testHashCode() {
		SourcePosition start = new SourcePosition(SOURCE_A, 50, 10, 15);
		SourcePosition end = new SourcePosition(SOURCE_A, 60, 10, 25);
		
		assertEquals(SourceRange.invalid().hashCode(), SourceRange.invalid().hashCode());
		assertEquals(SourceRange.startingFrom(start).hashCode(), SourceRange.startingFrom(start).hashCode());
		assertEquals(SourceRange.endingAt(end).hashCode(), SourceRange.endingAt(end).hashCode());
		assertEquals(new SourceRange(start, end).hashCode(), new SourceRange(start, end).hashCode());
	}
	
	@Test
	void testEquals() {
		SourcePosition start = new SourcePosition(SOURCE_A, 50, 10, 15);
		SourcePosition end = new SourcePosition(SOURCE_A, 60, 10, 25);
		
		assertEquals(SourceRange.invalid(), SourceRange.invalid());
		assertEquals(SourceRange.startingFrom(start), SourceRange.startingFrom(start));
		assertEquals(SourceRange.endingAt(end), SourceRange.endingAt(end));
		assertEquals(new SourceRange(start, end), new SourceRange(start, end));
		assertEquals(new SourceRange(start, null), new SourceRange(start, SourcePosition.invalid()));
		assertEquals(new SourceRange(start, null), SourceRange.startingFrom(start));
		assertEquals(new SourceRange(null, end), SourceRange.endingAt(end));
		
		assertNotEquals(SourceRange.startingFrom(start), new SourceRange(start, end));
		assertNotEquals(SourceRange.startingFrom(start), SourceRange.startingFrom(end));
		assertNotEquals(SourceRange.endingAt(end), new SourceRange(start, end));
		assertNotEquals(SourceRange.endingAt(start), SourceRange.endingAt(end));
		assertNotEquals(SourceRange.invalid(), null);
		assertNotEquals(new SourceRange(start, end), null);
	}
	
	@Test
	void testSameFileToString() {
		assertEquals("<foo.js(10:15 - 11:25)>", new SourceRange(
				new SourcePosition(SOURCE_A, 50, 10, 15),
				new SourcePosition(SOURCE_A, 70, 11, 25)).toString());
		
		// Missing lines
		assertEquals("<foo.js(50 - 70)>", new SourceRange(
				new SourcePosition(SOURCE_A, 50, -1, 15),
				new SourcePosition(SOURCE_A, 70, -1, 25)).toString());
		assertEquals("<foo.js(50 - 11:25)>", new SourceRange(
				new SourcePosition(SOURCE_A, 50, -1, 15),
				new SourcePosition(SOURCE_A, 70, 11, 25)).toString());
		assertEquals("<foo.js(10:15 - 70)>", new SourceRange(
				new SourcePosition(SOURCE_A, 50, 10, 15),
				new SourcePosition(SOURCE_A, 70, -1, 25)).toString());

		// Same lines
		assertEquals("<foo.js(10)>", new SourceRange(
				new SourcePosition(SOURCE_A, 50, 10, -1),
				new SourcePosition(SOURCE_A, 60, 10, -1)).toString());
		assertEquals("<foo.js(10:15-25)>", new SourceRange(
				new SourcePosition(SOURCE_A, 50, 10, 15),
				new SourcePosition(SOURCE_A, 60, 10, 25)).toString());
		// (degenerate cases)
		assertEquals("<foo.js(10:-1 - 10:25)>", new SourceRange(
				new SourcePosition(SOURCE_A, 50, 10, -1),
				new SourcePosition(SOURCE_A, 60, 10, 25)).toString());
		assertEquals("<foo.js(10:15 - 10:-1)>", new SourceRange(
				new SourcePosition(SOURCE_A, 50, 10, 15),
				new SourcePosition(SOURCE_A, 60, 10, -1)).toString());
		
		// Missing cols
		assertEquals("<foo.js(10:-1 - 11:15)>", new SourceRange(
				new SourcePosition(SOURCE_A, 50, 10, -1),
				new SourcePosition(SOURCE_A, 70, 11, 15)).toString());
		assertEquals("<foo.js(10:15 - 11:-1)>", new SourceRange(
				new SourcePosition(SOURCE_A, 50, 10, 15),
				new SourcePosition(SOURCE_A, 70, 11, -1)).toString());
		assertEquals("<foo.js(10-11)>", new SourceRange(
				new SourcePosition(SOURCE_A, 50, 10, -1),
				new SourcePosition(SOURCE_A, 70, 11, -1)).toString());
		
		assertEquals("<foo.js(50 - 70)>", new SourceRange(
				new SourcePosition(SOURCE_A, 50, -1, -1),
				new SourcePosition(SOURCE_A, 70, -1, -1)).toString());
	}
	
	@Test
	void testCrossFileToString() {
		assertEquals("<foo.js(10:15) - bar.js(10:25)>", new SourceRange(new SourcePosition(SOURCE_A, 50, 10, 15), new SourcePosition(SOURCE_B, 60, 10, 25)).toString());
	}
	
	@Test
	void testPartialToString() {
		assertEquals("<???>", new SourceRange(null, null).toString());
		
		assertEquals("<unknown(10:15-25)>", new SourceRange(
				new SourcePosition(null, 50, 10, 15),
				new SourcePosition(null, 60, 10, 25)).toString());
		
		assertEquals("<foo.js(10:15) - ?>", SourceRange.startingFrom(new SourcePosition(SOURCE_A, 50, 10, 15)).toString());
		assertEquals("<unknown(10:15) - ?>", SourceRange.startingFrom(new SourcePosition(null, 50, 10, 15)).toString());
		
		assertEquals("<? - foo.js(10:25)>", SourceRange.endingAt(new SourcePosition(SOURCE_A, 60, 10, 25)).toString());
		assertEquals("<? - unknown(10:25)>", SourceRange.endingAt(new SourcePosition(null, 60, 10, 25)).toString());
	}
	
}
