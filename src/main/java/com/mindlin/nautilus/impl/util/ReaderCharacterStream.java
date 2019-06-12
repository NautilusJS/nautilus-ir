package com.mindlin.nautilus.impl.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.nio.InvalidMarkException;
import java.util.Objects;

import com.mindlin.nautilus.util.CharacterStream;

public class ReaderCharacterStream extends AbstractCharacterStream {
	static final long ILLEGAL_POSITION = -1;
	static final int DEFAULT_CAP = 256;
	static final int MAX_GROWTH_FACTOR = 4;
	/**
	 * Computes <pre>2**ceil(log2(value))</pre>
	 * @param value
	 * @return
	 */
	private static long nextPowerOf2(long value) {
		if (value <= 1)
			return value;
		else
			return Long.highestOneBit(value - 1) << 1;
	}
	
	protected final Reader in;
	/** Position of value that would be returned by in.read() */ 
	protected long readerNextPosition = 0;
	
	/** Position that is used for this stream */
	protected long position = ILLEGAL_POSITION;
	
	//In case our reader can't support buffers
	/** Resolved position of {@link CharBuffer#position() buffer.start()}. */
	protected long bufferStart = ILLEGAL_POSITION;
	/**
	 * Buffer for caching data
	 * Absolute position of any element `i` in buffer (accessible by buffer.get(i)) can be computed by ({@link #bufferStart} + (i - buffer.position())).
	 * position is the location of the first valid character
	 * limit is the last loaded character
	 */
	protected CharBuffer buffer;
	
	public ReaderCharacterStream(Reader in) {
		this.in = Objects.requireNonNull(in, "Source reader must not be null");
	}
	// Translation helpers
	/**
	 * @param position Absolute position
	 * @return Buffer offset (relative to buffer.position)
	 */
	private int positionToOffset(long position) throws ArithmeticException {
		return Math.toIntExact(Math.subtractExact(position, this.bufferStart) + 1);
	}
	
	/**
	 * @param position Absolute position
	 * @return Buffer index
	 */
	private int positionToIndex(long position) throws ArithmeticException {
		return Math.toIntExact(this.buffer.position() + Math.subtractExact(position, this.bufferStart));
	}
	
	/**
	 * @param offset Buffer offset (relative to buffer.position)
	 * @return Absolute position
	 */
	private long offsetToPosition(int offset) throws ArithmeticException {
		return Math.addExact(this.bufferStart, offset);
	}
	
	/**
	 * @param index Buffer index
	 * @return Absolute position
	 */
	private long indexToPosition(int index) throws ArithmeticException {
		return this.offsetToPosition(Math.subtractExact(index, this.buffer.position()));
	}
	
	protected long translateOffset(long offset) throws IndexOutOfBoundsException {
		try {
			return Math.addExact(this.position, offset);
		} catch (ArithmeticException e) {
			IndexOutOfBoundsException e1 = new IndexOutOfBoundsException();
			e1.initCause(e);
			throw e1;
		}
	}
	
	protected long computePositionAfter(long offset) throws IllegalArgumentException, IndexOutOfBoundsException {
		if (offset < 0)
			throw new IllegalArgumentException("Negative offset");
		return this.translateOffset(offset);
	}
	
	protected int nextCapacity(long minimum) {
		long newCapL = Math.max(minimum, nextPowerOf2(minimum));
		
		long maximum;
		if (this.buffer == null)
			maximum = DEFAULT_CAP;
		else
			maximum = this.buffer.capacity() * (long) MAX_GROWTH_FACTOR;
		maximum = Math.min(Integer.MAX_VALUE, maximum);
		System.out.format("maximum=%d\n", maximum);
		
		int newCap = Math.toIntExact(Math.min(newCapL, maximum));
		
		if (this.buffer == null)
			return Math.max(newCap, DEFAULT_CAP);
		
		//TODO: check
		int oldCap = this.buffer.capacity();
		return Math.max(newCap, oldCap * 2);
	}
	
	protected boolean growBuffer(long minCap) {
		int nextCap = this.nextCapacity(minCap);
		CharBuffer next = CharBuffer.allocate(nextCap);
		next.position(0);
		if (this.buffer != null) {
			next.limit(this.buffer.remaining());
			next.put(this.buffer);
			next.position(0);
		} else {
			next.limit(0);
		}
		this.buffer = next;
		return nextCap >= minCap;
	}
	
	/**
	 * Ensures capacity for position range [left, right + 1)
	 * @param rcPos Right position constraint
	 */
	protected boolean ensureRange(long rcPos) throws IllegalArgumentException, ArithmeticException {
		final long lcPos = this.getLeftConstraint() + 1;
		System.out.format("Ensure range [%d, %d]\n", lcPos, rcPos);
		
		if (lcPos > rcPos)//TODO: will this hold?
			throw new IllegalArgumentException("RC = " + rcPos);
		
		if (this.buffer == null) {
			// Initialize buffer
			System.out.println(" ->Init buffer");
			this.growBuffer(Math.toIntExact(rcPos + 1 - lcPos));
			return true;
		}
		
		if (lcPos < this.bufferStart) {
			// Left must be in range
			throw new IllegalArgumentException();
		}
		
		//TODO if lcPos != startPosition, GC left
		
		// Check if right is in range (we don't have to shift anything)
		if (indexToPosition(this.buffer.capacity()) >= rcPos) {
			System.out.println(" ->No update " + indexToPosition(this.buffer.capacity()) + "/ " + rcPos);
			return true;
		}
		
		// Delete characters left of lcPos
		if (lcPos != this.bufferStart) {
			System.out.println(" ->GC left " + lcPos + "/" + this.bufferStart);
			this.buffer.position(positionToIndex(lcPos));
			this.bufferStart = lcPos;
		}
		long minCap = Math.max(0, rcPos + 1 - lcPos);
		if (this.buffer.capacity() < minCap) {
			// Grow buffer
			System.out.println(" ->Grow to " + minCap);
			return this.growBuffer(minCap);
		} else {
			// Rotate buffer
			System.out.println(" ->Rotate");
			this.buffer.compact();
			this.buffer.flip();
			return true;
		}
	}
	
