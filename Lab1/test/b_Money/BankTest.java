package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("Nordea",Nordea.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK,SweBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		DanskeBank.openAccount("Eren");
		assertTrue(DanskeBank.accountExists("Eren"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		Nordea.deposit("Bob", new Money(500, SEK));
		assertEquals(500,Nordea.getBalance("Bob"),01d);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.withdraw("Bob", new Money(1500, SEK));
		assertEquals(-1500, SweBank.getBalance("Bob"), 01d);
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(0,Nordea.getBalance("Bob"),01d);
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(3000, SEK));
		SweBank.transfer("Bob", "Ulrika", new Money(500, SEK)); 
		assertEquals(2500, SweBank.getBalance("Bob"), 01d);
		assertEquals(500, SweBank.getBalance("Ulrika"), 01d);
		
		SweBank.transfer("Ulrika", DanskeBank, "Gertrud", new Money(400, SEK));
		assertEquals(100, SweBank.getBalance("Ulrika"), 01d);
		//assertEquals(400,DanskeBank.getBalance("Gertrud"),01d);
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(2000, SEK));
		SweBank.addTimedPayment("Bob", "first", 3, 1, new Money(1000, SEK), SweBank, "Ulrika"); 
		SweBank.tick();
		SweBank.tick();

		assertEquals(1000, SweBank.getBalance("Bob"), 01d);
		assertEquals(1000, SweBank.getBalance("Ulrika"), 01d);
	}
}
