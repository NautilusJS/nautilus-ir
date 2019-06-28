package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.PrimaryExpressionTree;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({Tree.Kind.REGEXP_LITERAL})
public interface RegExpLiteralTree extends PrimaryExpressionTree {
	
	@Tree.Property
	String getBody();
	
	@Tree.Property
	@Ordering(after="body")
	String getFlags();
	
	@Override
	default Kind getKind() {
		return Kind.REGEXP_LITERAL;
	}

	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitRegExpLiteral(this, data);
	}
}
