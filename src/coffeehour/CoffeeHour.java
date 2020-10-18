package coffeehour;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

/**
 * CoffeeHour - Driver class
 * 
 * this is where JFrame is established & the only main method is
 * (essentially the driver)
 * 
 * @author Rebecca Williams
 * @version October 17, 2020
 *
 */
public class CoffeeHour extends JFrame implements ActionListener {
	//global variables
	private static ArrayList<FeUnit> myUnits = new ArrayList<>();
	private static ArrayList<FeUnit> searchedUnits = new ArrayList<>();
	
	//JFrame
	private static JFrame f;
	
	//JLabel
	private static JLabel label1;
	private static JLabel label2;
	private static JLabel idealTea;
	
	//JTextArea
	private static JTextArea results;
	
	//JScrollPlane
	//private static JScrollPane resultPlane;
	
	//DropDown Menu
	private static JComboBox<String> cb;
	
	//JButton
	private static JButton b;
	private static JButton selectUnit;
	
    //JTextField
	private static JTextField textfield;
	private static JTextField responses;
	
	/**
	 * default constructor
	 */
	public CoffeeHour() {
		//default
	}
	
	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException{
		//local variables
		boolean isFourth; //did the user correctly input the previous three answers, and is there the fourth question
		myUnits = readUnitFile("units.txt");
		
		importFirstAnswers();
		
		f = new JFrame("Coffee Hour: A 'Tea Time' assistant");
		f.setSize(500, 600); // ADJUST SIZE OF WINDOW IF NECESSARY
		f.setLocation(100, 150);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setDefaultLookAndFeelDecorated(true); //CHECK THIS OUT FURTHER
		f.setLayout(new BorderLayout());
		
		label1 = new JLabel("Enter the name of your chosen character below.");
		label1.setBounds(50, 20, 200, 30);
		//              (x, y, width, height)
		
		textfield = new JTextField(30);
		textfield.setBounds(50, 70, 200, 30);
		//              (x, y, width, height)
		textfield.setToolTipText("Enter either two letters or more of the full name. Not CAPS sensitive");
		
		label2 = new JLabel("You searched: ");
		label2.setBounds(50, 120, 200, 30);
		
		CoffeeHour tea = new CoffeeHour();
		b = new JButton("Search");
		b.setBounds(260, 70, 100, 30);
		b.setToolTipText("Click to Search");
		b.addActionListener(tea);
		
		selectUnit = new JButton("Select");
		selectUnit.setBounds(170, 170, 100, 30);
		selectUnit.setToolTipText("Once you have selected your unit, click to proceed with guide.");
		selectUnit.addActionListener(tea);
		selectUnit.setEnabled(false);
		selectUnit.setVisible(false);
		
		cb = new JComboBox<String>();
		cb.setEnabled(false);
		cb.setVisible(false);
		
		f.add(label1);
		f.add(textfield);
		f.add(b);
		f.add(label2);
		f.add(cb);
		f.add(selectUnit);
		
		f.setLayout(null);
		f.setVisible(true);
	}
	
	/*
	 * if a button is pressed
	 */
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("Search")) {
			label2.setText("You searched: " + textfield.getText());
			searchedUnits = searchUnit(textfield.getText());
			writeOut();
			cb.setVisible(true);
			selectUnit.setVisible(true);
			cb.setEnabled(true);
			cb.setBounds(50, 170, 100, 30);
			cb.setMaximumRowCount(39);
			selectUnit.setEnabled(true);
			//clear textfield
			textfield.setText("");
		}
	}
	
	/*
	 * adds values to combobox
	 */
	public static void writeOut() {
		for (FeUnit unit : searchedUnits) {
			cb.addItem(unit.getName());
		}
	}
	
	/**
	 * searches for a unit based off of a String
	 */
	private static ArrayList<FeUnit> searchUnit(String query) {
		ArrayList<FeUnit> searched = new ArrayList<>(); //create a list of all valid FeUnit Objects
		for (FeUnit unit : myUnits) {
			if (unit.getName().toLowerCase().contains((query.toLowerCase()))) {
				searched.add(unit);
			}
		}
		return searched;
	}
	
	/**
	 * 
	 */
	private static ArrayList<FeUnit> readUnitFile(String filename) throws FileNotFoundException {
		ArrayList<FeUnit> list = new ArrayList<>(); //create a list of all valid FeUnit Objects
		String line; //the whole line
		int spltLine; //index of comma
		String name; //line first half, separated by comma
		String house; //line second half
		
		FileReader fr = new FileReader(filename);
        BufferedReader bfr = new BufferedReader(fr);
		try {
			line = bfr.readLine();
			while (line != null) {
				spltLine = line.indexOf(',');
				name = line.substring(0, spltLine);
				house = line.substring(spltLine + 1);
				//create FeUnit Object
				FeUnit placeholder = new FeUnit(name, house);
				list.add(placeholder);
				line = bfr.readLine();
			}
			bfr.close();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static void importFirstAnswers() throws FileNotFoundException {
		String line;
		for (FeUnit unit : myUnits) {
			try {
				//for (FeUnit unit : myUnits) {
					FileReader f = new FileReader("C:\\Users\\Becca\\eclipse-workspace" 
							+ "\\FE3H_CoffeeHour\\firstAnswers\\" 
							+ unit.getName() + "_firstAnswers.txt");
			        BufferedReader br = new BufferedReader(f);
			        line = br.readLine();
			        while (line != null) {
			        	//System.out.println(line);
			        	unit.addFirstAnswers(line);
			        	line = br.readLine();
			        }
			        //System.out.println(unit.getName());
			        br.close();
				//}
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
