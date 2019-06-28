package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * Type representing a type expression in the form of {@code keyof TYPE} or {@code unique TYPE}
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.KEYOF_TYPE, Tree.Kind.UNIQUE_TYPE })
public interface UnaryTypeTree extends TypeTree {

	/**
	 * Get the type that this is a key of
	 * @return base type
	 */
	@Tree.Child
	@Ordering.After("kind")
	TypeTree getBaseType();
	
	@Override
	@Tree.Property
	@Ordering.First
	Kind getKind();
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitUnaryType(this, data);
	}
}
