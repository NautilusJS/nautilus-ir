package com.mindlin.nautilus.tree.type;

import com.mindlin.nautilus.tree.BooleanLiteralTree;
import com.mindlin.nautilus.tree.NullLiteralTree;
import com.mindlin.nautilus.tree.NumericLiteralTree;
import com.mindlin.nautilus.tree.StringLiteralTree;
import com.mindlin.nautilus.tree.ThisExpressionTree;
import com.mindlin.nautilus.tree.TreeVisitor;
import com.mindlin.nautilus.tree.annotations.NonNullByDefault;

/**
 * Visitor for type trees
 * 
 * @author mailmindlin
 *
 * @param <R>
 *            Return type
 * @param <D>
 *            Data (context) type
 * @see TreeVisitor
 */
@NonNullByDefault
public interface TypeTreeVisitor<R, D> {
	R visitArrayType(ArrayTypeTree node, D d);
	
	R visitBooleanLiteral(BooleanLiteralTree node, D d);
	
	R visitConditionalType(ConditionalTypeTree node, D d);
	
	R visitConstructorType(ConstructorTypeTree node, D d);
	
	R visitFunctionType(FunctionTypeTree node, D d);
	
	R visitInferType(InferTypeTree node, D d);
	
	R visitInterfaceType(ObjectTypeTree node, D d);
	
	R visitIntersectionType(CompositeTypeTree node, D d);
	
	R visitMappedType(MappedTypeTree node, D d);
	
	R visitMemberType(MemberTypeTree node, D d);
	
	R visitNullLiteral(NullLiteralTree node, D d);
	
	R visitNumericLiteral(NumericLiteralTree node, D d);
	
	R visitOptionalType(OptionalTypeTree node, D d);
	
	R visitParenthesizedType(ParenthesizedTypeTree node, D d);
	
	R visitRestType(RestTypeTree node, D d);
	
	R visitSpecialType(SpecialTypeTree node, D d);
	
	R visitStringLiteral(StringLiteralTree node, D d);
	
	R visitThis(ThisExpressionTree node, D d);
	
	R visitTupleType(TupleTypeTree node, D d);
	
	R visitTypePredicate(TypePredicateTree node, D d);
	
	R visitTypeQuery(TypeQueryTree node, D d);
	
	R visitTypeReference(TypeReferenceTree node, D d);
	
	R visitUnaryType(UnaryTypeTree node, D d);
	
	R visitUnionType(CompositeTypeTree node, D d);
}
