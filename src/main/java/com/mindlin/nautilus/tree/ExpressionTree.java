package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;

/**
 * A generic super-interface that's used for trees that represent expressions
 * @author mailmindlin
 */
@Tree.NoImpl
public interface ExpressionTree extends ArrayLiteralElement {
	<R, D> R accept(@NonNull ExpressionTreeVisitor<R, D> visitor, D data);

	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return this.accept((ExpressionTreeVisitor<R, D>) visitor, data);
	}
	
	@Override
	default <R, D> R accept(ArrayLiteralElementVisitor<R, D> visitor, D data) {
		return this.accept((ExpressionTreeVisitor<R, D>) visitor, data);
	}
	
	@Tree.NoImpl
	public interface UnaryExpressionTree extends ExpressionTree {
		
	}
	
	@Tree.NoImpl
	public interface UpdateExpressionTree extends UnaryExpressionTree {
		
	}
	
	@Tree.NoImpl
	public interface LeftHandSideExpression extends UpdateExpressionTree {
		
	}
	
	@Tree.NoImpl
	public interface MemberExpressionTree extends LeftHandSideExpression {
		
	}
	
	@Tree.NoImpl
	public interface PrimaryExpressionTree extends MemberExpressionTree {
		
	}
}
