package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.comment.CommentNode;
import com.mindlin.nautilus.tree.type.TypeParameterDeclarationTree;
import com.mindlin.nautilus.tree.type.TypeTreeVisitor;

public interface TreeVisitor<R, D> extends StatementTreeVisitor<R, D>, ExpressionTreeVisitor<R, D>, TypeTreeVisitor<R, D>, ArrayLiteralElementVisitor<R, D>, PatternTreeVisitor<R, D>, ClassElementVisitor<R, D>, TypeElementVisitor<R, D>, PropertyNameVisitor<R, D>, ObjectLiteralElementVisitor<R, D> {
	R visitComment(CommentNode node, D d);
	
	R visitCompilationBundle(CompilationBundleTree node, D d);
	
	R visitCompilationUnit(CompilationUnitTree node, D d);
	
	R visitParameter(ParameterTree node, D d);
	
	R visitTypeParameterDeclaration(TypeParameterDeclarationTree node, D d);
	
	R visitSwitchCase(SwitchCaseTree node, D d);
}
