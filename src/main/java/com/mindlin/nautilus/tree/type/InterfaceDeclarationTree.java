package com.mindlin.nautilus.tree.type;

import java.util.List;

import com.mindlin.nautilus.tree.DeclarationStatementTree;
import com.mindlin.nautilus.tree.HeritageClauseTree;
import com.mindlin.nautilus.tree.IdentifierTree;
import com.mindlin.nautilus.tree.StatementTreeVisitor;
import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.INTERFACE_DECLARATION })
public interface InterfaceDeclarationTree extends DeclarationStatementTree {
	
	@Override
	@Tree.Child
	IdentifierTree getName();
	
	@Tree.Children
	@Ordering.After("name")
	@Nullable List<TypeParameterDeclarationTree> getTypeParameters();
	
	@Tree.Children
	@Ordering.After("typeParameters")
	@Nullable List<HeritageClauseTree> getHeritage();
	
	@Tree.Children
	@Ordering.After("heritage")
	@NonNull List<TypeElementTree> getDeclaredMembers();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.INTERFACE_DECLARATION;
	}

	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitInterfaceDeclaration(this, data);
	}
}
