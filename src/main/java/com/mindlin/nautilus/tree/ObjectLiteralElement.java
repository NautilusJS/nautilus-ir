package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;

/**
 * Marker interface for valid elements of an {@link ObjectLiteralTree object literal}.
 * @author mailmindlin
 */
@Tree.NoImpl
public interface ObjectLiteralElement extends DeclarationTree {
	<R, D> R accept(@NonNull ObjectLiteralElementVisitor<R, D> visitor, D data);
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return this.accept((ObjectLiteralElementVisitor<R, D>) visitor, data);
	}
}
