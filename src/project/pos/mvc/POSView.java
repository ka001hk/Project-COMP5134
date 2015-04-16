package project.pos.mvc;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import project.pos.items.*;

public class POSView extends JFrame {

	public static final long serialVersionUID = 138938126;
	protected JPanel topPanel, leftPanel, rightPanel, endPanel;
	protected GridLayout grid;
	protected ArrayList<IceCream> flavor = new ArrayList();
	protected ArrayList<IceCream> decorator = new ArrayList();
	protected ArrayList<JButton> btnFlavors = new ArrayList();
	protected ArrayList<JButton> btnDecorator = new ArrayList();
	protected JLabel lblTotal = null;
	protected JButton btnCheckOut = null;
	
	private POSController controller;
	private POSModel model;
	
	 
			
	public POSView(POSModel model){
		super("Ice-cream POS (Student ID: 14060491g, Name: Lam Ka Chun, Course ID: COMP5134)");
		this.model = model;
		this.controller = makeController();
		buildUI();
		
	}
	

	private void buildUI(){
		
		topPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		endPanel = new JPanel();
		
		JLabel label1 = new JLabel("14060491g Company ICE-CREAM System    ", JLabel.LEFT);
		this.setLabelLayout(label1);
		//JLabel label2 = new JLabel("Decorator    ", JLabel.LEFT);
		//this.setLabelLayout(label2);
		//JLabel label3 = new JLabel("[New IceCream]    ", JLabel.LEFT);
		//this.setLabelLayout(label3);
		
		JLabel label4 = new JLabel("Total:", JLabel.CENTER);
		this.setLabelLayout(label4);
		
		lblTotal = new JLabel("$0", JLabel.CENTER);
		this.setLabelLayout(lblTotal);
		
		JButton btnAdmin = new JButton("System Administrator");
		setButtonLayout(btnAdmin);

		btnCheckOut = new JButton("Check Out");
		setButtonLayout(btnCheckOut);
		btnCheckOut.setEnabled(false);
		btnAdmin.addActionListener(controller);
		btnCheckOut.addActionListener(controller);
		
		endPanel.add(btnAdmin);
		endPanel.add(btnCheckOut);
		endPanel.add(label4);
		endPanel.add(lblTotal);
		
		topPanel.add(label1);
		//topPanel.add(label2);
		//topPanel.add(label3);
		
		this.createItems();
		Iterator i = flavor.iterator();
		while(i.hasNext()){
			
			IceCream item = (IceCream) i.next();
			//System.out.println(item.getName());
			JButton btn = new JButton(item.getName());
			btnFlavors.add(btn);
			btn.addActionListener(controller);
			leftPanel.add(btn);
		}
		
		i = decorator.iterator();
		while(i.hasNext()){
			IceCream item = (IceCream) i.next();
			JButton btn = new JButton(item.getName());
			btnDecorator.add(btn);
			btn.setEnabled(false);
			btn.addActionListener(controller);
			rightPanel.add(btn);
		}
		
		this.config();
		
		
	}
	
	private void createItems() {
		flavor.add(new Flavor("Chocolate", 20));
		flavor.add(new Flavor("Vanilla", 20));
		decorator.add(new Decorator("M&M", 5));
		decorator.add(new Decorator("Strawberry", 4));
	}

	private void setLabelLayout(JLabel label) {
		label.setFont(new Font("Serif", Font.BOLD, 19));
		label.setForeground(Color.BLACK);
		label.setBackground(Color.green);
	}

	private void setButtonLayout(JButton button) {
		button.setPreferredSize(new Dimension(200, 30));
	}

	private void config() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.add(topPanel, BorderLayout.PAGE_START);
		topPanel.setPreferredSize(new Dimension(500,50));
		leftPanel.setPreferredSize(new Dimension(287,250));
		rightPanel.setPreferredSize(new Dimension(287,250));
		endPanel.setPreferredSize(new Dimension(500,50));
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Flavor");
		title.setTitleJustification(TitledBorder.CENTER);
		leftPanel.setBorder(title);
		
		title = BorderFactory.createTitledBorder("Decorator");
		title.setTitleJustification(TitledBorder.CENTER);
		rightPanel.setBorder(title);
		
		/*
		topPanel.setBackground(Color.GREEN);
		leftPanel.setBackground(Color.yellow);
		rightPanel.setBackground(Color.red);
		endPanel.setBackground(Color.black);
		*/
		this.add(topPanel, BorderLayout.PAGE_START);
		this.add(leftPanel, BorderLayout.LINE_START);
		this.add(rightPanel, BorderLayout.LINE_END);
		this.add(endPanel, BorderLayout.PAGE_END);
		this.setSize(600, 400);
		this.setLocation(100, 100);
		this.setVisible(true);
		
	}

	
	public POSController makeController(){
		return new POSController(this, model);
	}
	
	public void showCheckOutDialog(){

		final JComponent[] inputs = new JComponent[] {
				new JLabel("Ice-Cream: " + model.getSaleIceCream().getIceCream()),
				new JLabel("Total Price: $" + model.getSaleIceCream().getTotalPrice())
		};
		
		JOptionPane.showMessageDialog(this, inputs, "Checkout", JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	public void showNewIceDialog(){
		JTextField name = new JTextField();
		JTextField price = new JTextField();

		//In initialization code:
	    //Create the radio buttons.
	    JRadioButton btnFaver = new JRadioButton("Flaver");
	    btnFaver.setSelected(true);

	    JRadioButton btnDec = new JRadioButton("Decorator");

	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(btnFaver);
	    group.add(btnDec);

		final JComponent[] inputs = new JComponent[] {
				new JLabel("Ice-cream"),
				btnFaver, btnDec,
				new JLabel("Name"),
				name,
				new JLabel("Price (Must Be A Number)"),
				price
		};
		
		JOptionPane.showMessageDialog(this, inputs, "New IceCream", JOptionPane.INFORMATION_MESSAGE);
	
		System.out.println(name.getText());
		if(!name.getText().equals("") && !price.getText().equals("")){
			System.out.println(getSelectedButtonText(group) + " / " + name.getText() + " / " + price.getText());
			controller.createIceCream(getSelectedButtonText(group), name.getText(), price.getText());
			JButton btn = new JButton(name.getText());
			
			
			btn.addActionListener(controller);
			if(getSelectedButtonText(group).equals("Flaver") ){
				btnFlavors.add(btn);
				leftPanel.add(btn);
				if(controller.counter > 0)
					btn.setEnabled(false);
			}
			
			if(getSelectedButtonText(group).equals("Decorator")){
				btnDecorator.add(btn);
				rightPanel.add(btn);
				if(controller.counter == 0)
					btn.setEnabled(false);
			}
			
			this.repaint();
		}else{
			JOptionPane.showMessageDialog(this,
				    "Error found on creating ice-cream!.",
				    "Create Ice-cream Error",
				    JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	public void displayTotalPrice(float price){
		System.out.println("Price = $"+price);
		lblTotal.setText("$" + Float.toString(price));
		this.repaint();
	}
	
	public void enableBtns(Iterator btns, boolean enable){
		while(btns.hasNext()){
			JButton btn = (JButton) btns.next();
			btn.setEnabled(enable);
		}
	}
	
	
	public void repaint(){
		topPanel.repaint();
		topPanel.revalidate();
	}
	
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
