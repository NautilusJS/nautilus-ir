package com.mindlin.nautilus.fs;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Objects;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Represents a position in a {@link SourceFile}.
 * 
 * @author mailmindlin
 */
public class SourcePosition implements Serializable {
	private static final long serialVersionUID = 4861187271740573475L;
	
	private static final SourcePosition INVALID = new SourcePosition();
	/** Marker value for missing offset */
	public static final long INVALID_OFFSET = -1;
	/** Marker value for missing line number */
	public static final long INVALID_LINE = -1;
	/** Marker value for missing column number */
	public static final long INVALID_COL = -1;
	
	public static @NonNull SourcePosition invalid() {
		return INVALID;
	}
	
	protected final @Nullable SourceFile source;
	protected final long offset;
	protected final long line;
	protected final long col;
	
	// For serialization
	private SourcePosition() {
		this(null, INVALID_OFFSET, -1, -1);
	}
	
	public SourcePosition(@Nullable SourceFile source, long offset) {
		this(source, offset, INVALID_LINE, INVALID_COL);
	}
	
	public SourcePosition(@Nullable SourceFile source, long offset, long line, long col) {
		this.source = source;
		this.offset = offset;
		this.line = line;
		this.col = col;
	}
	
	/**
	 * @return Character position offset (starting from 0), else {@link #INVALID_OFFSET} if not present.
	 */
	public long getOffset() {
		return this.offset;
	}
	
	/**
	 * @return Line number (starting from 0), else {@link #INVALID_LINE} if not present.
	 */
	public long getLine() {
		return this.line;
	}
	
	/**
	 * @return Column number (starting from 0), else {@link #INVALID_COL} if not present.
	 */
	public long getCol() {
		return this.col;
	}
	
	/**
	 * @return Source file
	 */
	public @Nullable SourceFile getSource() {
		return this.source;
	}
	
	@Override
	public String toString() {
		SourceFile source = this.getSource();
		String sourceName = source == null ? "unknown" : source.getName();
		
		long row = this.getLine();
		if (row != INVALID_LINE)
			return String.format("%s(%d:%d)", sourceName, row, this.getCol());
		
		// Try fallback to offset
		long offset = this.getOffset();
		if (offset != INVALID_OFFSET)
			return String.format("%s(%d)", sourceName, this.getOffset());
		
		return String.format("%s(???)", sourceName);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getSource(), this.getOffset(), this.getLine(), this.getCol());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SourcePosition))
			return false;
		
		SourcePosition otherPos = (SourcePosition) obj;
		return this.getOffset() == otherPos.getOffset()
				&& this.getLine() == otherPos.getLine()
				&& this.getCol() == otherPos.getCol()
				&& Objects.equals(this.getSource(), otherPos.getSource());
	}
	
	/**
	 * Replace invalid SourcePositions with the {@link #INVALID} constant.
	 * @return
	 * @throws ObjectStreamException
	 */
	@SuppressWarnings("unused")
	private SourcePosition readResolve() throws ObjectStreamException {
		// Don't mess with whatever subclasses 
		if (this.getClass() == SourcePosition.class && this != INVALID && this.equals(INVALID))
			return INVALID;
		return this;
	}
}
