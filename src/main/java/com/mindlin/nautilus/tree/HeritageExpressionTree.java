package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.annotations.Ordering;
import com.mindlin.nautilus.tree.type.TypeTree;

@Tree.Impl({ Tree.Kind.HERITAGE_EXPRESSION })
public interface HeritageExpressionTree extends UnvisitableTree {
	@Tree.Child
	ExpressionTree getExpression();
	
	@Tree.Children
	@Ordering.After("expression")
	List<? extends TypeTree> getTypeAguments();
	
	@Override
	default Kind getKind() {
		return Kind.HERITAGE_EXPRESSION;
	}
}
