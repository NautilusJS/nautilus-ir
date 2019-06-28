package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.annotations.Nullable;

/**
 * A tree for ES6 array destructuring operators.
 * Example:
 * <code>
 * const [i, j, k] = [1, 2, 3];
 * //i = 1, j = 2, k = 3
 * </code>
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.ARRAY_PATTERN })
public interface ArrayPatternTree extends PatternTree {
	/**
	 * Get the elements of the array destructuring in order.
	 * <br/>
	 * Note that elements may be null (for skipping).
	 * 
	 * @return elements
	 */
	@Tree.Children
	List<@Nullable ArrayPatternElement> getElements();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.ARRAY_PATTERN;
	}
	
	@Override
	default <R, D> R accept(PatternTreeVisitor<R, D> visitor, D data) {
		return visitor.visitArrayPattern(this, data);
	}
}
