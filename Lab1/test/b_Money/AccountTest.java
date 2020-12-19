package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		Money a =new Money(1000,SEK);
		testAccount.addTimedPayment("First",5,10,a,SweBank,"Alice");
		assertTrue(testAccount.timedPaymentExists("First"));
		testAccount.removeTimedPayment("First");
		assertFalse(testAccount.timedPaymentExists("First"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("First", 2, 1, new Money(5000, SEK), SweBank, "Alice"); 
		System.out.println(testAccount.getBalance().getAmount());
		testAccount.tick();
		testAccount.tick();
	
		
		assertEquals((10000000 - 5000), testAccount.getBalance().getAmount(), 0);
	}

	@Test
	public void testAddWithdraw() {
		Account a;
		a = new Account ("Eren",SEK);
		a.deposit(new Money (50000, SEK));
		a.withdraw(new Money (4000,SEK));
		assertEquals(46000,a.getBalance().getAmount(),01d);
	}
	
	@Test
	public void testGetBalance() {
		Account a;
		a = new Account ("Eren",SEK);
		a.deposit(new Money (50000, SEK));
		assertTrue (new Money(50000,SEK).equals(a.getBalance()));
		
	}
}
