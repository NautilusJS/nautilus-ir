package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.WITH })
public interface WithTree extends StatementTree {
	@Tree.Child
	ExpressionTree getScope();
	
	@Ordering.After("scope")
	@Tree.Child
	StatementTree getStatement();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.WITH;
	}
	
	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitWith(this, data);
	}
}
