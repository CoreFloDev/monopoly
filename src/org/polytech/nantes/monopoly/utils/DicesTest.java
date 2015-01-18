package org.polytech.nantes.monopoly.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DicesTest {
	private Dices dices;
	
	private void assertLesserOrEquals(int expected, int value) {
		if(value > expected)
			fail("Not lesser or equals than the value expected");
	}
	
	@Before
	public void setUp() throws Exception {
		dices = new Dices();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDouble() {
		dices.throwDices();
		if(dices.getTotal() == 12)
			assertTrue(dices.isDouble());
	}
	
	@Test
	public void testNumbers() {
		dices.throwDices();
		assertLesserOrEquals(12, dices.getTotal());
	}

}
