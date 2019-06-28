package com.mindlin.nautilus.tree;

import java.util.List;

@Tree.Impl({ Tree.Kind.OBJECT_PATTERN })
public interface ObjectPatternTree extends PatternTree {
	@Tree.Children
	List<ObjectPatternElement> getProperties();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.OBJECT_PATTERN;
	}
	
	@Override
	default <R, D> R accept(PatternTreeVisitor<R, D> visitor, D data) {
		return visitor.visitObjectPattern(this, data);
	}
	
	@Tree.NoImpl
	public static interface ObjectPatternElement extends DeclarationTree {
		
	}
}
