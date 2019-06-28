package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ObjectPatternTree.ObjectPatternElement;

@Tree.Impl({ Tree.Kind.REST_PATTERN })
public interface RestPatternElementTree extends PatternTree, ObjectPatternElement {
	@Tree.Child
	PatternTree getValue();
	
	@Override
	default Kind getKind() {
		return Kind.REST_PATTERN;
	}
	
	@Override
	default <R, D> R accept(PatternTreeVisitor<R, D> visitor, D data) {
		return visitor.visitRest(this, data);
	}
}
