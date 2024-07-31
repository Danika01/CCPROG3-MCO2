import java.util.Scanner;
public class DriverConsole {

	public static void main(String[] args) {
		
		ReservationSys rs = new ReservationSys();
		Scanner input = new Scanner(System.in);
		int i, choice, index;
		boolean exit = true;
		
		do 
		{
			rs.renderMenu();
			choice = input.nextInt();
			input.nextLine(); // catches the newline
			
			if(choice == 1) // 1) Add hotel
			{
				System.out.println("Enter a unique hotel name: ");
				String name = input.nextLine();
				rs.addHotel(name);
			}
			else if(choice == 2) // 2) View Hotel
			{
				if(rs.getHotelList().size() > 0) {
					System.out.println("Choose a hotel to view: ");
					for(i = 0; i < rs.getHotelList().size(); i++)
					{
						System.out.println((i + 1) + ") " + rs.getHotelList().get(i).getName());
					}
					index = input.nextInt();
					
					System.out.println("\n1) Show high-level information \n2) Show low-level information");
					int decision = input.nextInt();
					rs.viewHotel(decision, index - 1, input); // subtract 1 to account for the index
				}
				else {
					System.out.println("No available hotels");
				}
			}
			else if(choice == 3) // 3) Manage Hotel
			{
				if(rs.getHotelList().size() > 0) {
					System.out.println("Choose a hotel to manage: ");
					for(i = 0; i < rs.getHotelList().size(); i++)
					{
						System.out.println((i + 1) + ") " + rs.getHotelList().get(i).getName());
					}
					index = input.nextInt();
					rs.manageHotel(index - 1, input);
				}
				else {
					System.out.println("No available hotels");
				}
			}
			else if(choice == 4) // 4) Simulate Booking
			{
				rs.simBooking(input);
			}
			else if(choice == 5) // 4) Exit
			{
				System.out.println("Are you sure you want to exit? [1] Yes | [2] No");
				choice = input.nextInt();
				
				if(choice == 1)
				{
					exit = false;
				}
			}
			else // Invalid input
			{
				System.out.println("Warning! Invalid option. Try again.");
			}
			
		} while(exit == true); // exit
		System.out.println("Thank You!");
		
		input.close();
	}

}
