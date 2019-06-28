package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.UnaryExpressionTree;
import com.mindlin.nautilus.tree.annotations.NonNull;

@Tree.Impl({ Tree.Kind.DELETE })
public interface DeleteExpression extends UnaryExpressionTree {
	@Override
	default Kind getKind() {
		return Tree.Kind.DELETE;
	}
	
	@Tree.Child
	@NonNull UnaryExpressionTree getExpression();
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitDeleteExpression(this, data);
	}
}