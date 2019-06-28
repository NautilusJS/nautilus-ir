package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.ExpressionTree.PrimaryExpressionTree;

/**
 * A tree representing an array literal
 * 
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.ARRAY_LITERAL })
public interface ArrayLiteralTree extends PrimaryExpressionTree {
	/**
	 * Get list of elements in literal, in order.
	 * <p>
	 * Note that this list may contain elements with the value of {@code null}.
	 * This behavior is correct; the elements represent elements with the value
	 * of {@code undefined} (the value, not the identifier). This makes it
	 * easier on the parser in cases (TODO: maybe explain more?), so whatever.
	 * </p>
	 * 
	 * @return elements
	 */
	@Tree.Children
	List<? extends ExpressionTree> getElements();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.ARRAY_LITERAL;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitArrayLiteral(this, data);
	}
}
