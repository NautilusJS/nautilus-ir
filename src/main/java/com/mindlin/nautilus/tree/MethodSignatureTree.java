package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.type.TypeElementTree;

@Tree.Impl({ Tree.Kind.METHOD_SIGNATURE })
public interface MethodSignatureTree extends SignatureDeclarationTree, PropertyTree, TypeElementTree {
	@Override
	default Kind getKind() {
		return Kind.METHOD_SIGNATURE;
	}
	
	@Override
	default <R, D> R accept(TypeElementVisitor<R, D> visitor, D data) {
		return visitor.visitMethodSignature(this, data);
	}
}
