import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ResController {
	private ReservationSys sysModel;
	private ResGUI sysView;
	private int listSelection = -1;
	private Scanner scanCon = new Scanner(System.in);
	
	public ResController(ReservationSys sysModel, ResGUI sysView) {
		this.sysModel = sysModel;
		this.sysView = sysView;
		
		//Main Menu Buttons
		this.sysView.getMainMenuFrame().addHotelListener(new AddButton());
		this.sysView.getMainMenuFrame().viewHotelListener(new ViewButton());
		this.sysView.getMainMenuFrame().manageHotelListener(new ManageButton());
		this.sysView.getMainMenuFrame().simBookingListener(new SimButton());
		this.sysView.getMainMenuFrame().exitListener(new ExitButton());
		this.sysView.getMainMenuFrame().getList().getSelectionModel().addListSelectionListener(new HotelListListener());
	}
	
	//Main Menu Buttons
	class AddButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sysView.addHotelWindow();
			sysView.getAddHotelFrame().addEnterListener(new ahEnterButton());
		}
	}
	
	class ViewButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(listSelection > -1) {
				sysView.viewHotelWindow();
				sysView.getViewHotelFrame().highListener(new HighLvlButton());
				sysView.getViewHotelFrame().lowListener(new LowLvlButton());
			}
		}
	}
	
	class ManageButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	class SimButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Sim Button Pressed");
		}
	}
	
	class ExitButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sysView.exitWindow();
		}
	}
	
	class HotelListListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			listSelection = sysView.getMainMenuFrame().getList().getSelectedIndex();
		}
	}
	
	//Add Hotel Menu
	class ahEnterButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String hotelName = sysView.getAddHotelFrame().getAddTextField().getText();
			
			if(sysModel.addHotel(hotelName)) {
				sysView.getAddHotelFrame().dispose();
				sysView.getMainMenuFrame().getListModel().addElement(hotelName);
			}
			else {
				sysView.getAddHotelFrame().changeHotelAdvLabel();
			}
		}
		
	}
	
	//View Hotel Menu
	class HighLvlButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sysView.getViewHotelFrame().dispose();
			
			String name = sysModel.getHotelList().get(listSelection).getName();
			String roomNo = String.format("%d", sysModel.getHotelList().get(listSelection).getTotalRooms());
			String estEarn = String.format("%f", sysModel.getHotelList().get(listSelection).getEstEarning());
			
			sysModel.viewHotel(1, listSelection, scanCon);
			sysView.highLvlWindow();
			sysView.getHighLvlFrame().getHotelNameLabel().setText(name);
			sysView.getHighLvlFrame().getTotalRoomLabel().setText(roomNo);
			sysView.getHighLvlFrame().getEstEarningLabel().setText(estEarn);
		}
	}
	
	class LowLvlButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sysView.getViewHotelFrame().dispose();
		}
	}
	
	//Manage Hotel Menu
}
