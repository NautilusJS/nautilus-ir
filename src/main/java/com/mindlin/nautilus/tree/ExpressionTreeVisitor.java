package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.ClassTreeBase.ClassExpressionTree;
import com.mindlin.nautilus.tree.annotations.NonNullByDefault;
import com.mindlin.nautilus.tree.type.TypeAssertionTree;

@NonNullByDefault
public interface ExpressionTreeVisitor<R, D> {
	R visitArrayLiteral(ArrayLiteralTree node, D d);
	
	R visitArrowFunction(ArrowFunctionTree node, D d);
	
	R visitAssignment(AssignmentTree node, D d);
	
	R visitAwait(AwaitExpressionTree node, D d);
	
	R visitBinary(BinaryExpressionTree node, D d);
	
	R visitBooleanLiteral(BooleanLiteralTree node, D d);
	
	R visitCast(CastExpressionTree node, D d);
	
	R visitClassExpression(ClassExpressionTree node, D d);
	
	R visitConditionalExpression(ConditionalExpressionTree node, D d);
	
	R visitDeleteExpression(DeleteExpression node, D d);
	
	R visitFunctionCall(FunctionCallTree node, D d);
	
	R visitFunctionExpression(FunctionExpressionTree node, D d);
	
	R visitIdentifier(IdentifierTree node, D d);
	
	R visitNew(NewTree node, D d);
	
	R visitNullLiteral(NullLiteralTree node, D d);
	
	R visitNumericLiteral(NumericLiteralTree node, D d);
	
	R visitObjectLiteral(ObjectLiteralTree node, D d);
	
	R visitParentheses(ParenthesizedTree node, D d);
	
	R visitPostfixUnaryExpression(PostfixUnaryExpressionTree node, D d);
	
	R visitPrefixUnaryExpression(PrefixUnaryExpressionTree node, D d);
	
	R visitPropertyAccess(PropertyAccessTree node, D d);
	
	R visitRegExpLiteral(RegExpLiteralTree node, D d);
	
	R visitSequence(SequenceExpressionTree node, D d);
	
	R visitStringLiteral(StringLiteralTree node, D d);
	
	R visitSuper(SuperExpressionTree node, D d);
	
	R visitTemplateLiteral(TemplateLiteralTree node, D d);
	
	R visitTaggedTemplate(TaggedTemplateLiteralTree node, D d);
	
	R visitThis(ThisExpressionTree node, D d);
	
	R visitTypeAssertion(TypeAssertionTree node, D d);
	
	R visitTypeof(TypeOfExpression node, D d);
	
	R visitYield(YieldTree node, D d);
	
	R visitVoid(VoidExpressionTree node, D d);
}
