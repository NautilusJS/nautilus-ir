package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNullByDefault;

@NonNullByDefault
public interface PropertyNameVisitor<R, D> {
	R visitComputedPropertyKey(ComputedPropertyKeyTree node, D context);
	
	R visitIdentifier(IdentifierTree node, D context);
	
	R visitNumericLiteral(NumericLiteralTree node, D context);
	
	R visitStringLiteral(StringLiteralTree node, D context);
}
