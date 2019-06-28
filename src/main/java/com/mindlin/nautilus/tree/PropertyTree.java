package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * A property on an object literal or something.
 * 
 * @author mailmindlin
 */
@Tree.NoImpl
public interface PropertyTree extends NamedDeclarationTree {
	@Tree.Property
	Modifiers getModifiers();
	
	@Override
	@Ordering(after="modifiers")
	@Tree.Child
	PropertyName getName();
}
