package com.mindlin.nautilus.tree;

@Tree.Impl({ Tree.Kind.SEMICOLON_CLASS_ELEMENT })
public interface SemicolonClassElementTree extends ClassElementTree {
	
	@Override
	default Kind getKind() {
		return Kind.SEMICOLON_CLASS_ELEMENT;
	}
	
	@Override
	default <R, D> R accept(ClassElementVisitor<R, D> visitor, D data) {
		return visitor.visitSemicolonClassElement(this, data);
	}
}
