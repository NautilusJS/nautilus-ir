package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.type.TypeTree;
import com.mindlin.nautilus.tree.type.TypeTreeVisitor;

@Tree.Impl({ Tree.Kind.NULL_LITERAL })
public interface NullLiteralTree extends LiteralTree, TypeTree {
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.NULL_LITERAL;
	}
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return visitor.visitNullLiteral(this, data);
	}

	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitNullLiteral(this, data);
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitNullLiteral(this, data);
	}
}
