package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.IMPORT })
public interface ImportDeclarationTree extends StatementTree {
	@Tree.Children
	List<ImportSpecifierTree> getSpecifiers();

	@Ordering.After("specifiers")
	@Tree.Child
	StringLiteralTree getSource();

	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.IMPORT;
	}

	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitImport(this, data);
	}
}
