package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.ExpressionTree.PrimaryExpressionTree;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;
import com.mindlin.nautilus.tree.type.TypeTree;

@Tree.Impl({ Tree.Kind.NEW })
public interface NewTree extends PrimaryExpressionTree, DeclarationTree {
	@Tree.Child
	ExpressionTree getCallee();
	
	@Tree.Children
	@Ordering.After("callee")
	@Nullable List<TypeTree> getTypeArguments();
	
	@Tree.Children
	@Ordering.After("typeArguments")
	@Nullable List<ArrayLiteralElement> getArguments();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.NEW;
	}
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitNew(this, data);
	}
}