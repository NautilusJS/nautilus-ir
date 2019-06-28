package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.UpdateExpressionTree;
import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.POSTFIX_INCREMENT, Tree.Kind.POSTFIX_DECREMENT })
public interface PostfixUnaryExpressionTree extends UpdateExpressionTree {
	@Override
	@Tree.Property
	@Ordering.First
	@NonNull Kind getKind();
	
	@Tree.Child
	@Ordering.After("kind")
	@NonNull LeftHandSideExpression getOperand();
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitPostfixUnaryExpression(this, data);
	}
}
