package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;

@Tree.NoImpl
public interface PropertyName extends DeclarationName {
	<R, D> R accept(@NonNull PropertyNameVisitor<R, D> visitor, D data);

	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return this.accept((PropertyNameVisitor<R, D>) visitor, data);
	}
}
