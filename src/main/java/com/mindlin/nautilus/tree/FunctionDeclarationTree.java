package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;

@Tree.Impl({ Tree.Kind.FUNCTION_DECLARATION })
public interface FunctionDeclarationTree extends FunctionTree, DeclarationStatementTree {
	/**
	 * {@inheritDoc}
	 * <br/>
	 * Must not be null.
	 */
	@Override
	@Tree.Child
	@NonNull IdentifierTree getName();
	
	@Override
	default Kind getKind() {
		return Tree.Kind.FUNCTION_DECLARATION;
	}
	
	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitFunctionDeclaration(this, data);
	}
}
