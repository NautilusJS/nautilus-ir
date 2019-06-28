package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.MemberExpressionTree;
import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.PROPERTY_ACCESS })
public interface PropertyAccessTree extends MemberExpressionTree, PatternTree, NamedDeclarationTree {
	@Tree.Child
	@NonNull LeftHandSideExpression getExpression();
	
	@Override
	@Tree.Child
	@Ordering.After("expression")
	@NonNull IdentifierTree getName();
	
	@Override
	default Kind getKind() {
		return Kind.PROPERTY_ACCESS;
	}
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return visitor.visitPropertyAccess(this, data);
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitPropertyAccess(this, data);
	}
	
	@Override
	default <R, D> R accept(PatternTreeVisitor<R, D> visitor, D data) {
		return visitor.visitPropertyAccess(this, data);
	}
	
}
