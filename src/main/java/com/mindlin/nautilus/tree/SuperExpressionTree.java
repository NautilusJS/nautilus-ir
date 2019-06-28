package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.PrimaryExpressionTree;

@Tree.Impl({ Tree.Kind.SUPER_EXPRESSION })
public interface SuperExpressionTree extends PrimaryExpressionTree {
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.SUPER_EXPRESSION;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitSuper(this, data);
	}
}
