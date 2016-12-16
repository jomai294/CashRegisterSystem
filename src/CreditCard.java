
public abstract class CreditCard {
		private String cardNumber;
		private String owner;
		
		public String getOwner()
		{
				return owner;
		}
		
		public void setOwner(String o)
		{
				owner = o;
		}
		
		public String getCardNumber()
		{
				return cardNumber;
		}
		
		public abstract boolean isValidCard(String number);
		
		public void setCardNumber(String c)
		{
				cardNumber = c;
		}
		
		public CreditCard(String name)
		{
				cardNumber = name;
		}
		
		public void makePayment()
		{
				if (isValidCard(cardNumber))
				{
						System.out.println("Transaction has been approved");
				}
				else
				{
						System.out.println("There has been an error during processing");
				}
		}
		
		
		
		
		
}
