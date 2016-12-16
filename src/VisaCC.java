
public class VisaCC extends CreditCard{
		private String cardNumber;
		
		public boolean isValidCard(String number)
		{
				if (number.length() <= 19 && number.charAt(0) == '4' && (number.length() == 13) || number.length() == 16)
				{
						return true;
				}
				return false;
		}
		
		public VisaCC(String name)
		{
				super(name);
				cardNumber = name;
		}
}
