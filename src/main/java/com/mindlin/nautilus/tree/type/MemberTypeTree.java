package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.MEMBER_TYPE })
public interface MemberTypeTree extends TypeTree {
	@Tree.Child
	TypeTree getName();
	
	@Tree.Child
	@Ordering.After("name")
	TypeTree getBaseType();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.MEMBER_TYPE;
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitMemberType(this, data);
	}
}
