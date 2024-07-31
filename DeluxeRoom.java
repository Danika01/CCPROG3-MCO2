
public class DeluxeRoom extends Room {
	
	public DeluxeRoom(int roomNum)
	{
		super(roomNum);
		
		double base = 1299.0 + 1299.0 * 0.2;

		setBasePrice(base);
	}
}
