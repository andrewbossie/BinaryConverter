/**
 * @Title: Binary Converter
 * @Author: Andrew Bossie
 * @Version: 1.0
 * 
 * In this program, the user will be asked to enter a binary string that will...
 * then be converted into its decimal representation.
 * 
 * I have also implemented the reverse of this conversion...
 * When the user inputs a decimal and presses convert the proper binary will be displayed. 
 * 
 * As a little bit of better usability, the key <enter> can also be used in place of the....
 * convert button. 
 * 
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

public class BinaryConverter extends JFrame{
	
	/** Variable Declarations */
	private static final long serialVersionUID = 1L;
	JLabel decimalText;
	JTextField decimal;
	JLabel binaryText;			
	JTextField binary;
	JButton convertButton;
	JButton clearButton;
	
	/** BinaryConverter Class Constructor
	 * 
	 * 	This constructor defines how the window will be drawn...
	 *  and the elements contained within it.
	 *  
	 *  Here we are using Java's swing GUI manager along with GridBagLayout as JFrame's layout.
	 *  
	 */
	public BinaryConverter(){ 
		
		this.setSize(450, 150); // default window size, uneditable
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);	// Window Parameters
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Binary/Decimal Converter"); 			// Window Title

		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();			// JPanel Parameters, GridBagLayout
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(layout);
		
		
		/* Add Components */
		
		binaryText = new JLabel("\t Binary String");		// 'Binary' Label
		c.gridx = 0;
		c.gridy = 0;
		panel.add(binaryText, c);

		binary = new JTextField(10);		// Empty text box that takes in the user's binary string
		c.gridx = 3;
		c.gridy = 0;
		binary.setToolTipText("Please Enter A Binary String"); 	// Prompts user on mouse-over
		binary.requestFocus();
		BinaryListener listen = new BinaryListener();  // Listener that will listen for enter to be pressed. 
		binary.addKeyListener(listen);
		panel.add(binary, c);
		
		decimalText = new JLabel("\t Decimal String");		// 'Decimal' Label
		c.gridx = 0;
		c.gridy = 1;
		panel.add(decimalText, c);
		
		decimal = new JTextField(10);		// Empty text field that will display the user's decimal converted binary string.
		c.gridx = 3;
		c.gridy = 1;
		BinaryListener listen2 = new BinaryListener();  // Listener that will listen for enter to be pressed. 
		decimal.addKeyListener(listen2);
		panel.add(decimal, c);	
		
	
		convertButton = new JButton("Convert");		// Button that, when pressed, will convert user's binary to decimal.
		c.gridx = 0;
		c.gridy = 3;
		ButtonListener bl = new ButtonListener();	// Button listener listens for convert button to be pressed/
		convertButton.addActionListener(bl);
		panel.add(convertButton, c);
		
		clearButton = new JButton("Clear");		// Button that, when pressed, will clear user's all text
		c.gridx = 3;
		c.gridy = 3;
		ButtonListener cl = new ButtonListener();	// Button listener listens for clear button to be pressed/
		clearButton.addActionListener(cl);
		panel.add(clearButton, c);
		
		this.add(panel);		// Add panel to window
		this.setVisible(true); //Draw Window
	}
	
	/** 
	 * 
	 * @param b
	 * @return String r
	 * @throws NumberFormatException
	 * 
	 * This method takes in user's binary input (String) and converts it to decimal string.
	 * 
	 */
	public String parseBinary(String b) throws NumberFormatException{
		
		int result; 
		try{
			result = Integer.parseInt(b, 2);  		// Parses string input into a base-2 integer.
			String r = Integer.toString(result); 	// Converts base-2 integer into string for output.
			return r;
		}
		catch(NumberFormatException e){
			binary.setText("Invalid Format!");   	// See checklist for comments on this line.
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param d
	 * @return String q
	 * 
	 * This method takes in user's decimal input (String) and converts it to a binary string.
	 * 
	 */
	public String parseDecimal(String d) throws NumberFormatException{
		
		try{
			int result = Integer.parseInt(d);			// Parse String input to int
			String q = Integer.toBinaryString(result);	// Parse int to binary
			return q;
		}
		catch(NumberFormatException f){
			decimal.setText("Invalid Format!");
		}
		return null; 
		
	}
	/**
	 * Class Button Listener
	 * 
	 * Private inner class for the ActionListener associated with the convert button.
	 */
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == convertButton){
				if(decimal.getText().equals("")){
					decimal.setText(parseBinary(binary.getText())); // Calls parseBinary with the input from binary text field then sets decimal text area as the result.
				}
				
				else if(binary.getText().equals("")){
					binary.setText(parseDecimal(decimal.getText()));
				}
				
			}
			
			if(e.getSource() == clearButton){
				decimal.setText("");
				binary.setText("");
			}
			
		}
		
	}
	
	/**
	 * 
	 * Class Binary Listener
	 *
	 * Private inner class for the KeyListener associated with the binary text field.
	 * Listens for enter to be pressed, converts binary to decimal.
	 */
	private class BinaryListener implements KeyListener{


		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar() == KeyEvent.VK_ENTER){
				if(decimal.getText().equals("")){
					decimal.setText(parseBinary(binary.getText()));
				}
				
				else if(binary.getText().equals("")){
					binary.setText(parseDecimal(decimal.getText()));
				}
			}
			
		}

		public void keyPressed(KeyEvent e) {}  // Unimplemented Method
		public void keyReleased(KeyEvent e) {} // Unimplemented Method
		
	}
	
	/**
	 * Main method used to run the program.
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
