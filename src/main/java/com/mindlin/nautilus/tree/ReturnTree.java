package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Nullable;

@Tree.Impl({ Tree.Kind.RETURN })
public interface ReturnTree extends StatementTree {
	
	/**
	 * @return return expression (null if not present)
	 */
	@Tree.Child
	@Nullable ExpressionTree getExpression();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.RETURN;
	}
	
	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitReturn(this, data);
	}
}
