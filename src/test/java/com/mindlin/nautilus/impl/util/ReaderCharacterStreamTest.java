package com.mindlin.nautilus.impl.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

import com.mindlin.nautilus.impl.util.ReaderCharacterStream;
import com.mindlin.nautilus.util.CharacterStream;

public class ReaderCharacterStreamTest implements CharacterStreamContract {

	@Override
	public CharacterStream wrap(String text) {
		return new ReaderCharacterStream(new StringReader(text));
	}
	
	@Test
	public void testNullCtor() {
		assertThrows(NullPointerException.class, () -> new ReaderCharacterStream(null));
	}
	
}
