package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.Tree;

/**
 * Grammar: {@code ( TYPE )}
 * 
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.PARENTHESIZED_TYPE })
public interface ParenthesizedTypeTree extends TypeTree {
	@Tree.Child
	TypeTree getType();
	
	@Override
	default Kind getKind() {
		return Tree.Kind.PARENTHESIZED_TYPE;
	}
	
	@Override
	default <R, D> R accept(TypeTreeVisitor<R, D> visitor, D data) {
		return visitor.visitParenthesizedType(this, data);
	}
}
