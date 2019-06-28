package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNullByDefault;
import com.mindlin.nautilus.tree.type.IndexSignatureTree;

@NonNullByDefault
public interface ClassElementVisitor<R, D> {
	R visitConstructorDeclaration(ConstructorDeclarationTree node, D context);
	
	R visitGetAccessorDeclaration(GetAccessorDeclarationTree node, D context);
	
	R visitIndexSignature(IndexSignatureTree node, D context);
	
	R visitMethodDeclaration(MethodDeclarationTree node, D context);
	
	R visitPropertyDeclaration(PropertyDeclarationTree node, D context);
	
	R visitSemicolonClassElement(SemicolonClassElementTree node, D context);
	
	R visitSetAccessorDeclaration(SetAccessorDeclarationTree node, D d);
}
