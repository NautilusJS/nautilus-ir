package com.mindlin.nautilus.fs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.jdt.annotation.NonNull;

import com.mindlin.nautilus.impl.util.ReaderCharacterStream;
import com.mindlin.nautilus.util.CharacterStream;

public class LazySourceFile implements SourceFile {
	protected @NonNull Path path;
	protected Charset charset;
	
	public LazySourceFile(@NonNull Path path) {
		this(path, Charset.defaultCharset());
	}
	
	public LazySourceFile(@NonNull Path path, Charset charset) {
		this.path = path;
		this.charset = charset;
	}
	
	@Override
	public Path getPath() {
		return this.path;
	}
	
	protected Reader openReader() throws IOException {
		return Files.newBufferedReader(path, this.charset);
	}

	@Override
	@SuppressWarnings("resource")
	public CharacterStream getSourceStream() throws IOException {
		BufferedReader br = Files.newBufferedReader(this.getPath());
		return new ReaderCharacterStream(br);
	}

	@Override
	public String getName() {
		return this.getPath().getFileName().toString();
	}

	@Override
	public long[] lineOffsets() {
		return null;
	}

	@Override
	public Path getOriginalPath() {
		return getPath();
	}

	@Override
	public boolean isExtern() {
		// TODO Auto-generated method stub
		return false;
	}
}
