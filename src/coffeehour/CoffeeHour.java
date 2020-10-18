package coffeehour;

import javax.swing.*;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
	private static ArrayList<String> arr;
	private static FeUnit currentUnit;
	
	//JFrame
	private static JFrame f;
	
	//ImageIcon
	private static ImageIcon profile;
	private static ImageIcon houseBanner;
	
	//JLabel
	private static JLabel label1;
	private static JLabel label2;
	private static JLabel idealTea;
	private static JLabel firstThree;
	private static JLabel fullNameLabel;
	
	private static JLabel profileLabel;
	private static JLabel bannerLabel;
	
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
	private static JButton fourButton;
	private static JButton giftButton;
	
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
		importTeas();
		importQues();
		
		f = new JFrame("Coffee Hour: A 'Tea Time' assistant");
		f.setSize(650, 600); // ADJUST SIZE OF WINDOW IF NECESSARY
		f.setLocation(100, 150);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setDefaultLookAndFeelDecorated(true); //CHECK THIS OUT FURTHER
		f.setLayout(new BorderLayout());
		
		label1 = new JLabel("Enter the name of your chosen character below.");
		label1.setBounds(50, 20, 300, 30);
		//              (x, y, width, height)
		
		idealTea = new JLabel("Ideal tea: _______________ Tea");
		idealTea.setBounds(50, 70, 500, 30);
		idealTea.setVisible(true);
		
		textfield = new JTextField(30);
		textfield.setBounds(50, 70, 200, 30);
		textfield.setToolTipText("Enter either two letters or more of the full name. Not CAPS sensitive");
		
		firstThree = new JLabel("Enter a portion of an option.");
		firstThree.setBounds(50, 120, 200, 30);
		firstThree.setVisible(false);
		
		responses = new JTextField(30);
		responses.setBounds(50, 160, 200, 30);
		responses.setToolTipText("The phrases that will appear below are valid responses. If your option does not appear, try a different choice.");
		responses.setVisible(false);
		responses.setEnabled(false);
		
		label2 = new JLabel("You searched: ");
		label2.setBounds(50, 120, 400, 30);
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
		
		fullNameLabel = new JLabel("_____");
		fullNameLabel.setBounds(200, 20, 400, 30);
		fullNameLabel.setVisible(false);
		fullNameLabel.setFont(new Font("Serif", Font.BOLD, 20));
		
		back = new JButton("Back");
		back.setBounds(50, 20, 100, 30);
		back.addActionListener(tea);
		back.setToolTipText("Click this to return to the character selection page.");
		back.setEnabled(false);
		back.setVisible(false);
		
		findAns = new JButton("Click to Search");
		findAns.setBounds(260, 160, 150, 30);
		findAns.setToolTipText("Enter any portion of an option. The more precise, the quicker to find the response.");
		findAns.addActionListener(tea);
		findAns.setEnabled(false);
		findAns.setVisible(false);
		
		cb = new JComboBox<String>();
		cb.setEnabled(false);
		cb.setVisible(false);
		
		results = new JTextArea();
		results.setBounds(50, 200, 400, 300);
		results.setVisible(false);
		
		fourButton = new JButton("Fourth Conversation");
		fourButton.setToolTipText("Quick! Select this as soon as you've successfully completed three conversations!");
		fourButton.addActionListener(tea);
		fourButton.setBounds(50, 520, 200, 30);
		fourButton.setEnabled(false);
		fourButton.setVisible(false);
		
		giftButton = new JButton("Gift Options");
		giftButton.addActionListener(tea);
		giftButton.setBounds(270, 520, 150, 30);
		giftButton.setEnabled(false);
		giftButton.setVisible(false);
		
		profileLabel = new JLabel();
		profileLabel.setVisible(false);
		profileLabel.setBounds(470, 100, 120, 120);
		
		bannerLabel = new JLabel();
		bannerLabel.setVisible(false);
		bannerLabel.setBounds(470, 240, 120, 188);
		
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
		f.add(results);
		f.add(fourButton);
		f.add(giftButton);
		f.add(profileLabel);
		f.add(bannerLabel);
		f.add(fullNameLabel);
		
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
			selectUnit.setEnabled(true);
			writeOut();
			cb.setVisible(true);
			selectUnit.setVisible(true);
			cb.setBounds(50, 170, 100, 30);
			cb.setMaximumRowCount(39);
			if (!selectUnit.isEnabled()) {
				label2.setText("You searched: " + textfield.getText() + "      !! No results found !!");
			}
			//clear textfield
			textfield.setText("");
		} 
		if (str.equals("Select")) {
			currentUnit = findName((String) cb.getSelectedItem());
			giftButton.setToolTipText("Click to view" + currentUnit.getName() + "'s best gift options");
			fullNameLabel.setText(currentUnit.getFullName());
			fullNameLabel.setVisible(true);
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
			displayTea();
			responses.setVisible(true);
			responses.setEnabled(true);
			firstThree.setVisible(true);
			findAns.setVisible(true);
			findAns.setEnabled(true);
			fourButton.setEnabled(true);
			fourButton.setVisible(true);
			giftButton.setEnabled(true);
			giftButton.setVisible(true);
			profile = new ImageIcon(currentUnit.getPicPath());
			houseBanner = new ImageIcon(currentUnit.getHousePath());
			profileLabel.setIcon(profile);
			profileLabel.setVisible(true);
			bannerLabel.setIcon(houseBanner);
			bannerLabel.setVisible(true);
		} 
		if (str.equals("Back")) {
			back.setEnabled(false);
			back.setVisible(false);
			idealTea.setVisible(false);
			responses.setVisible(false);
			responses.setEnabled(false);
			findAns.setEnabled(false);
			findAns.setVisible(false);
			firstThree.setVisible(false);
			fourButton.setEnabled(false);
			fourButton.setVisible(false);
			giftButton.setEnabled(false);
			giftButton.setVisible(false);
			profileLabel.setVisible(false);
			bannerLabel.setVisible(false);
			fullNameLabel.setVisible(false);
			
			label1.setVisible(true);
			label2.setVisible(false);
			textfield.setEnabled(true);
			textfield.setVisible(true);
			search.setVisible(true);
			search.setEnabled(true);
			results.setText("");
			results.setBounds(50, 200, 400, 300);
			results.setToolTipText(null);
			results.setVisible(false);
		} 
		if (str.equals("Click to Search")) {
			results.setVisible(true);
			arr = searchFirstAnswers(responses.getText());
			writeArea();
			responses.setText("");
		}
		if (str.equals("Fourth Conversation")) {
			idealTea.setVisible(false);
			responses.setVisible(false);
			responses.setEnabled(false);
			findAns.setEnabled(false);
			findAns.setVisible(false);
			firstThree.setVisible(false);
			fourButton.setEnabled(false);
			fourButton.setVisible(false);
			//giftButton.setBounds(170, 20, 150, 30); //moving the button... may take out
			results.setVisible(true); //keep visible... put in all fourth q answers here as well.. may need to change bounds
			results.setToolTipText("Spoken Question  +  \"Preferred Reaction(s)\"");
			results.setBounds(50, 70, 400, 430);
			arr = currentUnit.getFourthQ();
			writeArea();
		}
		if (str.equals("Gift Options")) {
			idealTea.setVisible(false);
			responses.setVisible(false);
			responses.setEnabled(false);
			findAns.setEnabled(false);
			findAns.setVisible(false);
			firstThree.setVisible(false);
			fourButton.setEnabled(false);
			fourButton.setVisible(false);
			giftButton.setEnabled(false);
			giftButton.setVisible(false);
			results.setText("");
			results.setVisible(false);
		}
		
	}
	/**
	 * TODO
	 * 
	 * 
	 * read in fourth question files
	 * print those files
	 * write out gifts
	 * print gifts
	 * 
	 */
	
	
	/**
	 * displays preferred teas
	 */
	private static void displayTea() {
		String strTea = "";
		for (String teas : currentUnit.getFavTea()) {
			strTea = strTea.concat(teas + ", ");
		}
		strTea = "Ideal teas: " + strTea.substring(0, strTea.length() - 2);
		idealTea.setText(strTea);
	}
	
	/**
	 * adds values to combo box
	 */
	private static void writeOut() {
		cb.removeAllItems();
		int count = 0;
		for (FeUnit unit : searchedUnits) {
			cb.addItem(unit.getName());
			count++;
		}
		
		if (count == 0) {
			selectUnit.setEnabled(false);
		}
	}
	
	/**
	 * adds values to text area
	 */
	private static void writeArea() {
		results.setText("");
		String str1 = "";
		for (String str : arr) {
			str1 = str1.concat(str + "\n");
		}
		
		if (str1.equals("")) {
			str1 = "!! Your search was not found in the list of correct responses !!";
		}
		results.setText(str1);
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
		for (String str : currentUnit.getFirstAnswers()) {
			if (str.toLowerCase().contains(query.toLowerCase())) {
				list.add(str);
			}
		}
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
		String fullName;
		FileReader fr = new FileReader(filename);
        BufferedReader bfr = new BufferedReader(fr);
		try {
			line = bfr.readLine();
			while (line != null) {
				spltLine = line.indexOf(',');
				name = line.substring(0, spltLine);
				house = line.substring(spltLine + 1);
				fullName = bfr.readLine();
				//create FeUnit Object
				FeUnit placeholder = new FeUnit(name, fullName, house);
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
	
	/**
	 * reads in all fourth questions
	 */
	private static void importQues() throws FileNotFoundException {
		String unitQues;
		String quesResponse;
		String addition;
		for (FeUnit unit : myUnits) {
			try {
				FileReader f = new FileReader("C:\\Users\\Becca\\eclipse-workspace\\FE3H_CoffeeHour\\fourQ\\" + unit.getName() + "_four.txt");
				BufferedReader br = new BufferedReader(f);
				unitQues = br.readLine();
				while (unitQues != null) {
					quesResponse = br.readLine();
					addition = unitQues + "\n" + quesResponse + "\n";
					unit.addFourthQ(addition);
					unitQues = br.readLine();
				}
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * reads in the favorite teas
	 */
	private static void importTeas() throws FileNotFoundException {
		String unitName;
		int numTeas;
		try {
			FileReader f = new FileReader("C:\\Users\\Becca\\eclipse-workspace\\FE3H_CoffeeHour\\favoriteTea.txt");
			BufferedReader br = new BufferedReader(f);
			//for (int i = 0; i < 39; i++) {
			unitName = br.readLine();
			while (unitName != null) {
				numTeas = Integer.valueOf(unitName.substring(unitName.indexOf(' ') + 1));
				unitName = unitName.substring(0, unitName.indexOf(' '));
		        for (int j = 0; j < numTeas; j++) {
		        	findName(unitName).addFavTea(br.readLine());
		        }
		        unitName = br.readLine();
			}
	        br.close();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * reads in the firstAnswers for every unit
	 */
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
