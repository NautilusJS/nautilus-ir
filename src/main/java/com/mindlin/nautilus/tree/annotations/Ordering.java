package com.mindlin.nautilus.tree.annotations;

public @interface Ordering {
	int value() default -1;
	
	String[] before() default "";
	String[] after() default "";
	boolean first() default false;
	boolean last() default false;
	
	public static @interface First {
	}
	
	public static @interface Before {
		String value();
	}
	
	public static @interface After {
		String value();
	}
	
	public static @interface Last {
	}
}
