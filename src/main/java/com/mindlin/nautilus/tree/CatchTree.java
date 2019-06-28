package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.CATCH })
public interface CatchTree extends UnvisitableTree {
	/**
	 * Get declared parameter.
	 * @return declared parameter (may be null)
	 */
	@Tree.Child
	@Nullable VariableDeclaratorTree getParameter();
	
	@Tree.Child
	@Ordering(after="parameter")
	@NonNull BlockTree getBlock();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.CATCH;
	}

	@Override
	default boolean equivalentTo(Tree otherTree) {
		//Hit the low hanging fruit first
		if (this == otherTree)
			return true;
		
		if (otherTree == null || this.getKind() != otherTree.getKind() || this.hashCode() != otherTree.hashCode())
			return false;
		
		
		CatchTree other = (CatchTree) otherTree;
		
		if (!Tree.equivalentTo(this.getBlock(), other.getBlock()))
			return false;
		
		if (!Tree.equivalentTo(this.getParameter(), other.getParameter()))
			return false;
		
		return true;
	}
}
