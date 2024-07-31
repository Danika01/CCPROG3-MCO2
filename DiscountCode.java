/**
 * Represents the algorithm to apply discount codes
 * <p>
 * This class checks whether the discount code given is correct
 * </p>
 *
 * @author Danika Dy
 */
public class DiscountCode {
	private String[] discountCodes = new String[3];
	
	/**
	 * Constructor creates a DiscountCode
	 */
	public DiscountCode()
	{
		this.discountCodes[0] = "I_WORK_HERE";
		this.discountCodes[1] = "STAY4_GET1";
		this.discountCodes[2] = "PAYDAY";
	}
	
	/**
	 * Allows the system to check the code provided by the user
	 *
	 * @param reservation provides the reservation the code is being applied to
	 * @param code provides the user's entered code
	 * 
	 * @return boolean returns true or false to indicate success or failure of applying discount codes
	 */
	public boolean getDiscount(String code, Reservation reservation)
	{
		double newPrice = 0;
		
		if(discountCodes[0].equals(code)) // I WORK HERE = 10% off total
		{
			newPrice = reservation.getTotalPrice() - reservation.getTotalPrice() * 0.1;
			reservation.setTotalPrice(newPrice);
			reservation.setDisVoucher("I_WORK_HERE");
			reservation.setDisVoucher("I_WORK_HERE");
			return true;
		}
		else if(discountCodes[1].equals(code)) // STAY4 GET1 = 1 day free
		{
			if(reservation.getCheckOut() - reservation.getCheckIn() >= 4)
			{
				newPrice = reservation.getTotalPrice() - reservation.getRoom().getBasePrice();
				reservation.setTotalPrice(newPrice);
				reservation.setDisVoucher("STAY4_GET1");
				return true;
			}
			else 
			{
				System.out.println("Reservation duration doesn't meet the requirement.");
				return false;
			}
		}
		else if(discountCodes[2].equals(code)) // PAYDAY = 7% off specific days
		{
			int in = reservation.getCheckIn();
			int out = reservation.getCheckOut();
			
			if(in <= 15 && out > 15) // covers days 15 but NOT as checkout
			{
				newPrice = reservation.getTotalPrice() - reservation.getRoom().getBasePrice() * 0.07;
				reservation.setTotalPrice(newPrice);
				reservation.setDisVoucher("PAYDAY");
				return true;
			}
			else if(in <= 30 && out > 30) // covers days 30 but NOT as checkout
			{
				newPrice = reservation.getTotalPrice() - reservation.getRoom().getBasePrice() * 0.07;
				reservation.setTotalPrice(newPrice);
				reservation.setDisVoucher("PAYDAY");
				return true;
			}
			else
			{
				System.out.println("Reservation doesn't meet the requirement.");
				return false;
			}
		}
		else
		{
			System.out.println("Invalid code.");
			return false;
		}
	}
}
