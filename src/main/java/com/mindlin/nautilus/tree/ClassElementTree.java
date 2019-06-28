package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;

/**
 * Marker interface for valid elements of a {@link ClassTreeBase class declaration/expression}.
 * 
 * @author mailmindlin
 */
@Tree.NoImpl
public interface ClassElementTree extends DeclarationTree {
	<R, D> R accept(@NonNull ClassElementVisitor<R, D> visitor, D data);
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return this.accept((ClassElementVisitor<R, D>) visitor, data);
	}
}
