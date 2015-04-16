package project.pos.mvc;

import project.pos.items.Decorator;
import project.pos.items.Flavor;
import project.pos.items.IceCream;

public class POSModel {
 
	protected IceCream saleIceCream = null;

	public IceCream getSaleIceCream() {
		return saleIceCream;
	}

	public void setSaleIceCream(IceCream saleIceCream) {
		this.saleIceCream = saleIceCream;
	}
	
	public IceCream saleDecorator(IceCream decorator){
		if(saleIceCream != null){
			saleIceCream = new Decorator(decorator, saleIceCream);
		}
		
		return saleIceCream;
	}
	
	public IceCream saleFlavor(IceCream flover){
		saleIceCream = new Flavor(flover.getName(), flover.getPrice());
		
		return saleIceCream;
	}
	
	
}
