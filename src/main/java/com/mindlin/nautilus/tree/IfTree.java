package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Optional;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.IF })
public interface IfTree extends StatementTree {
	/**
	 * Condition that is tested in the if tree
	 * 
	 * @return condition
	 */
	@Ordering.First
	@Tree.Child
	@NonNull ExpressionTree getExpression();
	
	/**
	 * Statement that is executed if {@link #getExpression()} evaluates to true.
	 * 
	 * @return then
	 */
	@Ordering.After("expression")
	@Tree.Child
	@NonNull StatementTree getThenStatement();
	
	/**
	 * Statement that is executed if {@link #getExpression()} does not evaluate
	 * to true. May not be present.
	 * 
	 * @return else statement; null if not present
	 */
	@Ordering.After("thenStatement")
	@Optional("null")
	@Tree.Child
	@Nullable StatementTree getElseStatement();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.IF;
	}
	
	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitIf(this, data);
	}
}
