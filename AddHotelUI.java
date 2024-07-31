package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AddHotelUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton enterBtn = new JButton("enter");
	private JLabel addHotelAdvLabel = new JLabel("Enter a unique name:");
	private Font pxlBit22, pxlBit13;

	/**
	 * Create the frame.
	 */
	public AddHotelUI() {
		try {
			pxlBit22 = Font.createFont(Font.TRUETYPE_FONT, new File("src/pixel-bit-advanced.ttf")).deriveFont(22f);
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			env.registerFont(pxlBit22);
			
			pxlBit13 = Font.createFont(Font.TRUETYPE_FONT, new File("src/pixel-bit-advanced.ttf")).deriveFont(13f);
			env.registerFont(pxlBit13);
		} catch(Exception e) {
			
		}
		
		setTitle("Add Hotel");
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 589, 131);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(87, 96, 123));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Text Field
		textField = new JTextField();
		textField.setBounds(26, 51, 396, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//Enter Button
		enterBtn.setFont(pxlBit13);
		enterBtn.setBounds(446, 56, 117, 23);
		contentPane.add(enterBtn);
		
		//Add Hotel Advise Text
		addHotelAdvLabel.setForeground(new Color(255, 255, 255));
		addHotelAdvLabel.setFont(pxlBit22);
		addHotelAdvLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addHotelAdvLabel.setBounds(26, 11, 396, 29);
		contentPane.add(addHotelAdvLabel);
		
		setVisible(true);
	}
	
	public void changeHotelAdvLabel() {
		addHotelAdvLabel.setForeground(Color.red);
		addHotelAdvLabel.setText("Name is taken!");
	}
	
	public JTextField getAddTextField() {
		return textField;
	}
	
	public JButton getEnterButton() {
		return enterBtn;
	}
	
	public void addEnterListener(ActionListener e) {
		enterBtn.addActionListener(e);
	}
	
	public JLabel getHotelAdvLabel() {
		return addHotelAdvLabel;
	}

}
