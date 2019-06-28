package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;
import com.mindlin.nautilus.tree.type.TypeTree;

/**
 * Represents a parameter declaration (this is used when defining a function,
 * not when calling one). Supports most of ES6/TypeScript functionality,
 * including being a rest parameter, type annotations, default values, and
 * optional qualities.
 * 
 * TODO Does not yet support destructuring.
 * 
 * @author mailmindlin
 */
@Tree.Impl({Tree.Kind.PARAMETER})
public interface ParameterTree extends NamedDeclarationTree, JSDocContainer, UnvisitableTree {
	
	/**
	 * Get the access modifier for this parameter.
	 * <p>
	 * Property modifiers: {@link Modifiers#PUBLIC}, {@link Modifiers#PRIVATE}, {@link Modifiers#PROTECTED}, {@link Modifiers#READONLY}.
	 * 
	 * If any of these are present in a constructor, this parameter acts as a property declaration.
	 * </p>
	 * <p>
	 * Type modifiers: {@link Modifiers#DEFINITE}, {@link Modifiers#OPTIONAL}.
	 * 
	 * If these are present, they modify this parameter's type.
	 * </p>
	 * 
	 * @return this parameter's modifiers
	 */
	@Tree.Property
	@NonNull Modifiers getModifiers();
	
	/**
	 * Return if this parameter is a rest parameter.
	 * 
	 * @return If this parameter is a rest parameter.
	 */
	@Tree.Property
	@Ordering(after="modifiers")
	boolean isRest();
	
	@Override
	@Tree.Child
	@Ordering(after="rest")
	@NonNull PatternTree getName();
	
	/**
	 * Get declared type.
	 * <br/>
	 * Note that if this is an optional parameter, the actual type is implicitly
	 * this type or with {@code undefined}.
	 * 
	 * @return declared type (or null if not declared)
	 */
	@Tree.Child
	@Ordering(after="name")
	@Nullable TypeTree getType();
	
	/**
	 * Get declared initializer.
	 * 
	 * @return Initializer, or {@code null} if not present
	 */
	@Tree.Child
	@Ordering.Last
	@Nullable ExpressionTree getInitializer();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.PARAMETER;
	}
}