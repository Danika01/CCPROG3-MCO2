
public class ExecutiveRoom extends Room{

	public ExecutiveRoom(int roomNum)
	{
		super(roomNum);
		
		double base = 1299.0 + 1299.0 * 0.35;

		setBasePrice(base);
	}
}
