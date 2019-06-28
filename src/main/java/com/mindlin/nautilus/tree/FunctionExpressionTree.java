package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.PrimaryExpressionTree;

@Tree.Impl({ Tree.Kind.FUNCTION_EXPRESSION })
public interface FunctionExpressionTree extends FunctionTree, PrimaryExpressionTree {
	
	@Override
	default Kind getKind() {
		return Tree.Kind.FUNCTION_EXPRESSION;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitFunctionExpression(this, data);
	}
}
