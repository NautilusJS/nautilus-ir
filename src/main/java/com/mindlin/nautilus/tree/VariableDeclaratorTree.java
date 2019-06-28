package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;
import com.mindlin.nautilus.tree.type.TypeTree;

@Tree.Impl({ Tree.Kind.VARIABLE_DECLARATOR })
public interface VariableDeclaratorTree extends NamedDeclarationTree, UnvisitableTree {
	
	@Override
	@Tree.Child
	DeclarationName getName();
	
	/**
	 * Get any initializer for this variable. For parameters, this is the
	 * default value. Null means that there was no initializer/default value.
	 * 
	 * @return initializer, else null if not present
	 */
	@Tree.Child
	@Ordering(after="name")
	@Nullable ExpressionTree getInitializer();
	
	/**
	 * Get the type of this variable. Return null for any.
	 * 
	 * @return type (or null)
	 */
	@Tree.Child
	@Ordering(after="initializer")
	@Nullable TypeTree getType();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.VARIABLE_DECLARATOR;
	}
}
