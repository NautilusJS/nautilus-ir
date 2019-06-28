package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.ExpressionTree;
import com.mindlin.nautilus.tree.JSDocContainer;
import com.mindlin.nautilus.tree.NamedDeclarationTree;
import com.mindlin.nautilus.tree.PropertyName;
import com.mindlin.nautilus.tree.Tree;
import com.mindlin.nautilus.tree.UnvisitableTree;
import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.ENUM_MEMBER })
public interface EnumMemberTree extends NamedDeclarationTree, JSDocContainer, UnvisitableTree {
	@Override
	@Tree.Child
	@NonNull PropertyName getName();
	/**
	 * (Optional) initializer.
	 * @return initializer
	 */
	@Tree.Child
	@Ordering.After("name")
	@Nullable ExpressionTree getInitializer();
	
	@Override
	default Kind getKind() {
		return Tree.Kind.ENUM_MEMBER;
	}
}
