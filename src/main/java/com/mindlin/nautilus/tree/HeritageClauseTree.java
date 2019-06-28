package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * Extends/implements clause
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.EXTENDS_CLAUSE, Tree.Kind.IMPLEMENTS_CLAUSE })
public interface HeritageClauseTree extends UnvisitableTree {
	@Override
	@Ordering(first = true)
	@Tree.Property
	@NonNull Kind getKind();
	
	@Tree.Children
	List<? extends HeritageExpressionTree> getTypes();
}
