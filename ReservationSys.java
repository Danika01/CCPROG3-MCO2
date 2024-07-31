import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a reservation system
 * <p>
 * This class handles the management of the entire system
 * </p>
 *
 * @author Miguel Borromeo and Danika Dy
 */
public class ReservationSys {
	private ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
	private double newBasePrice = 1299.0;
	private DiscountCode discount = new DiscountCode();

	/**
		 * Allows the user to add a hotel to the system
		 * 
		 * @param name provides the name of the hotel to be created
		 * 
		 * @return boolean flags whether the creation is successful
		 */
	public boolean addHotel(String name)
	{
		int i;

		for(i = 0; i < hotelList.size(); i++)
		{
			if(name.equals(hotelList.get(i).getName())) // has same name
			{
				System.out.println("Error! Hotel name already exists.");
				return false;
			}
		}
		hotelList.add(new Hotel(name));
		return true;
	}

	/**
		 * Allows the program to print the main menu
		 */
	public void renderMenu() 
	{		
		System.out.println("+-+-+-+");
		System.out.println("|H|R|S|");
		System.out.println("+-+-+-+");
		System.out.println("[1] Add Hotel\n[2] View Hotel\n[3] Manage Hotel\n"
							+ "[4] Simulate Booking\n[5] Exit");
	}

	/**
		 * Allows the user to view a hotel's information
		 * 
		 * @param decision flags what level of info the program will show
		 * @param index provides the index of the hotel to be viewed
		 * @param input passes a scanner for user input
		 */
	public void viewHotel(int decision, int index, Scanner input)
	{
		ArrayList<Room> availableRooms = new ArrayList<Room>();
		ArrayList<Room> unavailableRooms = new ArrayList<Room>();
		int i = 0, tracker = 0, j = 0;
		boolean[] dateAvailable;

		System.out.println("");

		if(decision == 1) // high-level info
		{
			System.out.println("Hotel: " + hotelList.get(index).getName());		
			System.out.println("Total rooms: " + hotelList.get(index).getTotalRooms());	
			System.out.println("Estimated earning: " + hotelList.get(index).getEstEarning());	
		}
		else // low-level info
		{
			System.out.println("Select check in date (eg. 1): ");
			int userInput1 = input.nextInt();
			System.out.println("Select check out date (eg. 5): ");
			int userInput2 = input.nextInt();
			
			if(userInput2 <= userInput1) // error-handling
			{
				System.out.println("Invalid input.\n");			
			}
			else
			{
				for(i = 0; i < hotelList.get(index).getRoomList().size(); i++) 
				{
								Room room = hotelList.get(index).getRoomList().get(i);
								dateAvailable = room.getDatesAvailable();
								boolean isAvailable = true;

								// check if the room is available for the entire duration
								for(j = userInput1 - 1; j <= userInput2 - 1; j++) // prints booked dates
								{
										if(!dateAvailable[j])
										{
											isAvailable = false;
											break;
										}
								}

								if(isAvailable)
								{
									availableRooms.add(room);
								} 
								else if(!isAvailable && (j != 0) && (j != 1) && !dateAvailable[j - 1] && dateAvailable[j - 2])
								{
									availableRooms.add(room);
								}
								else if(!isAvailable && dateAvailable[j + 1])
								{
									availableRooms.add(room);
								}							
								else
								{
									unavailableRooms.add(room);
								}
						}

				System.out.println("Available rooms:");
				for(i = 0; i < availableRooms.size(); i++) // prints available rooms
				{
					System.out.println((i + 1) + ") " + availableRooms.get(i).getRoomNum()
										+ availableRooms.get(i).getType());
				}
				System.out.println("Unavailable rooms:");
				for(i = 0; i < unavailableRooms.size(); i++) // prints unavailable rooms
				{
					System.out.println((availableRooms.size() + i + 1) + ") " + unavailableRooms.get(i).getRoomNum()
										+ unavailableRooms.get(i).getType());
				}

				// 2) Room info
				System.out.println("\nSelect room number (eg. 101): ");
				int roomNum = input.nextInt();

				for(i = 0; i < hotelList.get(index).getRoomList().size(); i++) 
				{
					if(roomNum == hotelList.get(index).getRoomList().get(i).getRoomNum())
					{
						tracker = i;
					}
				}
				input.nextLine(); // collects the newline

				System.out.println("Room: " + hotelList.get(index).getRoomList().get(tracker).getRoomNum()
									+ hotelList.get(index).getRoomList().get(tracker).getType()); // room name
				System.out.println("Price per night: " + hotelList.get(index).getRoomList().get(tracker).getBasePrice()); // price per night

				// availability throughout the month
				dateAvailable = hotelList.get(index).getRoomList().get(tracker).getDatesAvailable();
				System.out.print("Available dates: ");
				boolean inRange = false;
				for(i = 0; i < 31; i++) // prints available dates
				{
				    if(dateAvailable[i]) 
				    {
				    	if(!inRange && (i != 0) && !dateAvailable[i - 1]) // previous day is false (taken) it's still considered available
				    	{
				    		System.out.print(i);
				    		inRange = true;
				    	}
				    	else if(!inRange) 
				        {
				            System.out.print((i + 1));
				            inRange = true;
				        }			        
				    } 
				    else 
				    {
				        if(inRange)
				        {
				            System.out.print("-" + (i + 1) + " ");
				            inRange = false;
				        }
				    }
				}
				if(inRange)
				{
				    System.out.print("-31 ");
				}

				// 3) Reservation info
				if(hotelList.get(index).getResList().size() > 0) 
				{
					System.out.println("\nSelect a reservation (eg. guestName): ");
					String userInput = input.nextLine();

					for(i = 0; i < hotelList.get(index).getResList().size(); i++)
					{
						if(userInput.equals(hotelList.get(index).getResList().get(i).getGuestName()))
						{
							tracker = i;
						}
					}

					System.out.println("\nGuest name: " + hotelList.get(index).getResList().get(tracker).getGuestName());

					System.out.println("\nRoom Information...\n");
					System.out.println("Room number: " + hotelList.get(index).getResList().get(tracker).getRoom().getRoomNum()
										+ hotelList.get(index).getResList().get(tracker).getRoom().getType());

					System.out.print("Booked date: ");
					dateAvailable = hotelList.get(index).getResList().get(tracker).getRoom().getDatesAvailable();
					inRange = false;
					for(i = hotelList.get(index).getResList().get(tracker).getCheckIn() - 1; 
						i <  hotelList.get(index).getResList().get(tracker).getCheckOut(); i++) // prints available dates
					{
							if(!dateAvailable[i]) 
							{
									if(!inRange) 
									{
											System.out.print(i + 1);
											inRange = true;
									}
							} 
							else 
							{
									if(inRange)
									{
											System.out.print("-" + (i) + " ");
											inRange = false;
											break;
									}
							}
					}
					if(inRange) 
					{
					    System.out.print("-" + hotelList.get(index).getResList().get(tracker).getCheckOut() + "\n");
					}
					
					System.out.println("\nTotal price: " + hotelList.get(index).getResList().get(tracker).getTotalPrice());
					// display breakdown
					
					hotelList.get(index).getMod().displayBreakdown(hotelList.get(index).getResList().get(tracker));
					System.out.println("Discount voucher used: " + hotelList.get(index).getResList().get(tracker).getDisVoucher());
				}
				else 
				{
					System.out.println("\nNo reservations");
				}
			}
		}	
	}

