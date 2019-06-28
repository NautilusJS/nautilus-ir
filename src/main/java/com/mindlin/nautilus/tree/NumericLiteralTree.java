package com.mindlin.nautilus.tree;

@Tree.Impl({ Tree.Kind.NUMERIC_LITERAL })
public interface NumericLiteralTree extends LiteralTree, PropertyName {
	@Tree.Property
	Number getValue();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.NUMERIC_LITERAL;
	}
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return visitor.visitNumericLiteral(this, data);
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitNumericLiteral(this, data);
	}
	
	@Override
	default <R, D> R accept(PropertyNameVisitor<R, D> visitor, D data) {
		return visitor.visitNumericLiteral(this, data);
	}
}
