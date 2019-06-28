package com.mindlin.nautilus.tree.impl;

import com.mindlin.nautilus.fs.SourceRange;
import com.mindlin.nautilus.tree.Tree;

public abstract class AbstractTreeBuilder<T extends Tree> {
	protected SourceRange range;
	protected Tree origin;
	
	
	public abstract T build();
}
