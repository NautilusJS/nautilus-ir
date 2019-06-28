package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.type.TypeTree;
import com.mindlin.nautilus.tree.type.TypeTreeVisitor;

@Tree.Impl({ Tree.Kind.BOOLEAN_LITERAL })
public interface BooleanLiteralTree extends LiteralTree, TypeTree {
	@Tree.Property
	boolean getValue();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.BOOLEAN_LITERAL;
	}
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return visitor.visitBooleanLiteral(this, data);
	}

	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitBooleanLiteral(this, data);
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitBooleanLiteral(this, data);
	}
}
