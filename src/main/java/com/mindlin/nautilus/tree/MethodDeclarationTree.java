package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * Object literal/class method declaration
 * 
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.METHOD_DECLARATION })
public interface MethodDeclarationTree extends FunctionTree, PropertyTree, ClassElementTree, ObjectLiteralElement {
	@Override
	@Tree.Property
	@Ordering.First
	@NonNull Modifiers getModifiers();
	
	@Override
	@Tree.Child
	@Ordering.After("modifiers")
	@NonNull PropertyName getName();
	
	@Override
	@Tree.Child
	@Ordering.After("name")
	@Nullable StatementTree getBody();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.METHOD_DECLARATION;
	}
	
	@Override
	default <R, D> R accept(ClassElementVisitor<R, D> visitor, D data) {
		return visitor.visitMethodDeclaration(this, data);
	}
	
	@Override
	default <R, D> R accept(ObjectLiteralElementVisitor<R, D> visitor, D data) {
		return visitor.visitMethodDeclaration(this, data);
	}
	
	@Override
	default <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
		return visitor.visitMethodDeclaration(this, data);
	}
}
