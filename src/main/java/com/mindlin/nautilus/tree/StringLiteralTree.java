package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.type.TypeTree;
import com.mindlin.nautilus.tree.type.TypeTreeVisitor;

@Tree.Impl({ Tree.Kind.STRING_LITERAL })
public interface StringLiteralTree extends LiteralTree, PropertyName, TypeTree {
	@Tree.Property
	String getValue();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.STRING_LITERAL;
	}
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return visitor.visitStringLiteral(this, data);
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitStringLiteral(this, data);
	}
	
	@Override
	default <R, D> R accept(PropertyNameVisitor<R, D> visitor, D data) {
		return visitor.visitStringLiteral(this, data);
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitStringLiteral(this, data);
	}
}
