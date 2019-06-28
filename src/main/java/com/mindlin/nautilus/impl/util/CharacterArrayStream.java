package com.mindlin.nautilus.impl.util;

import java.nio.InvalidMarkException;

import com.mindlin.nautilus.util.CharacterStream;

public class CharacterArrayStream extends AbstractCharacterStream {
	protected int position = -1;
	protected final char[] data;
	
	public CharacterArrayStream(char[] data) {
		this.data = data;
	}
	
	public CharacterArrayStream(String data) {
		this(data.toCharArray());
	}
	
	@Override
	public char current() {
		return this.data[this.position];
	}
	
	@Override
	public char next() {
		return this.data[++this.position];
	}
	
	@Override
	public CharacterStream skip(long offset) {
		this.position = Math.toIntExact(Math.addExact(this.position, offset));
		return this;
	}
	
	@Override
	public long position() {
		return this.position;
	}
	
	@Override
	public CharacterStream position(long pos) {
		this.position = Math.toIntExact(pos);
		return this;
	}
	
	@Override
	public boolean hasNext() {
		return this.position < this.data.length - 1;
	}
	
	@Override
	public boolean hasNext(long num) {
		if (num > Integer.MAX_VALUE)
			return false;
		if (num < 0)
			throw new IllegalArgumentException();
		
		return this.position < this.data.length - num;
	}
	
	@Override
	public CharacterStream skipWhitespace() {
		while (hasNext() && Characters.isJsWhitespace(this.data[this.position + 1]))
			this.position++;
		return this;
	}
	
	@Override
	public CharacterStream skipWhitespace(boolean passNewlines) {
		if (passNewlines)
			return this.skipWhitespace();
		
		while (this.position + 1 < this.data.length && Characters.isJsWhitespace(this.data[this.position + 1]))
			if (this.data[++this.position] == '\n')
				break;
		
		return this;
	}
	
	@Override
	public char peek(long offset) {
		long index = Math.addExact(this.position, offset);
		
		if (index > this.data.length)//Also checks if > Integer.MAX_INT
			throw new IndexOutOfBoundsException("Cannot look ahead by " + offset);
		
		return this.data[(int) index];
	}

	@Override
	public String copyFromMark() throws InvalidMarkException {
		final long mark = this.popMark();
		final int len = (int) (position() - mark);
		char[] buf = new char[len];
		System.arraycopy(this.data, (int)mark + 1, buf, 0, len);
		return new String(buf, 0, len);
	}
	
	@Override
	public void close() {
		//TODO: release data?
	}
}