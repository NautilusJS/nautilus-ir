package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ObjectPatternTree.ObjectPatternElement;

@Tree.Impl({ Tree.Kind.SHORTHAND_ASSIGNMENT_PATTERN })
public interface ShorthandAssignmentPatternTree extends PropertyTree, ObjectPatternElement, UnvisitableTree {
	@Tree.Child
	IdentifierTree getValue();
	
	@Override
	default Kind getKind() {
		return Kind.SHORTHAND_ASSIGNMENT_PATTERN;
	}
}
