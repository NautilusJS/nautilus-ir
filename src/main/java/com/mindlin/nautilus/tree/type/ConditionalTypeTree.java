package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.CONDITIONAL_TYPE })
public interface ConditionalTypeTree extends TypeTree {
	@Tree.Child
	TypeTree getCheckType();
	
	@Tree.Child
	@Ordering.After("checkType")
	TypeTree getLimitType();
	
	@Tree.Child
	@Ordering.After("limitType")
	TypeTree getConcequent();
	
	@Tree.Child
	@Ordering.After("concequent")
	TypeTree getAlternate();
	
	@Override
	default Kind getKind() {
		return Kind.CONDITIONAL_TYPE;
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitConditionalType(this, data);
	}
}
