package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.EntityName;
import com.mindlin.nautilus.tree.Tree;

@Tree.Impl({ Tree.Kind.TYPE_QUERY })
public interface TypeQueryTree extends TypeTree {
	
	@Tree.Child
	EntityName getExpression();
	
	@Override
	default Kind getKind() {
		return Kind.TYPE_QUERY;
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitTypeQuery(this, data);
	}
}
