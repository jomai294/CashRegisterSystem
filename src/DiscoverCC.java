
public class DiscoverCC extends CreditCard{
		private String cardNumber;
		
		public String getCardNumber()
		{
				return cardNumber;
		}
		
		public boolean isValidCard(String number)
		{
				String firstFew = number.substring(0,4);
				if (firstFew.equals("6011") && number.length() == 16 && number.length() <= 19)
				{
						return true;
				}
				return false;
		}
		
		public DiscoverCC(String name)
		{
				super(name);
				cardNumber = name;
		}
}
