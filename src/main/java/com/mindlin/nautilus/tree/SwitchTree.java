package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.SWITCH })
public interface SwitchTree extends StatementTree {
	/**
	 * @return Expression to switch on
	 */
	@Ordering.First
	@Tree.Child
	ExpressionTree getExpression();
	
	/**
	 * @return List of cases
	 */
	@Ordering.After("expression")
	@Tree.Children
	List<? extends SwitchCaseTree> getCases();

	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.SWITCH;
	}

	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitSwitch(this, data);
	}
}
