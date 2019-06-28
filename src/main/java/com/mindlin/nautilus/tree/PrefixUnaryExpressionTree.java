package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.UpdateExpressionTree;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.PREFIX_INCREMENT, Tree.Kind.PREFIX_DECREMENT, Tree.Kind.UNARY_PLUS, Tree.Kind.UNARY_MINUS, Tree.Kind.BITWISE_NOT,
		Tree.Kind.LOGICAL_NOT })
public interface PrefixUnaryExpressionTree extends UpdateExpressionTree {
	@Override
	@Tree.Property
	@Ordering.First
	Kind getKind();
	
	@Tree.Child
	@Ordering.After("kind")
	LeftHandSideExpression getOperand();
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitPrefixUnaryExpression(this, data);
	}
}
