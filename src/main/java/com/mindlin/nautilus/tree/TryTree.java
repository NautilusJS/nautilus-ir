package com.mindlin.nautilus.tree;

import java.util.Collections;
import java.util.List;

import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;
import com.mindlin.nautilus.util.annotations.Immutable;

/**
 * Try tree.
 * 
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.TRY })
public interface TryTree extends StatementTree {
	/**
	 * Get block of code executed under the exception catching.
	 * 
	 * @return try block
	 */
	@Ordering.First
	@Tree.Child
	BlockTree getBlock();
	
	/**
	 * Get any/all catch blocks.
	 * 
	 * @return list of catch trees; if none are present,
	 *         {@link Collections#emptyList()} should be returned in favor of
	 *         null.
	 */
	@Ordering.After("block")
	@Tree.Children
	@Immutable @NonNull List<@NonNull ? extends CatchTree> getCatches();
	
	/**
	 * Get finally block, if present.
	 * @return finally block, else null if not present
	 */
	@Ordering.After("catches")
	@Tree.Child
	@Nullable BlockTree getFinallyBlock();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.TRY;
	}
	
	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitTry(this, data);
	}
}
