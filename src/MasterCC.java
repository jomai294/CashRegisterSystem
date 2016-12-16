
public class MasterCC extends CreditCard{
	
		private String cardNumber;
		
		public boolean isValidCard(String number)
		{
				String second = "" + number.charAt(1);
				int convert = Integer.parseInt(second);
				if (convert == 2)
				{
						return true;
				}
				return false;
		}
		
		public MasterCC(String name)
		{
				super(name);
				cardNumber = name;
		}

}
