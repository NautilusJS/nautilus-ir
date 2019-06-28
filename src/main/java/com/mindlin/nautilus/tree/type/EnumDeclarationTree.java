package com.mindlin.nautilus.tree.type;

import java.util.List;

import com.mindlin.nautilus.tree.DeclarationStatementTree;
import com.mindlin.nautilus.tree.IdentifierTree;
import com.mindlin.nautilus.tree.Modifiers;
import com.mindlin.nautilus.tree.StatementTreeVisitor;
import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.ENUM_DECLARATION })
public interface EnumDeclarationTree extends DeclarationStatementTree {
	@Tree.Property
	Modifiers getModifiers();
	
	@Override
	@Tree.Child
	@Ordering.After("modifiers")
	@NonNull IdentifierTree getName();
	
	/**
	 * @return List members in order 
	 */
	@Tree.Children
	@Ordering.After("name")
	@NonNull List<? extends EnumMemberTree> getMembers();

	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.ENUM_DECLARATION;
	}

	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitEnumDeclaration(this, data);
	}
}
