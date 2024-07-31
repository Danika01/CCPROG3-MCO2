package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.awt.FlowLayout;

public class HighLevel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel hotelNameTitle = new JLabel("Hotel Name:");
	private JLabel totalRoomTitle = new JLabel("Total Rooms:");
	private JLabel estEarningTitle = new JLabel("Est. Earning:");
	private JPanel hotelNamePanel = new JPanel();
	private JLabel hotelNameLabel = new JLabel("");
	private JPanel totalRoomPanel = new JPanel();
	private JLabel totalRoomLabel = new JLabel("");
	private JPanel estEarnPanel = new JPanel();
	private JLabel estEarningLabel = new JLabel("");
	private Font pxlBit22, pxlBit13;

	/**
	 * Create the frame.
	 */
	public HighLevel() {
		try {
			pxlBit22 = Font.createFont(Font.TRUETYPE_FONT, new File("src/pixel-bit-advanced.ttf")).deriveFont(22f);
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			env.registerFont(pxlBit22);
			
			pxlBit13 = Font.createFont(Font.TRUETYPE_FONT, new File("src/pixel-bit-advanced.ttf")).deriveFont(13f);
			env.registerFont(pxlBit13);
		} catch(Exception e) {
			
		}
		
		setTitle("View Hotel");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 539, 184);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(87, 96, 123));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Hotel Name Title
		hotelNameTitle.setForeground(new Color(255, 255, 255));
		hotelNameTitle.setFont(pxlBit22);
		hotelNameTitle.setBounds(10, 11, 207, 31);
		contentPane.add(hotelNameTitle);
		
		//Total Room Title
		totalRoomTitle.setForeground(Color.WHITE);
		totalRoomTitle.setFont(pxlBit22);
		totalRoomTitle.setBounds(10, 53, 227, 31);
		contentPane.add(totalRoomTitle);
		
		//Est Earning Title
		estEarningTitle.setForeground(Color.WHITE);
		estEarningTitle.setFont(pxlBit22);
		estEarningTitle.setBounds(10, 95, 239, 31);
		contentPane.add(estEarningTitle);
		
		//Hotel Name Panel
		hotelNamePanel.setBackground(new Color(255, 255, 255));
		hotelNamePanel.setBounds(251, 11, 262, 31);
		contentPane.add(hotelNamePanel);
		hotelNamePanel.setLayout(null);
		
		//Hotel Name Label
		hotelNameLabel.setBounds(10, 0, 242, 31);
		hotelNamePanel.add(hotelNameLabel);
		hotelNameLabel.setBackground(new Color(255, 255, 255));
		hotelNameLabel.setForeground(new Color(0, 0, 0));
		hotelNameLabel.setFont(pxlBit13);
		
		//Total Room Panel
		totalRoomPanel.setLayout(null);
		totalRoomPanel.setBackground(Color.WHITE);
		totalRoomPanel.setBounds(251, 53, 262, 31);
		contentPane.add(totalRoomPanel);
		
		//Total Room Label
		totalRoomLabel.setForeground(Color.BLACK);
		totalRoomLabel.setFont(pxlBit13);
		totalRoomLabel.setBackground(Color.WHITE);
		totalRoomLabel.setBounds(10, 0, 242, 31);
		totalRoomPanel.add(totalRoomLabel);
		
		//Est Earn Panel
		estEarnPanel.setLayout(null);
		estEarnPanel.setBackground(Color.WHITE);
		estEarnPanel.setBounds(251, 95, 262, 31);
		contentPane.add(estEarnPanel);
		
		//Est Earn Label
		estEarningLabel.setForeground(Color.BLACK);
		estEarningLabel.setFont(pxlBit13);
		estEarningLabel.setBackground(Color.WHITE);
		estEarningLabel.setBounds(10, 0, 242, 31);
		estEarnPanel.add(estEarningLabel);
		
		setVisible(true);
	}
	
	public JLabel getHotelNameLabel() {
		return hotelNameLabel;
	}
	
	public JLabel getTotalRoomLabel() {
		return totalRoomLabel;
	}
	
	public JLabel getEstEarningLabel() {
		return estEarningLabel;
	}
}
