package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ObjectPatternTree.ObjectPatternElement;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.ASSIGNMENT_PATTERN })
public interface AssignmentPatternTree extends PropertyTree, ObjectPatternElement, UnvisitableTree {
	@Tree.Child
	@Ordering.Before("initializer")
	PatternTree getValue();
	
	@Tree.Child
	@Ordering.Last
	ExpressionTree getInitializer();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.ASSIGNMENT_PATTERN;
	}
}
