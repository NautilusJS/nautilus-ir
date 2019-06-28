package com.mindlin.nautilus.tree;

@Tree.Impl({ Tree.Kind.SPREAD })
public interface SpreadElementTree extends ArrayLiteralElement, ObjectLiteralElement {
	@Tree.Child
	ExpressionTree getExpression();
	
	@Override
	default Kind getKind() {
		return Tree.Kind.SPREAD;
	}
	
	@Override
	default <R, D> R accept(ArrayLiteralElementVisitor<R, D> visitor, D data) {
		return visitor.visitSpread(this, data);
	}
	
	@Override
	default <R, D> R accept(ObjectLiteralElementVisitor<R, D> visitor, D data) {
		return visitor.visitSpread(this, data);
	}
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return visitor.visitSpread(this, data);
	}
}
