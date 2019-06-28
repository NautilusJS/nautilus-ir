package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.PrimaryExpressionTree;
import com.mindlin.nautilus.tree.type.TypeTree;
import com.mindlin.nautilus.tree.type.TypeTreeVisitor;

@Tree.Impl({ Tree.Kind.THIS_EXPRESSION })
public interface ThisExpressionTree extends PrimaryExpressionTree, TypeTree {
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.THIS_EXPRESSION;
	}
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return visitor.visitThis(this, data);
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitThis(this, data);
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitThis(this, data);
	}
}
