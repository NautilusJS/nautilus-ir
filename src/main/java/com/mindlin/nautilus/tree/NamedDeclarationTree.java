package com.mindlin.nautilus.tree;

/**
 * Declarations that have a name.
 * 
 * @author mailmindlin
 */
@Tree.NoImpl
public interface NamedDeclarationTree extends DeclarationTree, DecoratableTree {
	/**
	 * @return Declaration name
	 */
	@Tree.Child
	DeclarationName getName();
}
