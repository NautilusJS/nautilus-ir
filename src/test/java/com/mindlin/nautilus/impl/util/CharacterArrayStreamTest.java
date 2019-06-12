package com.mindlin.nautilus.impl.util;

import com.mindlin.nautilus.impl.util.CharacterArrayStream;
import com.mindlin.nautilus.util.CharacterStream;

class CharacterArrayStreamTest implements CharacterStreamContract {
	
	@Override
	public CharacterStream wrap(String text) {
		return new CharacterArrayStream(text);
	}
	
}
