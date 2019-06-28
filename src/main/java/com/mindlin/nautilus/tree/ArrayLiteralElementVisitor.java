package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNullByDefault;

@NonNullByDefault
public interface ArrayLiteralElementVisitor<R, D> extends ExpressionTreeVisitor<R, D> {
	R visitSpread(SpreadElementTree node, D d);
}
