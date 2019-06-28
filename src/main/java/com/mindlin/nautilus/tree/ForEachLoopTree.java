package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.FOR_IN_LOOP, Tree.Kind.FOR_OF_LOOP })
public interface ForEachLoopTree extends StatementTree {
	@Override
	@Tree.Property
	@NonNull Kind getKind();
	
	@Tree.Child
	@Ordering.After("kind")
	VariableDeclarationOrPatternTree getVariable();

	@Tree.Child
	@Ordering.After("variable")
	ExpressionTree getExpression();
	
	@Tree.Child
	@Ordering.After("expression")
	StatementTree getStatement();

	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitForEachLoop(this, data);
	}
}