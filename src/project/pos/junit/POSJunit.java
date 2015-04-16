package project.pos.junit;

import junit.framework.TestCase;
import project.pos.items.*;

public class POSJunit extends TestCase {

	public void test() {
		int expected;
		
		// Defined Chocolate/$20, Vanilla/$20, M&M/$5, Strawberry/$4
		IceCream chocolate = new Flavor("Chocolate", 20);
		IceCream vanilla = new Flavor("Vanilla", 20);
		IceCream mm = new Decorator("M&M", 5);
		IceCream strawberry = new Decorator("Strawberry", 4);

		// Case 1 - Get chocolate price
		IceCream sale = chocolate;
		System.out.println(sale.getIceCream());
		System.out.println(sale.getTotalPrice());
		expected = 20;
		assertEquals(expected, sale.getIceCream());
		
		// Case 2 - Get vanilla price
		sale = vanilla;
		expected = 20;
		assertEquals(expected, sale.getIceCream());
		
		// Case 3 - Get M&M price
		sale = mm;
		expected = 5;
		assertEquals(expected, sale.getIceCream());

		// Case 4 - Get strawberry price
		sale = strawberry;
		expected = 20;
		assertEquals(expected, sale.getIceCream());
		
		/*
		sale = new Decorator(mm, sale);
		System.out.println(sale.getIceCream());
		System.out.println(sale.getTotalPrice());
		sale = new Decorator(strawberry, sale);
		System.out.println(sale.getIceCream());
		System.out.println(sale.getTotalPrice());
		
		
		
		expected=4;
		assertEquals(expected, cal.gcd(12, 4));
		expected=3;
		assertEquals(expected, cal.gcd(51,57));
		expected=17;
		assertEquals(expected, cal.gcd(17,34));
		*/
	}

}
