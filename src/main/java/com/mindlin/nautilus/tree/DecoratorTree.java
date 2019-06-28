package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ExpressionTree.LeftHandSideExpression;
import com.mindlin.nautilus.tree.Tree.Kind;
import com.mindlin.nautilus.tree.annotations.NonNull;

@Tree.Impl({Kind.DECORATOR})
public interface DecoratorTree extends UnvisitableTree {
	@Tree.Child
	@NonNull LeftHandSideExpression getExpression();
	
	@Override
	default Kind getKind() {
		return Kind.DECORATOR;
	}
}
