package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * While loop node.
 * 
 * @see DoWhileLoopTree for {@code do...while} loops
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.WHILE_LOOP })
public interface WhileLoopTree extends StatementTree {
	@Tree.Child
	ExpressionTree getCondition();
	
	@Ordering.After("condition")
	@Tree.Child
	StatementTree getStatement();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.WHILE_LOOP;
	}
	
	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitWhileLoop(this, data);
	}
}