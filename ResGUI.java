import gui.MainMenu;
import gui.ViewHotelUI;
import gui.AddHotelUI;
import gui.HighLevel;

public class ResGUI {
	private MainMenu mainMenuFrame;
	private AddHotelUI addHotelFrame;
	private ViewHotelUI viewHotelFrame;
	private HighLevel highLvlFrame;
	
	public ResGUI() {
		mainMenuFrame = new MainMenu();
	}
	
	public void addHotelWindow() {
		addHotelFrame = new AddHotelUI();
	}
	
	public void viewHotelWindow() {
		viewHotelFrame = new ViewHotelUI();
	}
	
	public void highLvlWindow() {
		highLvlFrame = new HighLevel();
	}
	
	public void exitWindow() {
		System.exit(0);
	}
	
	public MainMenu getMainMenuFrame() {
		return mainMenuFrame;
	}
	
	public AddHotelUI getAddHotelFrame() {
		return addHotelFrame;
	}
	
	public ViewHotelUI getViewHotelFrame() {
		return viewHotelFrame;
	}
	
	public HighLevel getHighLvlFrame() {
		return highLvlFrame;
	}
}
