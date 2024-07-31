/**
 * Represents a Room 
 * <p>
 * This class handles the management of room details.
 * </p>
 *
 * @author Miguel Borromeo and Danika Dy
 */
public class Room {

	private int roomNum;
	private double basePrice = 1299.0;
	private boolean isAvailable = true;
	private boolean[] datesAvailable = new boolean[31];
	private String type;

	/**
		 * Constructor creates a room
		 *
		 * @param roomNum provides the room's number
		 */
	public Room(int roomNum) 
	{
		this.roomNum = roomNum;

		for(int i = 0; i < 31; i++) 
		{
			datesAvailable[i] = true;
		}
	}

	/**
		 * Allows the user to edit the base price of the rooms
		 * 
		 * @param basePrice provides the user's input to change the base price
		 * 
		 * @return boolean flags whether the user input is valid
		 */
	public boolean setBasePrice(double basePrice) 
	{
		if (basePrice >= 100.0) 
		{
			this.basePrice = basePrice;
			return true;
		}
		else 
		{
			System.out.println("Error! Base price must be 100 or more.");
			return false;
		}
	}

	/**
		 * Allows the system to update the room's availability/status
		 */
	public void updateStatus() 
	{
		for(int i = 0; i < 31; i++) 
		{
			if(datesAvailable[i] == true && datesAvailable[i + 1] == true) 
			{
				isAvailable = true;
				break;
			}
			else 
			{
				isAvailable = false;
			}
		}
	}

	/**
		 * Allows the system to check the availability given the check in and check out dates
		 * 
		 * @param in provides the check in date
		 * @param out provides the chec out date
		 * 
		 * @return boolean flags whether the inputs are valid
		 */
	public boolean checkRange(int in, int out) 
	{
		boolean res = true;

		if(in > 0 && out > 0)
		{
			for(int i = in; i <= out; i++) 
			{
				if(datesAvailable[in - 1] == false) 
				{
					res = false;
					break;
				}
			}
		}

		return res;
	}

	/**
		 * Allows the system to set the status of the chosen dates
		 * 
		 * @param index provides the index of the dates
		 * @param bool gives whether the date chosen is taken or not
		 */
	public void setDateStatus(int index, boolean bool) 
	{
		if(index >= 0 && index < 31)
		{
			datesAvailable[index] = bool; 
		}
		else
		{
			System.out.println("Error! Index out of bound.");
		}		
	}

	/**
		 * Allows the system to obtain the room number
		 * 
		 * @return roomNum returns the room number
		 */
	public int getRoomNum() 
	{
		return roomNum;
	}

	/**
		 * Allows the system to get the base price
		 * 
		 * @return basePrice returns the base price
		 */
	public double getBasePrice() 
	{
		return basePrice;
	}

	/**
		 * Allows the system to obtain the status/availability of the room
		 * 
		 * @return isAvailable returns the boolean status
		 */
	public boolean getStatus() 
	{
		return isAvailable;
	}

	/**
		 * Allows the system to get the array of booleans to check which dates are booked/unavailable
		 * 
		 * @return datesAvailable returns the array of booleans for the room's availability
		 */
	public boolean[] getDatesAvailable() 
	{
		return datesAvailable;
	}
	
	/**
	 * Allows the system to obtain the string type of the room
	 * 
	 * @return type returns the string indicating the type of room
	 */
	public void setType(String type) 
	{
		this.type = type;
	}
	
	/**
	 * Allows the system to obtain the string type of the room
	 * 
	 * @return type returns the string indicating the type of room
	 */
	public String getType() 
	{
		return type;
	}
}