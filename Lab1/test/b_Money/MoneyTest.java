package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}
		//testing getamount it should return the amount
	@Test
	public void testGetAmount() {
		assertEquals((Integer)1000,EUR10.getAmount());
	}
		// testing getcurrency it should return the currency object
	@Test
	public void testGetCurrency() {
		assertEquals(EUR, EUR0.getCurrency());
		assertEquals(SEK, SEK100.getCurrency());
	}
		//testing to string method, testing it as "10.0" instead of "1000", accoring to your instructions
	@Test
	public void testToString() {
		assertEquals("10.0 EUR", EUR10.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals(1500, EUR10.universalValue(),01d);//first value amount * rate
	}

	@Test
	public void testEqualsMoney() {
		assertTrue(SEK200.equals(EUR20));
	}

	@Test
	public void testAdd() {
		assertEquals((20000+1000*1.5/0.15), SEK200.add(EUR10).getAmount(),01d);
	}

	@Test
	public void testSub() {
		assertEquals((10000-1000*1.5/0.15), SEK100.sub(EUR10).getAmount(),01d );
	}

	@Test
	public void testIsZero() {
		assertTrue(SEK0.isZero());
		assertTrue(EUR0.isZero());
		assertFalse(SEK200.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals(Integer.valueOf(-20000),SEK200.negate().getAmount());
	}

	@Test
	public void testCompareTo() {
		assertEquals("comparable",1, SEK100.compareTo(SEK0));
	}
}
