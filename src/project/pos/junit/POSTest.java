package project.pos.junit;

import junit.framework.TestCase;

import static org.junit.Assert.*;

import org.junit.Test;

import project.pos.items.Decorator;
import project.pos.items.Flavor;
import project.pos.items.IceCream;

public class POSTest extends TestCase{

	@Test
	public void test() {
		float expected;
		
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
		assertEquals(expected, sale.getTotalPrice());
		
		// Case 2 - Get vanilla price
		sale = vanilla;
		System.out.println(sale.getIceCream());
		System.out.println(sale.getTotalPrice());
		expected = 20;
		assertEquals(expected, sale.getTotalPrice());
		
		// Case 3 - Get M&M price
		sale = mm;
		System.out.println(sale.getIceCream());
		System.out.println(sale.getTotalPrice());
		expected = 5;
		assertEquals(expected, sale.getTotalPrice());

		// Case 4 - Get strawberry price
		sale = strawberry;
		System.out.println(sale.getIceCream());
		System.out.println(sale.getTotalPrice());
		expected = 4;
		assertEquals(expected, sale.getTotalPrice());
		
		// Case 5 - Chocolate + M&M
		sale = new Decorator(mm, chocolate);
		System.out.println(sale.getIceCream());
		System.out.println(sale.getTotalPrice());
		expected = 25;
		assertEquals(expected, sale.getTotalPrice());
		
		// Case 6 - Chocolate + M&M + Strawberry
		sale = new Decorator(strawberry, sale);
		System.out.println(sale.getIceCream());
		System.out.println(sale.getTotalPrice());
		expected = 29;
		assertEquals(expected, sale.getTotalPrice());
		
		// Case 7 - Chocolate + M&M + Strawberry + M&M
		sale = new Decorator(mm, sale);
		System.out.println(sale.getIceCream());
		System.out.println(sale.getTotalPrice());
		expected = 33;
		assertEquals(expected, sale.getTotalPrice());		
	}

}
