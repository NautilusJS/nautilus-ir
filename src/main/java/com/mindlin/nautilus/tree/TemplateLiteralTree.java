package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.ExpressionTree.PrimaryExpressionTree;
import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * Template literal.
 * 
 * @author mailmindlin
 *
 */
@Tree.Impl({ Tree.Kind.TEMPLATE_LITERAL })
public interface TemplateLiteralTree extends PrimaryExpressionTree {
	
	/**
	 * The actual strings in this template literal
	 * @return list of quasis, in order
	 */
	@Tree.Children
	List<TemplateElementTree> getQuasis();
	
	/**
	 * Expressions interleaved between quasis
	 * @return list of expressions, in order
	 */
	@Tree.Children
	@Ordering(after="quasis")
	List<ExpressionTree> getExpressions();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.TEMPLATE_LITERAL;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitTemplateLiteral(this, data);
	}
}
