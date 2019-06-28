package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Ordering;
import com.mindlin.nautilus.tree.type.TypeElementTree;
import com.mindlin.nautilus.tree.type.TypeTree;

@Tree.Impl({ Tree.Kind.PROPERTY_SIGNATURE })
public interface PropertySignatureTree extends NamedDeclarationTree, TypeElementTree {
	/**
	 * Valid modifiers:
	 * <dl>
	 * 	<dt>{@link Modifiers#PUBLIC}</dt>
	 * 	<dt>{@link Modifiers#PRIVATE}</dt>
	 * 	<dt>{@link Modifiers#PROTECTED}</dt>
	 * 	<dt>{@link Modifiers#READONLY}</dt>
	 * 	<dt>{@link Modifiers#ASYNC}</dt>
	 * <dt>{@link Modifiers#OPTIONAL}</dt>
	 * </dl>
	 * @return Modifiers
	 */
	@Tree.Property
	@NonNull Modifiers getModifiers();
	
	@Override
	@Tree.Child
	@Ordering.After("modifiers")
	PropertyName getName();
	
	@Tree.Child
	@Ordering.After("name")
	TypeTree getType();
	
	@Tree.Child
	@Ordering.After("type")
	ExpressionTree getInitializer();
	
	@Override
	default Kind getKind() {
		return Kind.PROPERTY_SIGNATURE;
	}
	
	@Override
	default <R, D> R accept(TypeElementVisitor<R, D> visitor, D data) {
		return visitor.visitPropertySignature(this, data);
	}
}