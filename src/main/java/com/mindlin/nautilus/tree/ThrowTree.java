package com.mindlin.nautilus.tree;

@Tree.Impl({ Tree.Kind.THROW })
public interface ThrowTree extends StatementTree {
	@Tree.Child
	ExpressionTree getExpression();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.THROW;
	}
	
	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitThrow(this, data);
	}
}
