package com.mindlin.nautilus;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({ "com.mindlin.nautilus.fs", "com.mindlin.nautilus.impl.util" })
public class AllTests {
	
}