	protected boolean isInBuffer(long position) {
		return (this.buffer != null)
				&& (this.bufferStart != ILLEGAL_POSITION)
				&& (this.bufferStart <= position)
				&& (position < indexToPosition(this.buffer.limit()));
	}
	
	protected char getBuffered(long position) throws IndexOutOfBoundsException {
		if (position < 0)
			throw new IndexOutOfBoundsException("Negative position");
		if (!this.isInBuffer(position))
			if (!this.load(position))
				throw new IndexOutOfBoundsException("EOF");
		return this.buffer.get(this.positionToIndex(position));
	}
	
	protected CharBuffer makeWriteBuffer() {
		CharBuffer target = this.buffer.duplicate();
		target.position(this.buffer.limit());
		target.limit(this.buffer.capacity());
		return target;
	}
	
	protected boolean load(long rightPos) {
		boolean finishedGrowing = this.ensureRange(rightPos);
		
		if (this.bufferStart == ILLEGAL_POSITION)
			this.bufferStart = this.readerNextPosition;
		else if (indexToPosition(this.buffer.limit()) != this.readerNextPosition)
			throw new IllegalStateException("Bad skip? " + indexToPosition(this.buffer.limit()) + " / " + this.readerNextPosition);

		CharBuffer target = this.makeWriteBuffer();
		final long minRead = rightPos + 1 - this.bufferStart;
		long totalRead = 0;
		while (totalRead < minRead) {
			int read;
			try {
				read = this.in.read(target);
			} catch (IOException e) {
				//TODO: fix?
				throw new RuntimeException(e);
			}
			System.out.format("Read %d (%d/%d,%d/%d)\n", read, Math.max(read, 0) + totalRead, minRead, target.position(), target.limit());
			if (read == -1)
				break; // EOF
			totalRead += read;
			this.readerNextPosition += read;
			this.buffer.limit(this.buffer.limit() + read);
			if (!finishedGrowing && !target.hasRemaining()) {
				finishedGrowing = this.ensureRange(rightPos);
				target = this.makeWriteBuffer();
			}
		}
		
		System.out.format("Buffer status: %d+%d/%d/%d\n", this.bufferStart, this.buffer.position(), this.buffer.limit(), this.buffer.capacity());
		
		return totalRead >= minRead;
	}
	
	@Override
	public char peek(long offset) throws IndexOutOfBoundsException {
		long position = this.computePositionAfter(offset);
		return this.getBuffered(position);
	}
	
	@Override
	public CharacterStream skip(long offset) throws IndexOutOfBoundsException {
		long endPosition = this.computePositionAfter(offset);
		this.position = endPosition;
		return this;
	}
	
	@Override
	public long position() {
		return this.position;
	}
	
	@Override
	public CharacterStream position(long pos) {
		return skip(Math.subtractExact(pos, this.position));
	}
	
	@Override
	public boolean hasNext(long num) {
		long position = this.computePositionAfter(num);
		System.out.format("Translate offset %d to position %d\n", num, position);
		System.out.format("Check buffer: %s\n", this.isInBuffer(position));
		if (this.isInBuffer(position))
			return true;
		// Attempt to load
		System.out.println("Attempt load");
		boolean result = this.load(position);
		System.out.println("Done load: " + result);
		return result;
	}
	

	@Override
	public ReaderCharacterStream resetToMark() throws InvalidMarkException {
		this.position = this.popMark();
		return this;
	}
	
	@Override
	public String copyFromMark() throws InvalidMarkException {
		this.getBuffered(this.position()); // Should be run before popMark so we don't accidentally GC our region
		
		long markPos = this.popMark();
		// Sanity check
		if (!this.isInBuffer(markPos + 1))
			// This is bad
			throw new IllegalStateException("Marked region (" + markPos + " / " + this.bufferStart + ") was accidentally GC'd");
		
		int startOffset = this.positionToOffset(markPos);
		int endOffset = this.positionToOffset(this.position());
		String result = this.buffer.subSequence(startOffset, endOffset).toString();
		
		return result;
	}
	
	@Override
	public String copyNext(long len) throws IllegalArgumentException, IndexOutOfBoundsException {
		long endPos = this.computePositionAfter(len);
		this.getBuffered(endPos);
		
		int startOffset = positionToOffset(this.position());
		int endOffset = positionToOffset(endPos);
		System.out.format("Buffer status: %d+%d/%d/%d\n", this.bufferStart, this.buffer.position(), this.buffer.limit(), this.buffer.capacity());
		System.out.format("Copy region [%d, %d]\n", startOffset, endOffset);
		this.position = endPos;
		return this.buffer.subSequence(startOffset, endOffset).toString();
	}
	
	protected long getLeftConstraint() {
		if (this.marks.isEmpty())
			return this.position;
		else
			return this.marks.get(0);
	}
	
	@Override
	public void close() throws IOException {
		this.in.close();
		//TODO: release reader/memory?
	}
}
