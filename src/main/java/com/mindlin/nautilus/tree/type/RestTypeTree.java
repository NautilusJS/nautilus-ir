package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.Tree;

@Tree.Impl({ Tree.Kind.REST_TYPE })
public interface RestTypeTree extends TypeTree {
	@Tree.Child
	TypeTree getType();
	
	@Override
	default Kind getKind() {
		return Tree.Kind.REST_TYPE;
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitRestType(this, data);
	}
}
