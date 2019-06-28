package com.mindlin.nautilus.tree;

@Tree.Impl({ Tree.Kind.CONSTRUCTOR_DECLARATION })
public interface ConstructorDeclarationTree extends PropertyTree, FunctionTree, ClassElementTree {
	@Override
	@Tree.Property
	Modifiers getModifiers();
	
	@Override
	default Kind getKind() {
		return Kind.CONSTRUCTOR_DECLARATION;
	}
	
	@Override
	default <R, D> R accept(ClassElementVisitor<R, D> visitor, D data) {
		return visitor.visitConstructorDeclaration(this, data);
	}
}
