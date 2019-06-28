package com.mindlin.nautilus.tree;

@Tree.Impl({ Tree.Kind.DIRECTIVE })
public interface DirectiveTree extends StatementTree {
	
	@Tree.Property
	String getDirective();
	
	@Override
	default Kind getKind() {
		return Tree.Kind.DIRECTIVE;
	}
	
	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitDirective(this, data);
	}
}
