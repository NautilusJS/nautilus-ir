package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Nullable;

@Tree.NoImpl
public interface GotoTree extends StatementTree {
	@Tree.Child
	@Nullable IdentifierTree getLabel();
}
