package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.io.File;

public class ViewHotelUI extends JFrame {
	private JPanel contentPane;
	private JButton highLvlBtn = new JButton("View High-Level");
	private JButton lowLvlBtn = new JButton("View Low-Level");
	private Font pxlBit22, pxlBit13;

	/**
	 * Create the frame.
	 */
	public ViewHotelUI() {
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
		setBounds(100, 100, 353, 136);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(87, 96, 123));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//High Level Info Button
		highLvlBtn.setFont(pxlBit13);
		highLvlBtn.setBounds(10, 11, 317, 32);
		contentPane.add(highLvlBtn);
		
		//Low Level Info Button
		lowLvlBtn.setFont(pxlBit13);
		lowLvlBtn.setBounds(10, 54, 317, 32);
		contentPane.add(lowLvlBtn);
		
		setVisible(true);
	}
	
	public JButton getHighLvl() {
		return highLvlBtn;
	}
	
	public void highListener(ActionListener e) {
		highLvlBtn.addActionListener(e);
	}
	
	public JButton getLowLvl() {
		return lowLvlBtn;
	}
	
	public void lowListener(ActionListener e) {
		lowLvlBtn.addActionListener(e);
	}
}
