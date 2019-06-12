package com.mindlin.nautilus.fs;

/**
 * Mapping for an identifier to a source file
 * @author mailmindlin
 */
public interface SourceMapping {
	String identifierName();
	SourceFile sourceFile();
	int lineNumber();
	int column();
}
