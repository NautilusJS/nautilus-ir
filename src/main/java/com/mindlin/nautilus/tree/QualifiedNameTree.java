package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.Tree.Kind;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Kind.QUALIFIED_NAME })
public interface QualifiedNameTree extends EntityName, UnvisitableTree {
	@Tree.Child
	EntityName getLeft();
	
	@Tree.Child
	@Ordering.After("left")
	IdentifierTree getRight();
	
	@Override
	default Kind getKind() {
		return Kind.QUALIFIED_NAME;
	}
}
