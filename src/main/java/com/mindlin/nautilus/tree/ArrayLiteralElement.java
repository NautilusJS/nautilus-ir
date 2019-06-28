package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;

@Tree.NoImpl
public interface ArrayLiteralElement extends Tree {
	<R, D> R accept(@NonNull ArrayLiteralElementVisitor<R, D> visitor, D data);
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return this.accept((ArrayLiteralElementVisitor<R, D>) visitor, data);
	}
}
