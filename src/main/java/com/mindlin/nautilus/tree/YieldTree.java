package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.Tree.Kind;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Kind.YIELD, Kind.YIELD_GENERATOR })
public interface YieldTree extends ExpressionTree {
	@Override
	@Tree.Property(hash = false)
	@Ordering.First
	Kind getKind();
	
	@Tree.Child
	@Nullable ExpressionTree getExpression();
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitYield(this, data);
	}
}