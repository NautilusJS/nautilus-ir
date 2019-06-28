package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.PrimaryExpressionTree;

@Tree.Impl({ Tree.Kind.PARENTHESIZED })
public interface ParenthesizedTree extends PrimaryExpressionTree, JSDocContainer {
	
	@Tree.Child
	ExpressionTree getExpression();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.PARENTHESIZED;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitParentheses(this, data);
	}
}