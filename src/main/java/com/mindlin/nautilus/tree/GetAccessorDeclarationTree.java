package com.mindlin.nautilus.tree;

@Tree.Impl({ Tree.Kind.GET_ACCESSOR_DECLARATION })
public interface GetAccessorDeclarationTree extends MethodDeclarationTree {
	@Override
	default Kind getKind() {
		return Kind.GET_ACCESSOR_DECLARATION;
	}
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return visitor.visitGetAccessorDeclaration(this, data);
	}
	
	@Override
	default <R, D> R accept(ClassElementVisitor<R, D> visitor, D data) {
		return visitor.visitGetAccessorDeclaration(this, data);
	}
	
	@Override
	default <R, D> R accept(ObjectLiteralElementVisitor<R, D> visitor, D data) {
		return visitor.visitGetAccessorDeclaration(this, data);
	}
}
