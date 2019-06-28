package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.Tree.Kind;
import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Kind.ADDITION, Kind.ARRAY_ACCESS, Kind.BITWISE_AND, Kind.BITWISE_OR, Kind.BITWISE_XOR, Kind.DIVISION, Kind.EQUAL,
		Kind.EXPONENTIATION, Kind.GREATER_THAN, Kind.GREATER_THAN_EQUAL, Kind.IN, Kind.INSTANCEOF, Kind.LEFT_SHIFT, Kind.LESS_THAN,
		Kind.LESS_THAN_EQUAL, Kind.LOGICAL_AND, Kind.LOGICAL_OR, Kind.MULTIPLICATION, Kind.NOT_EQUAL, Kind.REMAINDER, Kind.STRICT_EQUAL,
		Kind.STRICT_NOT_EQUAL, Kind.SUBTRACTION, Kind.UNSIGNED_RIGHT_SHIFT })
public interface BinaryExpressionTree extends ExpressionTree {
	@Override
	@Tree.Property
	@Ordering(first=true)
	@NonNull Kind getKind();
	
	@Tree.Child(name = "left")
	@Ordering(before="right")
	ExpressionTree getLeftOperand();
	
	@Tree.Child(name = "right")
//	@Ordering(after="left")
	ExpressionTree getRightOperand();
	
	@Override
	default <R, D> R accept(ExpressionTreeVisitor<R, D> visitor, D data) {
		return visitor.visitBinary(this, data);
	}
	
	@Override
	default boolean equivalentTo(Tree other) {
		if (this == other)
			return true;
		if (getKind() != other.getKind() || this.hashCode() != other.hashCode())
			return false;
		
		BinaryExpressionTree b = (BinaryExpressionTree) other;
		return getLeftOperand().equivalentTo(b.getLeftOperand()) && getRightOperand().equivalentTo(b.getRightOperand());
	}
}
