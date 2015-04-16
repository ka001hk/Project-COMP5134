package project.pos.mvc;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.*;

import project.pos.items.*;

public class POSController implements ActionListener {

	private POSModel model;
	private POSView view;
	protected int counter = 0;
	
	public POSController(POSView view, POSModel model){
		this.view = view;
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e){
		String comment = e.getActionCommand();
		Iterator i = null;
		
		System.out.println("Comment: " + comment);
		
		if(comment.equals("System Administrator")){
			view.showNewIceDialog();
		}else if(comment.equals("Check Out")){
			view.showCheckOutDialog();
		}else{
		
			if(!comment.equals("")){
				i = view.flavor.iterator();
				while(i.hasNext()){
					IceCream item = (IceCream) i.next();
					if(item.getName().equals(comment)){
						IceCream saleItem = model.saleFlavor(item);
						System.out.println("Order: " + saleItem.getIceCream());
						view.displayTotalPrice(saleItem.getTotalPrice());
						view.enableBtns(view.btnDecorator.iterator(), true);
					}
				}
				i = view.decorator.iterator();
				while(i.hasNext()){
					IceCream item = (IceCream) i.next();
					if(item.getName().equals(comment)){
						IceCream saleItem = model.saleDecorator(item);
						System.out.println("Order: " + saleItem.getIceCream());
						view.displayTotalPrice(saleItem.getTotalPrice());
					}
				}
				view.enableBtns(view.btnFlavors.iterator(), false);
				view.btnCheckOut.setEnabled(true);
				counter++;
			}
		}
	}
	
	public void createIceCream(String type, String name, String price){
		if(type.equals("Flaver")){
			IceCream flavor = new Flavor(name, Float.valueOf(price));
			view.flavor.add(flavor);
		}else if(type.equals("Decorator")){
			IceCream decorator = new Decorator(name, Float.valueOf(price)); 
			view.decorator.add(decorator);
		}
		
		
	}
}
