package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;
import com.mindlin.nautilus.tree.type.TypeTree;

/**
 * 
 * @author mailmindlin
 */
@Tree.Impl({Tree.Kind.PROPERTY_DECLARATION})
public interface PropertyDeclarationTree extends NamedDeclarationTree, ClassElementTree {
	/**
	 * Valid modifiers:
	 * <dl>
	 * 	<dt>{@link Modifiers#PUBLIC}</dt>
	 * 	<dt>{@link Modifiers#PRIVATE}</dt>
	 * 	<dt>{@link Modifiers#PROTECTED}</dt>
	 * 	<dt>{@link Modifiers#READONLY}</dt>
	 * 	<dt>{@link Modifiers#ASYNC}</dt>
	 * 	<dt>{@link Modifiers#OPTIONAL}</dt>
	 * 	<dt>{@link Modifiers#DEFINITE}</dt>
	 * </dl>
	 * @return modifiers
	 */
	@Tree.Property
	Modifiers getModifiers();
	
	@Override
	@Tree.Child
	@Ordering.After("modifiers")
	PropertyName getName();
	
	/**
	 * Get the declared type of this property.
	 * 
	 * @return declared type, else {@code null} if not declared
	 */
	@Tree.Child
	@Ordering.After("name")
	@Nullable TypeTree getType();
	
	@Tree.Child
	@Ordering.After("type")
	@Nullable ExpressionTree getInitializer();
	
	@Override
	default Kind getKind() {
		return Kind.PROPERTY_DECLARATION;
	}
	
	@Override
	default <R, D> R accept(ClassElementVisitor<R, D> visitor, D data) {
		return visitor.visitPropertyDeclaration(this, data);
	}
}
