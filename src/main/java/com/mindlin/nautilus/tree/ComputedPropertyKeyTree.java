package com.mindlin.nautilus.tree;

@Tree.Impl({Tree.Kind.COMPUTED_PROPERTY_KEY})
public interface ComputedPropertyKeyTree extends PropertyName {
	
	@Tree.Child
	ExpressionTree getExpression();
	
	@Override
	default Kind getKind() {
		return Kind.COMPUTED_PROPERTY_KEY;
	}
	
	@Override
	default <R, D> R accept(PropertyNameVisitor<R, D> visitor, D data) {
		return visitor.visitComputedPropertyKey(this, data);
	}
}
