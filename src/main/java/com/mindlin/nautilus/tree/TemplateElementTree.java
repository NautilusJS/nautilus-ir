package com.mindlin.nautilus.tree;

import com.mindlin.nautilus.tree.annotations.NonNull;
import com.mindlin.nautilus.tree.annotations.Ordering;

@Tree.Impl({ Tree.Kind.TEMPLATE_ELEMENT })
public interface TemplateElementTree extends UnvisitableTree {
	
	@Ordering.First
	@Tree.Property
	@NonNull String getRaw();
	
	@Ordering.After("raw")
	@Tree.Property
	@NonNull String getCooked();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.TEMPLATE_ELEMENT;
	}
}
