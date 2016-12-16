import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.math.BigDecimal;
/**
 * GUI design inspired by an image on Google.
 * Credit to stackoverflow for GUI approach. Also credit for the different users in terms of troubleshooting and implementation
 * @author John Mai
 *
 */
public class GUIWindow extends JFrame {

	private BigDecimal grandTotal;
	private CashController controller;
	private JPanel displayReceipt;
	private JPanel centerPanel;
	private JTextField order;
	private ArrayList <Item> itemsOrdered;
	private JTextPane Items;
	private String itemInfo;
	
	public GUIWindow() 
	{
		/**
		 * Initializing IVs 
		 */
		grandTotal = new BigDecimal(0);
		itemInfo = "";
		
		itemsOrdered = new ArrayList<Item>();
		controller = new CashController();
		setSize(1600,1600);
		create();
		
		
		setTitle("McDonald's Menu System");
		setBackground(Color.WHITE);
		
		setVisible(true);

	}
	
	
	public void create() 
	{
		JPanel mainPanel = (JPanel) getContentPane();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getItemButtons(), showReceipt());
		
		splitPane.setDividerLocation(780);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(splitPane, BorderLayout.CENTER);
		
		
	}
	
	public JScrollPane getItemButtons() 
	{
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(0,2));
		
		ArrayList<Item> itemButtons = controller.getItemsList();
		
		for (final Item itemButton: itemButtons) 
		{

			final JButton createButton = new JButton(itemButton.getName());
			createButton.setToolTipText(itemButton.getName());
			
			createButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					update(itemButton);
				}
			});
			pan.add(createButton);
			createButton.setPreferredSize(new Dimension(30,60));
			
		}
		
		JScrollPane scroller = new JScrollPane(pan);
		Border etchedBorder = BorderFactory.createEtchedBorder();
		Border border = BorderFactory.createTitledBorder(etchedBorder, "Items",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Lucida", Font.BOLD, 20) , Color.BLACK);
		pan.setBorder(border);
		return scroller;

		}
	
	public JPanel showReceipt() 
	{
		
		displayReceipt = new JPanel();
		JLabel label = new JLabel("OrderList:");
		displayReceipt.setLayout(new BorderLayout());
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new BorderLayout());
		
		displayReceipt.add(lowerPanel,BorderLayout.SOUTH);
		displayReceipt.add(label, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0,1));
		
		Items = new JTextPane();
		centerPanel.add(Items);
		
		Items.setEditable(false);
		
		//scrolls down automatically if the text exceeds. Similar to that of a cash register
		JScrollPane centerPanelScroller = new JScrollPane(centerPanel);
		displayReceipt.add(centerPanelScroller, BorderLayout.CENTER);
		
		order = new JTextField(20);
		order.setText("Total Cost = $0.00");
		order.setEditable(false);
		
		//sets the buttons on the bottom
		JButton placeOrder = new JButton("Confirm");
		JButton clearOrder = new JButton("Clear");
		placeOrder.setForeground(Color.GREEN);
		clearOrder.setForeground(Color.RED);
		placeOrder.setPreferredSize(new Dimension(30,50));
		clearOrder.setPreferredSize(new Dimension(30,50));
		
		centerPanel.setBackground(Color.LIGHT_GRAY);
		
		
		placeOrder.setFont(new Font ("Times New Roman", Font.BOLD,40));
		clearOrder.setFont(new Font ("Times New Roman", Font.BOLD,40));
		
		clearOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				delete();
				
			}
			
		});
		
		placeOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					if (!order.getText().equals("Total Cost = $0.00")) {
						String cardNum = JOptionPane.showInputDialog(null, "Enter credit card here",null);
						//calls to verify the CreditCardFactory
						if (controller.makeCard(cardNum).isValidCard(cardNum))
						{
							
							JOptionPane.showMessageDialog(getContentPane(), controller.validationMessage(controller.makeCard(cardNum)) + "Order has been sent to kitchen", "Order has been logged", JOptionPane.INFORMATION_MESSAGE);
							delete();
						}
						//otherwise it will throw an alert to the user saying there is something wrong
						else if (!controller.makeCard(cardNum).isValidCard(cardNum))
						{
							JOptionPane.showMessageDialog(null, "Something went wrong during process try again."
									 , " Error", JOptionPane.ERROR_MESSAGE);
						}
						
					}
					else {
						JOptionPane.showMessageDialog(null,"No items ordered", "Place order", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				 catch (Exception g) {
					 JOptionPane.showMessageDialog(null, "Something went wrong during process try again."
							 , " Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		/**
		 * Adding to the panel. Credit to stackoverflow for help
		 */
		lowerPanel.add(order, BorderLayout.NORTH);
		lowerPanel.add(placeOrder, BorderLayout.CENTER);
		lowerPanel.add(clearOrder, BorderLayout.SOUTH);
		lowerPanel.setBackground(Color.LIGHT_GRAY);
		displayReceipt.setBackground(Color.WHITE);
		return displayReceipt;
		
	}
	/**
	 * Clears out the receipt in case of restart
	 */
	private void delete() {
		
		order.setText("Total Cost = $0.00");
		grandTotal = new BigDecimal(0);
		itemsOrdered.clear();
		itemInfo = "";
		Items.setText(null);
		
	}
	/**
	 * Updates the menu based on the commands.
	 * @param itemButton
	 */
	public void update(final Item itemButton) {
		String item = itemButton.getName();
		BigDecimal itemPrice = new BigDecimal(itemButton.getPrice());
		itemPrice = itemPrice.setScale(2,BigDecimal.ROUND_DOWN);
		 itemInfo += "\n" + item + "\n" + itemPrice + "\n";
		 Items.setText(itemInfo);
		 itemsOrdered.add(itemButton);
		
		grandTotal = grandTotal.add(itemPrice) ;
		order.setText("Total = $" + grandTotal);
	}


}