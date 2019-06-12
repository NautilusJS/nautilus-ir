package com.mindlin.nautilus.fs;

import static com.mindlin.nautilus.fs.SourcePosition.INVALID_LINE;
import static com.mindlin.nautilus.fs.SourcePosition.INVALID_COL;
import static com.mindlin.nautilus.fs.SourcePosition.INVALID_OFFSET;
import java.io.Serializable;
import java.util.Objects;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class SourceRange implements Serializable {
	private static final long serialVersionUID = -1130923738278939904L;
	
	public static @NonNull SourceRange startingFrom(SourcePosition start) {
		return new SourceRange(start, SourcePosition.invalid());
	}
	
	public static @NonNull SourceRange endingAt(SourcePosition end) {
		return new SourceRange(SourcePosition.invalid(), end);
	}
	
	public static @NonNull SourceRange invalid() {
		//TODO: cache?
		return new SourceRange(SourcePosition.invalid(), SourcePosition.invalid());
	}
	
	protected @Nullable SourcePosition start;
	protected @Nullable SourcePosition end;
	
	/**
	 * Create range spanning from {@code start} to {@code end}.
	 * @param start Start position (inclusive)
	 * @param end End position (inclusive)
	 */
	public SourceRange(SourcePosition start, SourcePosition end) {
		this.start = start == SourcePosition.invalid() ? null : start;
		this.end = end == SourcePosition.invalid() ? null : end;
	}
	
	/**
	 * @return Start position (inclusive). May be null.
	 */
	public @NonNull SourcePosition getStart() {
		SourcePosition start = this.start;
		return start == null ? SourcePosition.invalid() : start;
	}
	
	/**
	 * @return End position (inclusive). May be null.
	 */
	public @NonNull SourcePosition getEnd() {
		SourcePosition end = this.end;
		return end == null ? SourcePosition.invalid() : end;
	}
	
	public boolean isFullyDefined() {
		return this.start != null && this.end != null;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getStart(), this.getEnd());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SourceRange))
			return false;
		
		SourceRange other = (SourceRange) obj;
		return Objects.equals(this.getStart(), other.getStart())
				&& Objects.equals(this.getEnd(), other.getEnd());
	}
	
	@Override
	public String toString() {
		final @NonNull SourcePosition start = this.getStart(),
				end = this.getEnd(),
				invalid = SourcePosition.invalid();
		
		if (start == invalid) {
			if (end == invalid)
				return "<???>";
			return String.format("<? - %s>", end);
		} else if (end == invalid) {
			return String.format("<%s - ?>", start);
		}
		
		if (!Objects.equals(start.getSource(), end.getSource()))
			return String.format("<%s - %s>", this.getStart(), this.getEnd());
		
		String name = start.getSource() == null ? "unknown" : start.getSource().getName();
		
		// Handle missing lines
		if (start.getLine() == INVALID_LINE) {
			if (end.getLine() == INVALID_LINE)
				return String.format("<%s(%d - %d)>", name, start.getOffset(), end.getOffset());
			return String.format("<%s(%d - %d:%d)>", name, start.getOffset(), end.getLine(), end.getCol());
		} else if (end.getLine() == INVALID_LINE) {
			return String.format("<%s(%d:%d - %d)>", name, start.getLine(), start.getCol(), end.getOffset());
		}
		
		// Handle same line
		if (start.getLine() == end.getLine()) {
			if (start.getCol() == INVALID_COL) {
				if (end.getCol() == INVALID_COL)
					return String.format("<%s(%d)>", name, start.getLine());
			} else if (end.getCol() != INVALID_COL) {
				return String.format("<%s(%d:%d-%d)>", name, start.getLine(), start.getCol(), end.getCol());
			}
		}
		
		// Handle missing cols
		if (start.getLine() != end.getLine() && start.getCol() == INVALID_COL && end.getCol() == INVALID_COL)
			return String.format("<%s(%d-%d)>", name, start.getLine(), end.getLine());
		
		return String.format("<%s(%d:%d - %d:%d)>", name, start.getLine(), start.getCol(), end.getLine(), end.getCol());
	}
}
