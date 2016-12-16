
public class Item {
		
	private double price;
	private int quantity;
	private String name;
	
	public void setPrice(double p)
	{
			price = p;
	}
	
	public double getPrice()
	{
			return price;
	}
	
	public void setQuanitity(int q)
	{
			quantity = q;
	}
	
	public int getQuantity()
	{
			return quantity;
	}
	
	public void setName(String n)
	{
			name = n;
	}
	
	public String getName()
	{
			return name;
	}
	public Item(String n, int q)
	{
			name = n;
			quantity = q;
	}
	
	public Item(String n)
	{
			name = n;
	}
	
	public Item(String n, double c)
	{
			name = n;
			price = c;
	}
	public double totalCost()
	{
			return price * quantity;
	}
}
