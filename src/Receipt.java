import java.text.DecimalFormat;
import java.util.ArrayList;
public class Receipt {

		private ArrayList<Item> arr;
		
		public Receipt()
		{
				arr = new ArrayList<Item>();
		}
		public ArrayList<Item> getList()
		{
				return arr;
		}
		
		public double computeTotal(ArrayList<Item> list)
		{
				double sum = 0;
				for (int i = 0 ; i < list.size() ; i++)
				{
						Item current = list.get(i);
						double currentPrice = current.getPrice();
						sum += currentPrice;
				}
				return sum;
		}
		
		public void addItem(Item i)
		{
				if (getList().size() == 0)
				{
					
					arr.add(i);
				}
				else
				{
					boolean stop = false;
					for (int element = 0 ; element < getList().size() ; element++)
					{
							if (getList().get(element).getName().equals(i.getName()))
							{
									getList().get(element).setQuanitity(arr.get(element).getQuantity()+1);
									stop = true;
									
							}
							
					}
					if (!stop)
					{
							arr.add(i);
					}
				}
		
		}
		
		public void display()
		{
				DecimalFormat formatter = new DecimalFormat("#0.00");
				for (int i = 0 ; i < arr.size(); i++)
				{
						System.out.println(arr.get(i).getName() + ": " + arr.get(i).getQuantity() + " @ " + 
											" $"+ formatter.format(arr.get(i).getPrice()) + " each.\n\t = " + formatter.format(arr.get(i).totalCost()));
						
						
				}
				//computing total
				double grandTotal = 0;
				for (int j = 0 ; j < arr.size() ; j++)
				{
						grandTotal += arr.get(j).totalCost();
				}
				System.out.println("Your total is: " + formatter.format(grandTotal));
				
		}
		
		public void clearReceipt()
		{
				arr.clear();
		}
		//Main is for testing purposes
//		public static void main(String[] args)
//		{
//				Receipt r = new Receipt();
//				
//				Item cheese = new Item("Cheese",3);
//				cheese.setPrice(3.40);
//				Item cheese2 = new Item("Cheese",3);
//				cheese.setPrice(3.40);
//				Item duck = new Item("duck",3);
//				duck.setPrice(10.75);
//				r.addItem(cheese);
//				r.addItem(cheese2);
//				r.addItem(duck);
//				r.display();
//				r.getList().clear();
//				
//		}
		
		
		
}
