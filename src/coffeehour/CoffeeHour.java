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
	private static FeUnit currentUnit;
	
	//JFrame
	private static JFrame f;
	
	//JLabel
	private static JLabel label1;
	private static JLabel label2;
	private static JLabel idealTea;
	private static JLabel firstThree;
	
	//JTextArea
	private static JTextArea results;
	
	//JScrollPlane
	//private static JScrollPane resultPlane;
	
	//DropDown Menu
	private static JComboBox<String> cb;
	
	//JButton
	private static JButton search;
	private static JButton selectUnit;
	private static JButton back;
	private static JButton findAns;
	
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
		label1.setBounds(50, 20, 300, 30);
		//              (x, y, width, height)
		
		idealTea = new JLabel("Ideal tea: _______________ Tea");
		idealTea.setBounds(50, 70, 200, 30);
		idealTea.setVisible(true);
		
		textfield = new JTextField(30);
		textfield.setBounds(50, 70, 200, 30);
		textfield.setToolTipText("Enter either two letters or more of the full name. Not CAPS sensitive");
		
		firstThree = new JLabel("Enter a portion of an option.");
		firstThree.setBounds(50, 120, 200, 30);
		firstThree.setVisible(false);
		
		responses = new JTextField(30);
		responses.setBounds(50, 170, 200, 30);
		responses.setToolTipText("The phrases that will appear below are valid responses. If your option does not appear, try a different choice.");
		responses.setVisible(false);
		responses.setEnabled(false);
		
		label2 = new JLabel("You searched: ");
		label2.setBounds(50, 120, 200, 30);
		label2.setVisible(false);
		
		CoffeeHour tea = new CoffeeHour();
		search = new JButton("Search");
		search.setBounds(260, 70, 100, 30);
		search.setToolTipText("Click to Search");
		search.addActionListener(tea);
		
		selectUnit = new JButton("Select");
		selectUnit.setBounds(170, 170, 100, 30);
		selectUnit.setToolTipText("Once you have selected your unit, click to proceed with guide.");
		selectUnit.addActionListener(tea);
		selectUnit.setEnabled(false);
		selectUnit.setVisible(false);
		
		back = new JButton("Back");
		back.setBounds(50, 20, 100, 30);
		back.addActionListener(tea);
		back.setToolTipText("Click this to return to the character selection page.");
		back.setEnabled(false);
		back.setVisible(false);
		
		findAns = new JButton("Click to Search");
		findAns.setBounds(260, 170, 150, 30);
		findAns.addActionListener(tea);
		findAns.setEnabled(false);
		findAns.setVisible(false);
		
		cb = new JComboBox<String>();
		cb.setEnabled(false);
		cb.setVisible(false);
		
		f.add(label1);
		f.add(textfield);
		f.add(search);
		f.add(label2);
		f.add(cb);
		f.add(selectUnit);
		f.add(idealTea);
		f.add(back);
		f.add(responses);
		f.add(firstThree);
		f.add(findAns);
		
		f.setLayout(null);
		f.setVisible(true);
	}
	
	/*
	 * if a button is pressed
	 */
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("Search")) {
			label2.setVisible(true);
			label2.setText("You searched: " + textfield.getText());
			searchedUnits = searchUnit(textfield.getText());
			cb.setEnabled(true);
			writeOut();
			cb.setVisible(true);
			selectUnit.setVisible(true);
			cb.setBounds(50, 170, 100, 30);
			cb.setMaximumRowCount(39);
			if (cb != null) {
				selectUnit.setEnabled(true);
			} else {
				
			}
			//clear textfield
			textfield.setText("");
		} 
		if (str.equals("Select")) {
			currentUnit = findName((String) cb.getSelectedItem());
			//disable
			label1.setVisible(false);
			label2.setVisible(false);
			textfield.setEnabled(false);
			textfield.setVisible(false);
			cb.setVisible(false);
			cb.setEnabled(false);
			selectUnit.setVisible(false);
			selectUnit.setEnabled(false);
			search.setVisible(false);
			search.setEnabled(false);
			
			//enable
			back.setEnabled(true);
			back.setVisible(true);
			idealTea.setVisible(true); //need to put in actual ideal tea
			responses.setVisible(true);
			responses.setEnabled(true);
			firstThree.setVisible(true);
			findAns.setVisible(true);
			findAns.setEnabled(true);
		} 
		if (str.equals("Back")) {
			//not functioning!!!!!!!!!!!!!!!!
			System.out.println("welp");
			
			back.setEnabled(false);
			back.setVisible(false);
			idealTea.setVisible(false);
			responses.setVisible(false);
			responses.setEnabled(false);
			findAns.setEnabled(false);
			findAns.setVisible(false);
			firstThree.setVisible(false);
			
			label1.setVisible(true);
			label2.setVisible(false);
			textfield.setEnabled(true);
			textfield.setVisible(true);
			search.setVisible(true);
			search.setEnabled(true);
		} 
		if (str.equals("Click to Search")) {
			findAns.setText(currentUnit.getHouse());
		}
	}
	
	/*
	 * adds values to combobox
	 */
	public static void writeOut() {
		cb.removeAllItems();
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
	 * finds FeUnit with name from combobox
	 */
	private static FeUnit findName(String query) {
		for (FeUnit unit : myUnits) {
			if (unit.getName().equals(query)) {
				return unit;
			}
		}
		return myUnits.get(0);
	}
	
	/**
	 * searches firstAnswers for matches
	 */
	private static ArrayList<String> searchFirstAnswers(String query) {
		ArrayList<String> list = new ArrayList<>();
		//for ()
		return list;
	}
	
	/**
	 * creates all FeUnits
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
