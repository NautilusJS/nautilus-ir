package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.UnaryExpressionTree;
import com.mindlin.nautilus.tree.annotations.NonNull;

@Tree.Impl({ Tree.Kind.VOID })
public interface VoidExpressionTree extends UnaryExpressionTree {
	@Tree.Child
	@NonNull UnaryExpressionTree getExpression();
	
	@Override
	default Kind getKind() {
		return Tree.Kind.VOID;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitVoid(this, data);
	}
}