import java.util.ArrayList;

/**
 * Represents the algorithm to apply modified prices on certain days of the month
 * <p>
 * This class 
 * </p>
 *
 * @author Danika Dy
 */
public class PriceModifier {
	 
	private double[] percentage = new double[31]; // this holds the specified discount. The indices are the days
	private boolean[] hasModification = new boolean[31]; // the calendar indicating if there is a discount in a specific day/index
	
	/**
	 * Constructor creates a price modifier
	 */
	public PriceModifier()
	{
		for(int i = 0; i < 31; i++)
		{
			percentage[i] = 1.0; // 100%
			hasModification[i] = true;
		}
	}
	
	/**
	 * Allows the user to change the hotel's rate during specific days
	 * 
	 * @param day is the date to modify the rate
	 * @param percentageInt is the new rate
	 */
	public void setModification(int day, int percentage)
	{
		double percent = percentage / 100.0; // converts to decimal
		this.percentage[day - 1] = percent; // assigns the percentage
		this.hasModification[day - 1] = true;
	}
	
	/**
	 * Allows the system to change the reservation's price
	 * 
	 * @param resList is the specific reservation to be updated
	 */
	public void modifyPrice(ArrayList<Reservation> resList)
	{		
		for(int p = 0; p < resList.size(); p++)
		{
			double sum = 0;
			double tempBase = resList.get(p).getTotalPrice() / (resList.get(p).getCheckOut() - resList.get(p).getCheckIn());
			
			for(int i = resList.get(p).getCheckIn(); i < resList.get(p).getCheckOut(); i++)
			{
				if(hasModification[i - 1])
				{
					sum += tempBase * this.percentage[i - 1];
					System.out.println("Day " + i + " has a rate of " + this.percentage[i - 1] * 100 + "%");
				}
				else
				{
					sum += tempBase * this.percentage[i - 1];
					
					System.out.println("Day " + i + " has a rate of " + this.percentage[i - 1] * 100 + "%");
				}
			}
			resList.get(p).setTotalPrice(sum);
		}		
	}
	
	/**
	 * Allows the system to display the breakdown of rate per night
	 * 
	 * @param reservation is the specific reservation to be referenced
	 */
	public void displayBreakdown(Reservation reservation)
	{
		int i;
		double rate = 0;
		
		System.out.println("Breakdown...");
		for(i = reservation.getCheckIn(); i < reservation.getCheckOut(); i++)
		{
			System.out.print("Day: " + (i) + "-" + (i + 1) + "-> " ); // print breakdown
			
			if(hasModification[i - 1])
			{
				rate = percentage[i - 1] * 100;
				System.out.println(rate + "%");
			}
			else
			{
				System.out.println(rate * 100 + "%");
			}			
		}		
	}
}
