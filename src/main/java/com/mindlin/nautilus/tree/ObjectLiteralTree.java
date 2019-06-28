package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.ExpressionTree.PrimaryExpressionTree;

@Tree.Impl({ Tree.Kind.OBJECT_LITERAL })
public interface ObjectLiteralTree extends PrimaryExpressionTree {
	@Tree.Children
	List<? extends ObjectLiteralElement> getProperties();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.OBJECT_LITERAL;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitObjectLiteral(this, data);
	}
}