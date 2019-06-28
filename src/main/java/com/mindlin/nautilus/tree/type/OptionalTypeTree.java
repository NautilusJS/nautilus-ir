package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.Tree;

@Tree.Impl({ Tree.Kind.OPTIONAL_TYPE })
public interface OptionalTypeTree extends TypeTree {
	@Tree.Child
	TypeTree getType();
	
	@Override
	default Kind getKind() {
		return Tree.Kind.OPTIONAL_TYPE;
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitOptionalType(this, data);
	}
}
