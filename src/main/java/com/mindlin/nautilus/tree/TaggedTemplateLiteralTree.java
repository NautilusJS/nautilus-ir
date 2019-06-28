package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.MemberExpressionTree;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.TAGGED_TEMPLATE })
public interface TaggedTemplateLiteralTree extends MemberExpressionTree {
	@Ordering.First
	@Tree.Child
	LeftHandSideExpression getTag();
	
	@Ordering.After("tag")
	@Tree.Child
	TemplateLiteralTree getQuasi();
	
	@Override
	default Kind getKind() {
		return Kind.TAGGED_TEMPLATE;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitTaggedTemplate(this, data);
	}
}
