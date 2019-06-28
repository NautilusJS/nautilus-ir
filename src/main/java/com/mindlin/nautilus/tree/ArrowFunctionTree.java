package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Nullable;

@Tree.Impl({ Tree.Kind.FUNCTION_EXPRESSION })
public interface ArrowFunctionTree extends ExpressionTree, FunctionTree {
	
	@Override
	default @Nullable PropertyName getName() {
		return null;
	}
	
	@Override
	default Kind getKind() {
		return Tree.Kind.FUNCTION_EXPRESSION;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitArrowFunction(this, data);
	}
}
