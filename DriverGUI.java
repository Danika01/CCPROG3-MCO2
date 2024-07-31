
public class DriverGUI {

	public static void main(String[] args) {
		ResGUI gui = new ResGUI();
		ReservationSys rs = new ReservationSys();
		ResController controller = new ResController(rs, gui);
	}
}
