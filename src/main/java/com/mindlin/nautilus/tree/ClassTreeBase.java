package com.mindlin.nautilus.tree;

import java.util.List;
import java.util.Objects;

import com.mindlin.nautilus.tree.ExpressionTree.PrimaryExpressionTree;
import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;
import com.mindlin.nautilus.tree.type.TypeParameterDeclarationTree;

@Tree.ADT
public interface ClassTreeBase extends NamedDeclarationTree, JSDocContainer {
	/**
	 * Valid modifiers:
	 * <dl>
	 * 	<dt>{@link Modifiers#DECLARE}</dt>
	 * 		<dd>Class is declared in ambient context</dd>
	 * 	<dt>{@link Modifiers#EXPORT}</dt>
	 * 		<dd>Class is exported</dd>
	 * 	<dt>{@link Modifiers#DEFAULT}</dt>
	 * 		<dd>Class is exported as default</dd>
	 * </dl>
	 * 
	 * @return Modifiers present on this class
	 */
	@Tree.Property
	@NonNull Modifiers getModifiers();
	
	/**
	 * @return Class identifier name
	 */
	@Override
	@Tree.Child
	@Ordering(after="modifiers")
	@Nullable IdentifierTree getName();
	
	/**
	 * @return The generic parameters on this class
	 */
	@Tree.Children
	@Ordering(after="name")
	@Nullable List<@NonNull ? extends TypeParameterDeclarationTree> getTypeParameters();
	
	/**
	 * @return Declared extends/implements clauses.
	 */
	@Tree.Children
	@Ordering(after="typeParameters")
	@Nullable List<@NonNull ? extends HeritageClauseTree> getHeritage();
	
	/**
	 * @return The (declared) properties for this class
	 */
	@Tree.Children
	@Ordering(after="heritage")
	@NonNull List<@NonNull ClassElementTree> getProperties();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.CLASS_DECLARATION;
	}
	
	@Override
	default boolean equivalentTo(Tree other) {
		if (this == other)
			return true;
		
		if (other == null || this.getKind() == other.getKind() && !(other instanceof ClassTreeBase) || this.hashCode() != other.hashCode())
			return false;
		
		
		ClassTreeBase o = (ClassTreeBase) other;
		
		return Objects.equals(this.getModifiers(), o.getModifiers())
			&& Tree.equivalentTo(this.getName(), o.getName())
			&& Tree.equivalentTo(this.getTypeParameters(), o.getTypeParameters())
			&& Tree.equivalentTo(this.getHeritage(), o.getHeritage())//TODO: fix for order
			&& Tree.equivalentTo(this.getProperties(), o.getProperties());//TODO fix for order
	}
	
	@Tree.Impl({ Kind.CLASS_EXPRESSION })
	public static interface ClassExpressionTree extends ClassTreeBase, PrimaryExpressionTree {
		@Override
		default Kind getKind() {
			return Tree.Kind.CLASS_EXPRESSION;
		}
		
		@Override
		default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
			return visitor.visitClassExpression(this, data);
		}
	}
	
	@Tree.Impl({ Kind.CLASS_DECLARATION })
	public static interface ClassDeclarationTree extends ClassTreeBase, DeclarationStatementTree {
		/**
		 * May be null in some cases (e.g., {@code export default class ...}).
		 */
		@Override
		@Tree.Child
		@Nullable IdentifierTree getName();
		
		@Override
		default Kind getKind() {
			return Tree.Kind.CLASS_DECLARATION;
		}
		
		@Override
		default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
			return visitor.visitClassDeclaration(this, data);
		}
	}
}
