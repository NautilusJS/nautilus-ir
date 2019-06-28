package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.IMPORT_SPECIFIER })
public interface ImportSpecifierTree extends UnvisitableTree {
	@Tree.Child
	IdentifierTree getImported();
	
	@Tree.Child
	@Ordering(after="imported")
	IdentifierTree getAlias();

	@Tree.Property
	@Ordering(after="alias")
	boolean isDefault();

	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.IMPORT_SPECIFIER;
	}
}
