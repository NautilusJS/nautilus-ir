package com.mindlin.nautilus.fs;

import java.nio.file.Path;

import com.mindlin.nautilus.util.CharacterStream;

class FakeSourceFile implements SourceFile {
	private final String name;
	private final long[] lines;
	
	public FakeSourceFile(String name) {
		this(name, null);
	}

	public FakeSourceFile(String name, long...lines) {
		this.name = name;
		this.lines = lines;
	}

	@Override
	public Path getPath() {
		return null;
	}

	@Override
	public CharacterStream getSourceStream() {
		return null;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public long[] lineOffsets() {
		return this.lines;
	}

	@Override
	public Path getOriginalPath() {
		return null;
	}

	@Override
	public boolean isExtern() {
		return false;
	}
}
