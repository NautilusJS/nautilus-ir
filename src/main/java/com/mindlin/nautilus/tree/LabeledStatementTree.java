package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.LABELED_STATEMENT })
public interface LabeledStatementTree extends StatementTree {
	@Ordering.First
	@Tree.Child
	IdentifierTree getName();
	
	@Ordering.After("name")
	@Tree.Child
	StatementTree getStatement();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.LABELED_STATEMENT;
	}
	
	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitLabeledStatement(this, data);
	}
}
