package coffeehour;

import javax.swing.*;
import java.util.ArrayList;
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
	private static JLabel results;
	
	//JButton
	private static JButton b;
	
    //JTextField
	private static JTextField textfield;
	
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
		
		f = new JFrame("New Frame");
		f.setSize(390, 300); // ADJUST SIZE OF WINDOW IF NECESSARY
		f.setLocation(100, 150);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setDefaultLookAndFeelDecorated(true); //CHECK THIS OUT FURTHER
		
		label1 = new JLabel("Enter the name of your chosen character below.");
		label1.setBounds(50, 50, 200, 30);
		//              (x, y, width, height)
		
		textfield = new JTextField();
		textfield.setBounds(50, 100, 200, 30);
		//              (x, y, width, height)
		textfield.setToolTipText("Enter either a portion or the full name. Not CAPS sensitive");
		
		label2 = new JLabel("Your search query: ");
		label2.setBounds(50, 150, 200, 30);
		
		results = new JLabel("");
		results.setBounds(50, 200, 200, 100);
		
		CoffeeHour tea = new CoffeeHour();
		b = new JButton("Search");
		b.setBounds(250, 100, 100, 30);
		b.setToolTipText("Click to Search");
		b.addActionListener(tea);
		
		f.add(label1);
		f.add(textfield);
		f.add(b);
		f.add(label2);
		
		f.setLayout(null);
		f.setVisible(true);
	}
	
	/*
	 * if a button is pressed
	 */
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("Search")) {
			//set label2 to the entered textfield
			label2.setText("Your search query: " + textfield.getText());
			searchedUnits = searchUnit(textfield.getText());
			results.setText(writeOut(searchedUnits));
			//clear textfield
			textfield.setText("");
		}
	}
	
	/*
	 * essentially an overridden toString
	 */
	public String writeOut(ArrayList<FeUnit> list) {
		String str = "Results: \n";
		for (FeUnit unit : list) {
			str.concat(unit.getName() + "\n");
		}
		return str;
	}
	
	/**
	 * searches for a unit based off of a String
	 */
	private static ArrayList<FeUnit> searchUnit(String query) {
		ArrayList<FeUnit> list = new ArrayList<>(); //create a list of all valid FeUnit Objects
		for (FeUnit unit : myUnits) {
			if (unit.getName().toLowerCase().contains(query.toLowerCase())) {
				list.add(unit);
			}
		}
		return list;
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
				bfr.close();
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static void importFirstAnswers() throws FileNotFoundException {
		String line;
		ArrayList<String> firstAnswers = new ArrayList<>();
		try {
			for (FeUnit unit : myUnits) {
				FileReader fr = new FileReader(unit.getName() + "_firstAnswers");
		        BufferedReader bfr = new BufferedReader(fr);
		        line = bfr.readLine();
		        while (line != null) {
		        	firstAnswers.add(line);
		        }
		        unit.setFirstAnswers(firstAnswers);
		        bfr.close();
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
