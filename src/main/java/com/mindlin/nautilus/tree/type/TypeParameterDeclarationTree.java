package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.IdentifierTree;
import com.mindlin.nautilus.tree.NamedDeclarationTree;
import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.UnvisitableTree;
import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * Generic type parameter (not argument).
 * 
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.TYPE_PARAMETER_DECLARATION })
public interface TypeParameterDeclarationTree extends NamedDeclarationTree, UnvisitableTree {
	
	/**
	 * Name of generic parameter.
	 * 
	 * @return name (not null)
	 */
	@Override
	@Tree.Child
	@Ordering.First
	@NonNull IdentifierTree getName();
	
	/**
	 * Supertype of generic parameter. For example, if declared as
	 * {@code T extends Foo}, this method would return Foo.
	 * 
	 * @return supertype, else null if not present
	 */
	@Tree.Child
	@Ordering.After("name")
	@Nullable TypeTree getSupertype();
	
	/**
	 * Default value of parameter
	 * 
	 * @return default value, else null if not present
	 */
	@Tree.Child
	@Ordering.After("supertype")
	@Nullable TypeTree getDefault();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.TYPE_PARAMETER_DECLARATION;
	}
}
