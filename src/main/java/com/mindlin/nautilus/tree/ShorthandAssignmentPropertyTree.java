package com.mindlin.nautilus.tree;

@Tree.Impl({ Tree.Kind.SHORTHAND_ASSIGNMENT_PROPERTY })
public interface ShorthandAssignmentPropertyTree extends AssignmentPropertyTree {
	@Override
	@Tree.Child
	IdentifierTree getName();
	
	@Override
	default Kind getKind() {
		return Kind.SHORTHAND_ASSIGNMENT_PROPERTY;
	}
	
	@Override
	default <R, D> R accept(ObjectLiteralElementVisitor<R, D> visitor, D data) {
		return visitor.visitShorthandAssignmentProperty(this, data);
	}
}
