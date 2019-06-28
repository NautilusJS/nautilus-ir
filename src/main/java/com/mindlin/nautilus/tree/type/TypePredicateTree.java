package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.ExpressionTree;
import com.mindlin.nautilus.tree.SignatureDeclarationTree;
import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.TYPE_PREDICATE })
public interface TypePredicateTree extends TypeTree {
	@Tree.Child
	SignatureDeclarationTree getParent();
	
	//TODO: return = Identifier | this
	@Tree.Child
	@Ordering.After("parent")
	ExpressionTree getParameterName();
	
	@Tree.Child
	@Ordering.After("parameterName")
	TypeTree getType();
	
	@Override
	default Kind getKind() {
		return Kind.TYPE_PREDICATE;
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitTypePredicate(this, data);
	}
}