	/**
		 * Allows the user to book a reservation
		 * 
		 * @param in passes a scanner for user input
		 */
	public void simBooking(Scanner in) 
	{
		int checkIn, checkOut, roomIndex, hotelIndex, decision;
		String guestName, discountCode;

		//if-else checks if hotelList is empty
		if(hotelList.size() > 0) 
		{
			System.out.println("Pick a hotel:");
			int i = 1;
			for(Hotel hotel : hotelList) 
			{
				System.out.println("[" + i + "] " + hotel.getName());
				i++;
			}
			hotelIndex = in.nextInt();
			in.nextLine(); // catches the newline
			hotelIndex -= 1;

			System.out.print("Enter name: ");
			guestName = in.nextLine();

			System.out.println("Available rooms:");
			for(i = 0; i < hotelList.get(hotelIndex).getRoomList().size(); i++) 
			{
				if(hotelList.get(hotelIndex).getRoomList().get(i).getStatus() == true) 
				{
					System.out.println("[" + (i + 1) + "] " + hotelList.get(hotelIndex).getRoomList().get(i).getRoomNum()
										+ getHotelList().get(hotelIndex).getRoomList().get(i).getType());
				}
			}
			roomIndex = in.nextInt();
			roomIndex -= 1;

			System.out.print("Enter check in day: ");
			checkIn = in.nextInt();

			System.out.print("Enter check out day: ");
			checkOut = in.nextInt();
			in.nextLine(); //catches newline

			if(checkIn != 31 && checkOut != 1 && checkIn < checkOut) 
			{
				//checks range for valid dates in between checkIn and checkOut
				if(hotelList.get(hotelIndex).getRoomList().get(roomIndex).checkRange(checkIn, checkOut)) 
				{
					//updates the chosen dates' statuses
					for(int k = checkIn - 1; k <= checkOut - 1; k++) 
					{
						hotelList.get(hotelIndex).getRoomList().get(roomIndex).setDateStatus(k, false);
					}
					hotelList.get(hotelIndex).getRoomList().get(roomIndex).updateStatus();
					hotelList.get(hotelIndex).addReservation(guestName, roomIndex, checkIn, checkOut);
					
				}				
				hotelList.get(hotelIndex).getMod().modifyPrice(hotelList.get(hotelIndex).getResList());
				
				System.out.println("Would you like to avail a discount? [1] Yes  |  [2] No");
				decision = in.nextInt();
				in.nextLine(); //catches newline
				
				int listSize = hotelList.get(hotelIndex).getResList().size();
				if(decision == 1)
				{
					System.out.println("Enter discount code: ");
					discountCode = in.nextLine();
					boolean success = discount.getDiscount(discountCode, hotelList.get(hotelIndex).getResList().get(listSize - 1));
					if(success == true)
					{
						System.out.println("Discount was successfully applied!");
					}
					else
					{
						System.out.println("Discount failed.");
					}
				}
				System.out.println("Reservation was successful!");
			}
			else 
			{
				System.out.println("Reservation not made: Invalid dates");
			}
		}
		else 
		{
			System.out.println("No hotels available");
		}
	}

