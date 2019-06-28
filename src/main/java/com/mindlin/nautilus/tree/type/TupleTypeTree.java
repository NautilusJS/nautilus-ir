package com.mindlin.nautilus.tree.type;

import java.util.List;

import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.annotations.NonNull;

/**
 * Tuple type. For all types in form of {@code [T1, T2, ..., Tn]}.
 * <p>
 * Note that empty tuples ({@code []}) are not valid. Instead, they are treated as
 * {@link ArrayTypeTree} {@code any[]}.
 * </p>
 * @author mailmindlin
 *
 */
@Tree.Impl({ Tree.Kind.TUPLE_TYPE })
public interface TupleTypeTree extends TypeTree {
	/**
	 * Get the types of each slot, in order.
	 * <p>
	 * While the returned list MUST be not null, it also should have a size >= 1
	 * (an empty tuple is the same as an array).
	 * </p>
	 * <p>
	 * The returned value SHOULD NOT be modified.
	 * </p>
	 * 
	 * @return slot types
	 */
	@Tree.Children
	@NonNull List<? extends TypeTree> getSlotTypes();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.TUPLE_TYPE;
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitTupleType(this, data);
	}
}
