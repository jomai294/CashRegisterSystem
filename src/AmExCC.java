
public class AmExCC extends CreditCard{
		
		
		// TODO Auto-generated constructor stub
	

		private String cardNumber;
		
		public boolean isValidCard(String number)
		{
				if(number.charAt(0) == '3' && (number.charAt(1) == '4' || number.charAt(1) == '7')
						&& number.length() == 15 && number.length() < 19)
				{
						return true;
				}
				return false;
		}
		
		public AmExCC(String name)
		{
				super(name);
				cardNumber = name;
		}
}
