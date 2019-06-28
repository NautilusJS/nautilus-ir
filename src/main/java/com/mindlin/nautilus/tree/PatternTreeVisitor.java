package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNullByDefault;

@NonNullByDefault
public interface PatternTreeVisitor<R, D> {
	R visitArrayPattern(ArrayPatternTree node, D d);
	
	R visitIdentifier(IdentifierTree node, D d);
	
	R visitPropertyAccess(PropertyAccessTree node, D d);
	
	R visitRest(RestPatternElementTree node, D d);
	
	R visitObjectPattern(ObjectPatternTree node, D d);
}
