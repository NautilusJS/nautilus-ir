package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.annotations.Nullable;
import com.mindlin.nautilus.tree.annotations.Ordering;
import com.mindlin.nautilus.tree.type.TypeElementTree;
import com.mindlin.nautilus.tree.type.TypeParameterDeclarationTree;
import com.mindlin.nautilus.tree.type.TypeTree;

//@Tree.ADT
//TODO: ADT
@Tree.NoImpl
public interface SignatureDeclarationTree extends NamedDeclarationTree, JSDocContainer {
	@Override
	@Ordering.First
	@Tree.Child
	@Nullable PropertyName getName();
	
	/**
	 * Get generic parameters.
	 * @return generics
	 */
	@Ordering.After("name")
	@Tree.Children
	List<? extends TypeParameterDeclarationTree> getTypeParameters();

	/**
	 * Get function parameters.
	 * @return parameters
	 */
	@Ordering.After("typeParameters")
	@Tree.Children
	List<? extends ParameterTree> getParameters();
	
	/**
	 * Get <strong>declared</strong> function return type.
	 * <p>
	 * Note: the type provided by this method is <strong>wrong</strong> if this function is:
	 * <ul>
	 * 	<li>
	 * 		A generator (in which case, the *actual* return type is {@code Generator<getReturnType()>}).
	 * 	</li>
	 * 	<li>
	 * 		async (in which case, the *actual* return type is {@code Promise<getReturnType()>}).
	 * 	</li>
	 * </ul>
	 * </p>
	 * @return declared return type
	 */
	@Ordering.After("parameters")
	@Tree.Child
	@Nullable TypeTree getReturnType();
	
	@Tree.Impl({ Tree.Kind.CALL_SIGNATURE })
	public static interface CallSignatureTree extends SignatureDeclarationTree, TypeElementTree {
		@Override
		default Kind getKind() {
			return Tree.Kind.CALL_SIGNATURE;
		}
		
		@Override
		default <R, D> R accept(TypeElementVisitor<R, D> visitor, D data) {
			return visitor.visitCallSignature(this, data);
		}
	}
	
	@Tree.Impl({ Tree.Kind.CONSTRUCT_SIGNATURE })
	public static interface ConstructSignatureTree extends SignatureDeclarationTree, TypeElementTree {
		@Override
		default Kind getKind() {
			return Tree.Kind.CONSTRUCT_SIGNATURE;
		}
		
		@Override
		default <R, D> R accept(TypeElementVisitor<R, D> visitor, D data) {
			return visitor.visitConstructSignature(this, data);
		}
	}
}
