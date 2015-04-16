package project.pos.items;
public class Flavor implements IceCream {

	protected String name;
	protected float price;

	public Flavor(String name, float price) {
		this.setName(name);
		this.setPrice(price);
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

	@Override
	public String getIceCream() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public float getTotalPrice() {
		// TODO Auto-generated method stub
		return price;
	}

}
