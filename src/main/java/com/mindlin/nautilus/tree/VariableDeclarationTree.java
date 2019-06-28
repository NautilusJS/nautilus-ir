package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.annotations.Ordering;

/**
 * Node representing a variable declaration statement.
 * <p>
 * Note that multiple variables may be declared in this statement; that's the
 * reason for the separation between this and {@link VariableDeclaratorTree}:
 * all declarators under this declaration share the same var/let/const keyword.
 * </p>
 * 
 * @see VariableDeclaratorTree
 * @author mailmindlin
 */
@Tree.Impl({ Tree.Kind.VARIABLE_DECLARATION })
public interface VariableDeclarationTree extends DeclarationStatementTree, VariableDeclarationOrPatternTree {
	
	@Ordering(first = true, before="declarations")
	@Tree.Property
	VariableDeclarationKind getDeclarationStyle();
	
	@Ordering.After("declarationStyle")
	@Tree.Children
	List<VariableDeclaratorTree> getDeclarations();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.VARIABLE_DECLARATION;
	}

	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitVariableDeclaration(this, data);
	}
	
	public static enum VariableDeclarationKind {
		VAR(false),
		LET(true),
		CONST(true),
		;
		
		private final boolean scoped;
		
		private VariableDeclarationKind(boolean scoped) {
			this.scoped = scoped;
		}
		
		public boolean isScoped() {
			return this.scoped;
		}
	}
}