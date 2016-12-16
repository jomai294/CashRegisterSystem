/**
 * Credit given to stack overflow and adityanaganath for GUI skeleton. 
 * This class is implemented so that it can create CreditCard objects using an abstract factory approach
 * @author John
 *
 */
public class CreditCardFactory {
	/**
	 * Creates the card based on conditions.
	 * @param number
	 * @return
	 */
	public CreditCard makeCard(String number)
	{
		String second = ""+number.charAt(1);	
		int convert = Integer.parseInt(second);
		//MasterCard
		if ( (convert <= 5 && convert >= 1 ) && number.charAt(0) == '5' && number.length() == 16 && number.length() <= 19)
		{
				return new MasterCC(number);
		}
		//Visa
		else if (number.length() == 13 || number.length() == 16 && (number.charAt(0)) == '4' && number.length() <= 19)
		{
				return new VisaCC(number);
		}
		//AmEx
		else if ((number.charAt(0)) == '3' && ((number.charAt(1)) == '4' || (int)(number.charAt(1)) == '7') && number.length() == 15 && number.length() <= 19)
		{
				return new AmExCC(number);
		}
		//Discover
		else if (number.subSequence(0, 4).equals("6011") && number.length() == 16 && number.length() <= 19)
		{
				return new DiscoverCC(number);
		}
		else
		{
				return null;
		}
		
	}

}
