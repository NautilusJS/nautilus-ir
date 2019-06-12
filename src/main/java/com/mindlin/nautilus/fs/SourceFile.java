package com.mindlin.nautilus.fs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import org.eclipse.jdt.annotation.Nullable;

import com.mindlin.nautilus.impl.util.CharacterArrayStream;
import com.mindlin.nautilus.impl.util.LineMap;
import com.mindlin.nautilus.impl.util.LineMap.CompiledLineMap;
import com.mindlin.nautilus.util.CharacterStream;

/**
 * Abstract away a JS source
 */
public interface SourceFile {
	/**
	 * @return Path to file location. May be null.
	 */
	@Nullable Path getPath();
	
	/**
	 * Get stream of source
	 * @return New stream. Should be unique across calls.
	 * @throws IOException 
	 */
	CharacterStream getSourceStream() throws IOException;
	
	/**
	 * Get unique source file name
	 * @return Source file name
	 */
	String getName();
	
	/**
	 * Get LineMap for file
	 * @return Line map (or null, if not present)
	 */
	default LineMap getLineMap() {
		return new CompiledLineMap(this, lineOffsets());
	}
	
	long[] lineOffsets();
	
	Path getOriginalPath();
	
	boolean isExtern();
	
	default int getLineOffset(long offset) {
		long[] offsets = lineOffsets();
		int idx = Arrays.binarySearch(offsets, offset);
		if (idx < 0)
			idx = 1 - idx;
		return idx;
	}
	
	default SourcePosition getOffsetPosotion(long offset) {
		long[] offsets = lineOffsets();
		int line = Arrays.binarySearch(offsets, offset);
		if (line < 0)
			line = 1 - line;
		int col = (int) (offset - offsets[line]);
		return new SourcePosition(this, offset, line, col);
	}
	
	public static class NominalSourceFile implements SourceFile {
		protected final String name;
		protected final String text;
		
		public NominalSourceFile(String name, String text) {
			this.name = name;
			this.text = text;
		}

		@Override
		public Path getPath() {
			throw new UnsupportedOperationException();
		}

		@Override
		public CharacterStream getSourceStream() {
			return new CharacterArrayStream(this.text);
		}

		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public long[] lineOffsets() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Path getOriginalPath() {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isExtern() {
			return false;
		}
	}
}
