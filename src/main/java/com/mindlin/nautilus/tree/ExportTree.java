package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.EXPORT })
public interface ExportTree extends StatementTree {
	/**
	 * @return Whether is default export
	 */
	@Tree.Property
	@Ordering.Before("expression")
	boolean isDefault();
	
	/**
	 * @return Exported expression
	 */
	@Tree.Child
	ExpressionTree getExpression();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.EXPORT;
	}
	
	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitExport(this, data);
	}
}
