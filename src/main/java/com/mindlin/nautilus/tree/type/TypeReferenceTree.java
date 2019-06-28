package com.mindlin.nautilus.tree.type;

import java.util.List;

import com.mindlin.nautilus.tree.EntityName;
import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.TYPE_REFERENCE })
public interface TypeReferenceTree extends TypeTree {
	@Tree.Child
	EntityName getName();
	
	@Tree.Children
	@Ordering(after="name")
	List<? extends TypeTree> getTypeArguments();
	
	@Override
	default Kind getKind() {
		return Kind.TYPE_REFERENCE;
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitTypeReference(this, data);
	}
}
