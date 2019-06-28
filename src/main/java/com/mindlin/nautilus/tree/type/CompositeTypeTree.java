package com.mindlin.nautilus.tree.type;

import java.util.List;

import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * Shared interface for both {@link com.mindlin.nautilus.tree.Tree.Kind#TYPE_UNION TYPE_UNION} and {@link com.mindlin.nautilus.tree.Tree.Kind#TYPE_INTERSECTION TYPE_INTERSECTION}.
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.TYPE_UNION, Tree.Kind.TYPE_INTERSECTION })
public interface CompositeTypeTree extends TypeTree {
	
	@Override
	@Tree.Property
	@NonNull Kind getKind();
	
	/**
	 * Get a set of types that are either all the alternatives (if this is a type union tree) or
	 * all the boundaries (if this is a type intersection).
	 * <p>
	 * This method will traverse all children recursively. For example, the type expression
	 * <code> (A | B | (C & D)) => Union[A, Union[B, Intersection[C, D]]] => {A, B, Intersection[C, D]}</code>.
	 * </p>
	 * <p>
	 * Note that this method will not recurse through binary type trees that have different kinds
	 * than the current one (e.g., <code> (A | (B & (C | A))) => {A, B & (C | A)}</code>).
	 * </p>
	 * @return set of all types encompassed by this union/intersection
	 */
	/**
	 * Get a list of constituent types.
	 * Returned list should have size of >= 2
	 * @return constituent types
	 */
	@Tree.Children
	@Ordering(after="kind")
	@NonNull List<@NonNull TypeTree> getConstituents();
	
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		if (this.getKind() == Kind.TYPE_UNION)
			return visitor.visitUnionType(this, data);
		return visitor.visitIntersectionType(this, data);
	}
}
