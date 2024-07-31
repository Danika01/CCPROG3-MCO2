import java.util.ArrayList;

/**
 * Represents a hotel 
 * <p>
 * This class handles the creation, deletion, and management of rooms and reservations.
 * </p>
 *
 * @author Miguel Borromeo and Danika Dy
 */
public class Hotel {
	private String name;
	private ArrayList<Room> roomList = new ArrayList<Room>();
	private ArrayList<Reservation> resList = new ArrayList<Reservation>();
	private PriceModifier priceMod = new PriceModifier();
	private int totalRooms = 1;
	private double estEarning;

	/**
		 * Constructor creates a hotel
		 *
		 * @param name provides the created hotel's name
		 */
	public Hotel(String name) 
	{
		this.name = name;
		roomList.add(new StandardRoom(101));
		roomList.get(roomList.size() - 1).setType(" Standard");
		this.estEarning = roomList.get(0).getBasePrice();
	}
	
	/**
	 * Allows the user to change the hotel's price during specific days
	 * 
	 * @param day is the date to modify the rate
	 * @param percentageInt is the new rate
	 * @param hotel is the hotel that is being updated
	 */
	public void setMod(int day, int percentageInt, Hotel hotel)
	{
		priceMod.setModification(day, percentageInt);
		priceMod.modifyPrice(hotel.getResList());
	}
	
	/**
	 * Allows the system to obtain the price modifier
	 * 
	 * @return priceMod returns the price modification object
	 */
	public PriceModifier getMod()
	{
		return priceMod;
	}

	/**
		 * Allows the user to change the hotel's name
		 * 
		 * @param name gives the user's input for the hotel name
		 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
		 * Allows the system to adjust the room capacity of the hotel
		 * 
		 * @param totalRooms provides the new capacity
		 * 
		 * @return boolean flags whether the input is valid
		 */
	public boolean setTotalRooms(int totalRooms) 
	{
		if(totalRooms >= 1 || totalRooms <= 50) 
		{
			this.totalRooms = totalRooms;
			return true;
		}
		else 
		{
			System.out.println("Error! A hotel can only have 1-50 rooms.");
			return false;
		}
	}

	/**
		 * Allows the user to add a room
		 * 
		 * @return boolean flags whether the number of rooms is valid
		 */
	public boolean addRoom(int choice) 
	{
		int floor = 0, num = 0;
		int lastRoom = roomList.get(roomList.size() - 1).getRoomNum();

		if(roomList.size() < 10) 
		{
			floor = 100;
		} 
		else if(roomList.size() < 20 && roomList.size() >= 10) 
		{
			floor = 200;
		} 
		else if(roomList.size() < 30 && roomList.size() >= 20) 
		{
			floor = 300;
		} 
		else if(roomList.size() < 40 && roomList.size() >= 30) 
		{
			floor = 400;
		} 
		else if(roomList.size() < 50 && roomList.size() >= 40) 
		{
			floor = 500;
		}

		if(lastRoom % 100 == 10) 
		{
			num = 0;
		}
		else
		{
			num = lastRoom % 100;
		}

		if(roomList.size() < 50) 
		{
			if(choice == 1) // Standard Room
			{
				roomList.add(new StandardRoom(floor + num + 1));
				roomList.get(roomList.size() - 1).setType(" Standard");
				setTotalRooms(totalRooms + 1);
				return true;
			}
			else if(choice == 2) // Deluxe Room
			{
				roomList.add(new DeluxeRoom(floor + num + 1));
				roomList.get(roomList.size() - 1).setType(" Deluxe");
				setTotalRooms(totalRooms + 1);
				return true;
			}
			if(choice == 3) // Executive Room
			{
				roomList.add(new ExecutiveRoom(floor + num + 1));
				roomList.get(roomList.size() - 1).setType(" Executive");
				setTotalRooms(totalRooms + 1);
				return true;
			}			
		}
		else 
		{
			System.out.println("Error! The hotel has reached its room limit.");
			return false;
		}
		return false;
	}

	/**
		 * Allows the user to remove rooms in a hotel
		 * 
		 * @param index provides the index of the chosen room to remove
		 * 
		 * @return boolean flags whether removing a room is successful
		 */
	public boolean removeRoom(int index) 
	{
		if(roomList.size() > 0) 
		{
			roomList.remove(index);
			setTotalRooms(getTotalRooms() - 1);
			return true;
		}
		else 
		{
			System.out.println("Error! Hotels must have at least 1 room.");
			return false;
		}
	}

	/**
		 * Allows the user to add a reservation
		 * 
		 * @param guestName provides the name the reservation is under
		 * @param roomIndex provides the index of the chosen room
		 * @param checkIn provides the check in date
		 * @param checkOut provides the check out date
		 * 
		 * @return boolean flags whether the reservation was successful
		 */
	public boolean addReservation(String guestName, int roomIndex, int checkIn, int checkOut) 
	{
		if(roomList.get(roomIndex).getStatus() == true) 
		{
			resList.add(new Reservation(guestName, roomList.get(roomIndex), checkIn, checkOut));
			return true;
		}
		System.out.println("Error! Failed to make a reservation.");
		return false;

	}

	/**
		 * Allows the user to remove a reservation
		 * 
		 * @param index provides the chosen reservation to remove
		 * 
		 * @return boolean flags whether removing a reservation was successful
		 */
	public boolean removeReservation(int index) 
	{
		if(resList.size() > 0)
		{
			resList.remove(index);
			return true;
		}
		System.out.println("Error! There are no reservations to remove.");
		return false;
	}

	/**
		 * Allows the system to get the hotel name
		 * 
		 * @return name returns the hotel's name
		 */
	public String getName() 
	{
		return name;
	}

	/**
		 * Allows the system to get the hotel's total room capacity
		 * 
		 * @return totalRooms returns the hotel's room capacity
		 */
	public int getTotalRooms() 
	{
		return totalRooms;
	}

	/**
		 * Allows the system to get the hotel's estimated earning based on reservations
		 * 
		 * @return estEarning returns the estimated earning of the hotel
		 */
	public double getEstEarning() 
	{
		int i;
		double sum = 0.0;
		for(i = 0; i < getResList().size(); i++)
		{
			sum += getResList().get(i).getTotalPrice();
		}	
		estEarning = sum;
		return estEarning;
	}

	/**
		 * Allows the system to get the the list of rooms
		 * 
		 * @return roomList returns the hotel's room list
		 */
	public ArrayList<Room> getRoomList() 
	{
		return roomList;
	}

	/**
		 * Allows the system to get the the list of reservations
		 * 
		 * @return resList returns the hotel's reservation list
		 */
	public ArrayList<Reservation> getResList() 
	{
		return resList;
	}
}
