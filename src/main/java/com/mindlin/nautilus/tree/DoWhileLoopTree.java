package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.DO_WHILE_LOOP })
public interface DoWhileLoopTree extends StatementTree {
	@Tree.Child
	StatementTree getStatement();
	
	@Ordering.After("statement")
	@Tree.Child
	ExpressionTree getCondition();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.DO_WHILE_LOOP;
	}

	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitDoWhileLoop(this, data);
	}
}