	/**
		 * Allows the user to edit a hotel's attributes and rooms
		 *
		 * @param index provides the index of the hotel
		 * @param input passes a scanner for user input
		 */
	public void manageHotel(int index, Scanner input) // add -1 to the actual parameter to make up for the decision
	{
		int i, k, num;
		boolean[] dateAvailable = new boolean[31];

		// menu
		System.out.println("[1] Change hotel name\n[2] Add room\n[3] Remove room\n[4] Update room base price\n"
							+ "[5] Update date rate\n[6] Remove reservation\n[7] Remove hotel");
		int userInput = input.nextInt();
		input.nextLine(); // collects the newline

		if(userInput == 1) // 1) Change hotel name
		{
			boolean check = false;

			System.out.println("Enter new hotel name: ");
			String hotelName = input.nextLine();

			//checks if hotelName is unique
			for(i = 0; i < hotelList.size(); i++) {
				if(hotelName.equals(hotelList.get(i).getName())) {
					check = true;
					break;
				}
			}

			if(check == false) {
				getHotelList().get(index).setName(hotelName);
			}
			else {
				System.out.println("Error! Another hotel already exists with that name");
			}
		}
		else if(userInput == 2) // 2) Add room
		{
			if(getHotelList().get(index).getTotalRooms() < 50) 
			{
				System.out.print("Enter the kind of room:\n[1] Standard Room\n[2] Deluxe Room\n[3] Exclusive Room");
				int choice = input.nextInt();
				
				if(choice == 1 || choice == 2 || choice == 3) // no error in input
				{
					if(choice == 1) // Standard Room
					{
						System.out.print("Enter a number of rooms to add: ");
						num = input.nextInt();
						if(num + getHotelList().get(index).getTotalRooms() <= 50)
						{
							for(i = 0; i < num; i++) 
							{
								getHotelList().get(index).addRoom(choice);
							}
						}	
						else 
						{
							System.out.println("Error! The hotel has reached its room limit");
						}
					}
					if(choice == 2) // Deluxe Room
					{
						System.out.print("Enter a number of rooms to add: ");
						num = input.nextInt();
						if(num + getHotelList().get(index).getTotalRooms() <= 50)
						{
							for(i = 0; i < num; i++) 
							{
								getHotelList().get(index).addRoom(choice);
							}
						}	
						else 
						{
							System.out.println("Error! The hotel has reached its room limit");
						}
					}
					if(choice == 3) // Executive Room
					{
						System.out.print("Enter a number of rooms to add: ");
						num = input.nextInt();
						if(num + getHotelList().get(index).getTotalRooms() <= 50)
						{
							for(i = 0; i < num; i++) 
							{
								getHotelList().get(index).addRoom(choice);
							}
						}	
						else 
						{
							System.out.println("Error! The hotel has reached its room limit");
						}
					}					
				}
				else
				{
					System.out.println("Error! Invalid input.");
				}		
			}
			else 
			{
				System.out.println("Error! The hotel has reached its room limit");
			}
		}
		else if(userInput == 3) // 3) Remove room
		{
			int count = 1;

			System.out.println("Choose room to remove: ");
			for(i = 0; i < getHotelList().get(index).getRoomList().size(); i++)
			{
				System.out.println("[" + count + "] " + getHotelList().get(index).getRoomList().get(i).getRoomNum()
									+ getHotelList().get(index).getRoomList().get(i).getType());
				count++;
			}
			System.out.println("Enter number: ");
			k = input.nextInt();
			int track = 0;
			do
			{
				
				dateAvailable = getHotelList().get(index).getRoomList().get(k - 1).getDatesAvailable();
				for(i = 0; i < 31; i++)
				{
					if(dateAvailable[i] == false)
							track = 1;
					else
						track = 0;
				}
				System.out.println("Warning! This room still has reservations. Enter another room: ");
				k = input.nextInt();
			}while(track == 1);
			System.out.println("Are you sure you want to remove room " + 
								getHotelList().get(index).getRoomList().get(k - 1).getRoomNum() 
								+ getHotelList().get(index).getRoomList().get(i).getType() + "? [1] Yes | [2] No");
			userInput = input.nextInt();

			if(userInput == 1)
			{
				getHotelList().get(index).removeRoom(k - 1);
			}			
		}
		else if(userInput == 4) // 4) Change room base price
		{
			System.out.println("Enter new amount: ");
			double price = input.nextDouble();
			
			System.out.println("Are you sure you want to update the base price? [1] Yes | [2] No");
			userInput = input.nextInt();

			if(userInput == 1 && price >= 100)
			{
				for(i = 0; i < getHotelList().size(); i++) // updates all rooms' base price
				{
					for(k = 0; k < getHotelList().get(i).getRoomList().size(); k++)
					{
						getHotelList().get(i).getRoomList().get(k).setBasePrice(price);
					}
				}
				this.newBasePrice = price;
			}
			else
			{
				System.out.println("Error! Base price must be 100 or more.");
			}
		}
		else if(userInput == 5) // 5) Update Date Rate
		{
			System.out.println("Choose date (1-31) to update (e.g. 25): ");
			int day = input.nextInt();
			if(day < 1 || day > 31)
			{
				System.out.println("Invalid date.");
			}
			else // proceed
			{
				System.out.println("Enter rate (e.g. 110 for 110%): ");
				int rate = input.nextInt();
				
				if(rate < 1)
				{
					System.out.println("Invalid rate.");
				}
				else // modify
				{
					getHotelList().get(index).setMod(day, rate, getHotelList().get(index));
				}
			}
		}
		else if(userInput == 6) // 6) Remove Reservation
		{
			for(i = 0; i < getHotelList().get(index).getResList().size(); i++)
			{
				System.out.println("[" + (i + 1) + "] " + getHotelList().get(index).getResList().get(i).getGuestName());
			}
			System.out.println("Choose which reservation to remove: ");
			userInput = input.nextInt();
			int choice = userInput;

			System.out.println("Are you sure you want to remove the reservation for " + 
								getHotelList().get(index).getResList().get(userInput - 1).getGuestName() 
								+ "? [1] Yes | [2] No");
			userInput = input.nextInt();

			if(userInput == 1)
			{
				dateAvailable = getHotelList().get(index).getResList().get(userInput - 1).getRoom().getDatesAvailable();

		        // get the reservation and its booking dates]
		        int checkInDate = getHotelList().get(index).getResList().get(userInput - 1).getCheckIn();
		        int checkOutDate = getHotelList().get(index).getResList().get(userInput - 1).getCheckOut();

		        // turn the booked dates back to true
		        for (i = checkInDate - 1; i < checkOutDate; i++) 
		        {
		            dateAvailable[i] = true;
		        }
		        hotelList.get(index).getResList().get(userInput - 1).getRoom().setBasePrice(newBasePrice);
		        hotelList.get(index).getResList().get(userInput - 1).getRoom().updateStatus();
		        getHotelList().get(index).removeReservation(choice - 1);
			}		
		}
		else if(userInput == 7) // Remove Hotel
		{
			System.out.println("Are you sure you want to remove " + 
			getHotelList().get(index).getName() + "? [1] Yes | [2] No");
			userInput = input.nextInt();
			if(userInput == 1)				
			{
				if(getHotelList().size() > 0) 
				{
						hotelList.remove(index);
				}
			}
		}
	}

	/**
		 * Allows the system to obtain the hotel list
		 *
		 * @return hotelList returns the list of hotels
		 */
	public ArrayList<Hotel> getHotelList()
	{
		return hotelList;
	}
}