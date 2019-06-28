package com.mindlin.nautilus.tree;

@Tree.Impl({ Tree.Kind.SET_ACCESSOR_DECLARATION })
public interface SetAccessorDeclarationTree extends MethodDeclarationTree {
	@Override
	default Kind getKind() {
		return Kind.SET_ACCESSOR_DECLARATION;
	}
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return visitor.visitSetAccessorDeclaration(this, data);
	}
	
	@Override
	default <R, D> R accept(ClassElementVisitor<R, D> visitor, D data) {
		return visitor.visitSetAccessorDeclaration(this, data);
	}
	
	@Override
	default <R, D> R accept(ObjectLiteralElementVisitor<R, D> visitor, D data) {
		return visitor.visitSetAccessorDeclaration(this, data);
	}
}
