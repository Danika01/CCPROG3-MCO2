/**
 * Represents a reservation
 * <p>
 * This class handles the management of reservation details.
 * </p>
 *
 * @author Miguel Borromeo and Danika Dy
 */
public class Reservation {
	private Room room;
	private String guestName;
	private int checkIn;
	private int checkOut;
	private double totalPrice;
	private double breakdown;
	private String link;
	private String discountVoucher;
	private boolean discounted = false;

	/**
		 * Constructor creates a reservation
		 *
		 * @param guestName provides the name the reservation is under
		 * @param room provides the room chosen for a reservation
		 * @param checkIn provides the starting day of the reservation
		 * @param checkOut provides the ending day of the reservation
		 */
	public Reservation(String guestName, Room room, int checkIn, int checkOut) 
	{		
		this.guestName = guestName;
		this.room = room;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalPrice = room.getBasePrice() * (checkOut - checkIn);
	}
	
	/**
	 * Allows the system to change the total price of the reservation
	 * 
	 * @param newPrice provides the new total price for the reservation
	 */
	public void setTotalPrice(double newPrice)
	{
		this.totalPrice = newPrice;
	}

	/**
		 * Allows the system to get the total price of a reservation
		 * 
		 * @return totalPrice gives the total price of the reservation
		 */
	public double getTotalPrice() 
	{		
		return totalPrice;
	}

	/**
		 * Allows the system to get the breakdown of cost per night
		 * 
		 * @return breakdown gives the breakdown per night
		 */
	public double getBreakdown()
	{
		int count = 0, i;
		boolean[] dateBooked = getRoom().getDatesAvailable();

		for(i = 0; i < 31; i++)
		{
			if(dateBooked[i] == false)
			{
				count++;
			}	
		}
		this.breakdown = totalPrice / count;
		return breakdown;
	} 

	/**
	 * Allows the system to update the reserved room's status
	 * 
	 * @param bool provides the boolean value the room's status will be set to
	 */
	public void updateRoomStatus(boolean bool) {
		for(int i = checkIn - 1; i < checkOut - 1; i++) {
			room.setDateStatus(i, bool);
		}
	}
	
	/**
		 * Allows the system to obtain the name the reservation is under
		 * 
		 * @return guestName gives the name the reservation is under
		 */
	public String getGuestName() 
	{
		return guestName;
	}

	/**
		 * Allows the system to get the total price of a reservation
		 * 
		 * @return room.getRoomNum() returns the room the reservation is made to
		 */
	public int getResRoomNum() 
	{
		return room.getRoomNum();
	}

	/**
		 * Allows the system to get link to the room information
		 * 
		 * @return link returns the link to the room information
		 */
	public String getLink() 
	{
		return link;
	}

	/**
		 * Allows the system to get the room the reservation is made to
		 * 
		 * @return room returns the room the reservation is made to
		 */
	public Room getRoom()
	{
		return room;
	}
	
	/**
	 * Allows the system to get the check in day
	 * 
	 * @return room returns the check in day
	 */
	public int getCheckIn() 
	{
		return checkIn;
	}
	
	/**
	 * Allows the system to get the check out day
	 * 
	 * @return room returns the check out day
	 */
	public int getCheckOut() 
	{
		return checkOut;
	}
	
	/**
	 * Allows the system to record the discount voucher used
	 * 
	 * @param disc
	 */
	public void setDisVoucher(String disc)
	{
		this.discountVoucher = disc;
		this.discounted = true;
	}
	
	/**
	 * Allows the system to obtain the discount voucher used
	 * 
	 * @return discountVoucher
	 */
	public String getDisVoucher()
	{
		if(discounted == false)
		{
			return "N/A";
		}
		else
		{
			return discountVoucher;
		}
	}
}

