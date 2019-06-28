package com.mindlin.nautilus.tree;

import java.util.List;

@Tree.Impl({ Tree.Kind.SEQUENCE })
public interface SequenceExpressionTree extends ExpressionTree {
	
	@Tree.Children
	List<? extends ExpressionTree> getElements();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.SEQUENCE;
	}
	
	@Override
	default boolean equivalentTo(Tree other) {
		return this == other;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitSequence(this, data);
	}
}
