package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.UnaryExpressionTree;
import com.mindlin.nautilus.tree.annotations.NonNull;

@Tree.Impl({ Tree.Kind.AWAIT })
public interface AwaitExpressionTree extends UnaryExpressionTree {
	@Tree.Child
	@NonNull /* Unary */ExpressionTree getExpression();
	
	@Override
	default Kind getKind() {
		return Tree.Kind.AWAIT;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitAwait(this, data);
	}
}