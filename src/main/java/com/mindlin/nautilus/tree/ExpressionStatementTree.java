package com.mindlin.nautilus.tree;

@Tree.Impl({ Tree.Kind.EXPRESSION_STATEMENT })
public interface ExpressionStatementTree extends StatementTree {
	@Tree.Child
	ExpressionTree getExpression();

	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.EXPRESSION_STATEMENT;
	}

	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitExpressionStatement(this, data);
	}
}
