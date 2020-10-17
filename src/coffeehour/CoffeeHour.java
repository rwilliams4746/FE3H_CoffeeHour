package coffeehour;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
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
public class CoffeeHour {
	//global variables
	private static ArrayList<FeUnit> myUnits = new ArrayList<>();
	private static ArrayList<FeUnit> searchedUnits = new ArrayList<>();
	
	//JFrame
	private static JFrame f;
	
	//JLabel
	private static JLabel label1;
	private static JLabel label2;
	
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
		
		//debugging
		for (FeUnit feh : myUnits) {
			System.out.println(feh.getName() + " is in house: " + feh.getHouse());
		}
		
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
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
