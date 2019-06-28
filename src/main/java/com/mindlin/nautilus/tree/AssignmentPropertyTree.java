package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * Object literal assignment property, in form of {@code key: value}
 * 
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.ASSIGNMENT_PROPERTY })
public interface AssignmentPropertyTree extends PropertyTree, ObjectLiteralElement {
	@Tree.Child
	@Ordering.Last
	ExpressionTree getInitializer();
	
	@Override
	default Kind getKind() {
		return Kind.ASSIGNMENT_PROPERTY;
	}
	
	@Override
	default <R, D> R accept(@NonNull ObjectLiteralElementVisitor<R, D> visitor, D data) {
		return visitor.visitAssignmentProperty(this, data);
	}
}
