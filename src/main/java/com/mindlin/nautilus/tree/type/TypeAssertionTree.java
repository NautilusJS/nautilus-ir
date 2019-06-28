package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.ExpressionTree;
import com.mindlin.nautilus.tree.ExpressionTreeVisitor;
import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.ExpressionTree.LeftHandSideExpression;
import com.mindlin.nautilus.tree.annotations.NonNull;

@Tree.Impl({ Tree.Kind.TYPE_ASSERTION })
public interface TypeAssertionTree extends LeftHandSideExpression {
	@Tree.Child
	@NonNull ExpressionTree getExpression();
	
	@Override
	default Kind getKind() {
		return Tree.Kind.TYPE_ASSERTION;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitTypeAssertion(this, data);
	}
}