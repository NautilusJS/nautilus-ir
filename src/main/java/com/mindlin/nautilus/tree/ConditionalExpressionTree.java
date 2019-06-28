package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.CONDITIONAL })
public interface ConditionalExpressionTree extends ExpressionTree {
	@Tree.Child
	ExpressionTree getCondition();
	
	@Tree.Child
	@Ordering(after="condition")
	ExpressionTree getFalseExpression();
	
	@Tree.Child
	@Ordering(after="falseExpression")
	ExpressionTree getTrueExpression();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.CONDITIONAL;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitConditionalExpression(this, data);
	}
}
