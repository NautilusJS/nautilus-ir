package com.mindlin.nautilus.tree;

import java.util.Objects;

import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * Contains shared methods on {@link FunctionDeclarationTree} and {@link FunctionExpressionTree}
 * @author mailmindlin
 */
@Tree.ADT
public interface FunctionTree extends SignatureDeclarationTree {
	/**
	 * @return modifiers (not null)
	 */
	@Tree.Property
	@NonNull Modifiers getModifiers();
	
	/**
	 * Get function identifier.
	 * Null if expression
	 * @return name
	 */
	@Override
	@Tree.Child
	@Ordering.After("modifiers")
	@Nullable PropertyName getName();
	
	/**
	 * Get function body.
	 * @return function body
	 */
	@Tree.Child
	@Ordering.After("name")
	@Nullable StatementTree getBody();
	
	@Override
	default boolean equivalentTo(Tree other) {
		if (this == other)
			return true;
		
		if (other == null || this.getKind() != other.getKind() || !(other instanceof FunctionTree) || this.hashCode() != other.hashCode())
			return false;
		
		FunctionTree o = (FunctionTree) other;
		
		return Objects.equals(this.getModifiers(), o.getModifiers())
				&& Tree.equivalentTo(this.getName(), o.getName())
				&& Tree.equivalentTo(this.getReturnType(), o.getReturnType())
				&& Tree.equivalentTo(this.getParameters(), o.getParameters())
				&& Tree.equivalentTo(this.getBody(), o.getBody());
	}
}
