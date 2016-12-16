import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controller class. Handles the GUI and communicates
 * @author John
 *
 */
public class CashController {
		
		private ArrayList<String> foodItem;
		private ArrayList<Double> foodPrice;
		private ArrayList<Item> items;
		private boolean checkOut = false;
		private Receipt r;
		private String cardNumber;
		
		public CashController()
		{
				foodItem = new ArrayList<String>();
				foodPrice = new ArrayList<Double>();
				items = new ArrayList<Item>();
				loadMenu();
				r = new Receipt();
				cardNumber = "";
		}
		
		public void loadMenu()
		{
				try {
					Scanner kb = new Scanner(System.in);
					System.out.println("Enter the destination of file you want to read");
					String fileName = kb.next();
					Scanner reader = new Scanner(new File(fileName));
					
					while (reader.hasNext())
					{
							String currentLine = reader.nextLine();
							String[] arr = currentLine.split("\\|");
							foodItem.add(arr[0]);
							foodPrice.add(Double.parseDouble(arr[1]));
							
							
					}
					reader.close();
					
					for (int i = 0 ; i < foodItem.size() ; i++)
					{
							items.add(new Item(foodItem.get(i),foodPrice.get(i)));
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("File not found");
				}
				
		}
		public void setCardNum(String c)
		{
				cardNumber = c;
		}
		
		public String getCardNum()
		{
				return cardNumber;
		}
		
		public CreditCard makeCard(String number)
		{
				//CreditCardFactory Approach
				CreditCardFactory ccf = new CreditCardFactory();
				CreditCard card = ccf.makeCard(number);
				return card;
				
		}
		
		public String validationMessage(CreditCard c)
		{
				String message ="";
				if (c instanceof DiscoverCC)
				{
						message += "Discover approved";
				}
				
				else if (c instanceof AmExCC)
				{
						message += "American Express approved";
				}
				
				else if (c instanceof VisaCC)
				{
						message += "Visa card approved";
				}
				else if (c instanceof MasterCC)
				{
						message += "Master card approved";
				}
				return message;
		}
		public ArrayList<String> getFoodList()
		{
				return foodItem;
		}
		
		public ArrayList<Double> getPriceList()
		{
				return foodPrice;
		}
		
		public ArrayList<Item> getItemsList()
		{
				return items;
		}

		public void makePurchase()
		{
			CreditCardFactory fn = new CreditCardFactory();
			CreditCard cc = fn.makeCard("324213234124241");
			
		}
		public void finalize()
		{
				checkOut = true;
		}
		
		
		public static void main(String[] args)
		{
				GUIWindow w = new GUIWindow();
		}
		
		
		
		
		
}
