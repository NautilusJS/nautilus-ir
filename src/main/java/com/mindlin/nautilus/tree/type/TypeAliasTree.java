package com.mindlin.nautilus.tree.type;

import java.util.List;

import com.mindlin.nautilus.tree.DeclarationStatementTree;
import com.mindlin.nautilus.tree.IdentifierTree;
import com.mindlin.nautilus.tree.StatementTreeVisitor;
import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * Statement declaring a TypeScript type alias. Written in form
 * {@code type ALIASNAME = TYPE}, where ALIASNAME is an identifier
 * representing the name of the alias, and TYPE is a type statement.
 * 
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.TYPE_ALIAS })
public interface TypeAliasTree extends DeclarationStatementTree {
	@Override
	@Tree.Child
	IdentifierTree getName();
	
	/**
	 * @return Generic parameters
	 */
	@Tree.Child
	@Ordering.After("name")
	@Nullable List<TypeParameterDeclarationTree> getTypeParameters();
	
	@Tree.Child
	@Ordering.After("typeParameters")
	TypeTree getValue();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.TYPE_ALIAS;
	}
	
	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitTypeAlias(this, data);
	}
}
