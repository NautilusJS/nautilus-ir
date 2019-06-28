package com.mindlin.nautilus.tree;

import java.util.Collection;

import com.mindlin.nautilus.tree.annotations.NonNull;

@Tree.Impl({Tree.Kind.COMPILATION_BUNDLE})
public interface CompilationBundleTree extends Tree {
	@Tree.Children
	Collection<@NonNull CompilationUnitTree> getChildren();
	
	@Override
	default Kind getKind() {
		return Tree.Kind.COMPILATION_BUNDLE;
	}
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return visitor.visitCompilationBundle(this, data);
	}
}
