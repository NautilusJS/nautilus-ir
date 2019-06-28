package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.ExpressionTree.LeftHandSideExpression;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;
import com.mindlin.nautilus.tree.type.TypeTree;

@Tree.Impl({ Tree.Kind.FUNCTION_INVOCATION })
public interface FunctionCallTree extends LeftHandSideExpression, DeclarationTree {
	@Tree.Child
	ExpressionTree getCallee();
	
	@Tree.Children
	@Ordering.After("callee")
	@Nullable List<? extends TypeTree> getTypeArguments();
	
	@Tree.Children
	@Ordering.After("typeArguments")
	List<? extends ArrayLiteralElement> getArguments();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.FUNCTION_INVOCATION;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitFunctionCall(this, data);
	}
}
