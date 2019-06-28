package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.Tree;

@Tree.Impl({ Tree.Kind.INFER_TYPE })
public interface InferTypeTree extends TypeTree {
	@Tree.Child
	TypeParameterDeclarationTree getParameter();
	
	@Override
	default Kind getKind() {
		return Tree.Kind.INFER_TYPE;
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitInferType(this, data);
	}
}
