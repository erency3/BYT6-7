package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
		
	public void testGetName() {
		assertEquals ("EUR",EUR.getName()); // getname should return the name of the currency
		
	}
	
	@Test
	public void testGetRate() {
		assertEquals(Double.valueOf(1.5), EUR.getRate());
		assertEquals(Double.valueOf(0.15), SEK.getRate());
		assertEquals(Double.valueOf(0.20), DKK.getRate()); //checking getRate method for eur sek and dkk 
		
	}
	//setting a random rate value and checking if it works correctly 
	@Test
	public void testSetRate() {
		EUR.setRate(5.0);
		assertEquals(Double.valueOf(5.0),EUR.getRate());
		
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals(1.5*10,EUR.universalValue(10),0.01d); // adding delta since the both values are double. unversalvalue method returns "double" in my currency class  
	}
	
	//checking the calculations in testvalueinthis curency method in currency class
	@Test
	public void testValueInThisCurrency() {
		assertEquals(10*0.20/0.15,SEK.valueInThisCurrency(10, DKK),0.01d);
	}

}
