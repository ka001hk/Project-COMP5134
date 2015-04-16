package project.pos.items;

public class Decorator implements IceCream {

	protected String name;
	protected float price;
	protected IceCream iceCream;

	public Decorator(String name, float price) {
		this.setName(name);
		this.setPrice(price);
	}

	public Decorator(IceCream decorate, IceCream addItem) {
		this.setName(decorate.getName());
		this.setPrice(decorate.getPrice());
		iceCream = addItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setIceCream(IceCream iceCream) {
		this.iceCream = iceCream;
	}

	@Override
	public String getIceCream() {
		// TODO Auto-generated method stub
		if(iceCream == null)
			return this.getName();
		else
			return iceCream.getIceCream() + " + " + this.getName();
	}

	@Override
	public float getTotalPrice() {
		// TODO Auto-generated method stub
		if(iceCream == null)
			return this.getPrice();
		else
			return iceCream.getTotalPrice() + this.getPrice();
	}

}
