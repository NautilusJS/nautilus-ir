package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.Modifiers;
import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.MAPPED_TYPE })
public interface MappedTypeTree extends TypeTree {
	@Tree.Property
	Modifiers getModifiers();
	
	@Tree.Child
	@Ordering.After("modifiers")
	TypeParameterDeclarationTree getParameter();
	
	@Tree.Child
	@Ordering.After("parameter")
	TypeTree getType();
	
	@Override
	default Kind getKind() {
		return Kind.MAPPED_TYPE;
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitMappedType(this, data);
	}
}
