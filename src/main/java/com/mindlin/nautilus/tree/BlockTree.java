package com.mindlin.nautilus.tree;

import java.util.List;

import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Optional;
import com.mindlin.nautilus.tree.annotations.Ordering;
import com.mindlin.nautilus.util.annotations.Immutable;

@Tree.Impl({ Tree.Kind.BLOCK })
public interface BlockTree extends StatementTree {
	@Tree.Children
	@Immutable @NonNull List<@NonNull ? extends StatementTree> getStatements();
	
	@Optional("false")
	@Ordering(after="statements")
	@Tree.Property
	boolean isScoped();

	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.BLOCK;
	}
	
	@Override
	default <R, D> R accept(StatementTreeVisitor<R, D> visitor, D data) {
		return visitor.visitBlock(this, data);
	}
	
	@Override
	default boolean equivalentTo(Tree other) {
		if (this == other)
			return true;

		List<@NonNull ? extends StatementTree> statements = this.getStatements();
		if (other.getKind() == Kind.BLOCK && Tree.equivalentTo(this.getStatements(), ((BlockTree)other).getStatements()))
			return true;
		
		if (statements.size() == 1 && statements.get(0).equivalentTo(other))
			return true;
		
		return false;
	}
}