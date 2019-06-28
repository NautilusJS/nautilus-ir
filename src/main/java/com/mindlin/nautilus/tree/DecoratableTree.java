package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * Declarations that may be decorated
 * @author mailmindlin
 */
@Tree.NoImpl
public interface DecoratableTree extends Tree {
	@Tree.Children
	@Ordering.First
	List<DecoratorTree> getDecorators();
}
