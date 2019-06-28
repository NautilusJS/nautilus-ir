package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Optional;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.CASE })
public interface SwitchCaseTree extends UnvisitableTree {
	@Optional("null")
	@Ordering.First
	@Tree.Child
	@Nullable ExpressionTree getExpression();
	
	//TODO: is property?
	default boolean isDefault() {
		return getExpression() == null;
	}
	
	@Ordering.Last
	@Tree.Children
	List<? extends StatementTree> getBody();
	
	@Override
	default Kind getKind() {
		return Kind.CASE;
	}
}