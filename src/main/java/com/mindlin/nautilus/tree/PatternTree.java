package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;

/**
 * A generic super-interface for trees representing ES6 destructuring operators.
 * 
 * @see ArrayPatternTree
 * @see ObjectPatternTree
 * @see AssignmentPatternTree
 * 
 * @author mailmindlin
 */
@Tree.NoImpl
public interface PatternTree extends VariableDeclarationOrPatternTree, DeclarationName {
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return this.accept((PatternTreeVisitor<R, D>) visitor, data);
	}
	
	<R, D> R accept(@NonNull PatternTreeVisitor<R, D> visitor, D data);
}
