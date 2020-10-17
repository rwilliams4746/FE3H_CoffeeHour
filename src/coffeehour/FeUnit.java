package coffeehour;

import java.util.ArrayList;

/**
 * 
 * 
 * @author Rebecca Williams
 * @version October 17, 2020
 *
 */
public class FeUnit {
	//global variables
	private String name; //unit name
	private String house; //church, golden deer, blue lions, black eagles, ashen wolves
	private ArrayList<String> firstAnswers = new ArrayList<>();
	private ArrayList<String> fourthQ = new ArrayList<>();

	/**
	 * constructor
	 */
	public FeUnit(String name, String houseAbbrev) {
		this.name = name;
		if (houseAbbrev.equals("blk")) {
			house = "Black Eagles";
		} else if (houseAbbrev.equals("blue")) {
			house = "Blue Lions";
		} else if (houseAbbrev.equals("gold")) {
			house = "Golden Deer";
		} else if (houseAbbrev.equals("seir")) {
			house = "Church of Seiros";
		} else if (houseAbbrev.equals("ash")) {
			house = "Ashen Wolves";
		} else {
			house = "Incorrect Spelling";
		}
	}
	
	
	
	/*
	 * getters and setters below
	 */
	
	public ArrayList<String> getFirstAnswers() {
		return firstAnswers;
	}

	public ArrayList<String> getFourthQ() {
		return fourthQ;
	}
	
	public String getName() {
		return name;
	}

	public String getHouse() {
		return house;
	}

	
	public void setFirstAnswers(ArrayList<String> firstAnswers) {
		this.firstAnswers = firstAnswers;
	}
	
	public void setFourthQ(ArrayList<String> fourthQ) {
		this.fourthQ = fourthQ;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * using abbreviation of house name, determine the house
	 */
	public void setHouse(String houseAbbrev) {
		if (houseAbbrev.equals("blk")) {
			house = "Black Eagles";
		} else if (houseAbbrev.equals("blue")) {
			house = "Blue Lions";
		} else if (houseAbbrev.equals("gold")) {
			house = "Golden Deer";
		} else if (houseAbbrev.equals("seir")) {
			house = "Church of Seiros";
		} else if (houseAbbrev.equals("ash")) {
			house = "Ashen Wolves";
		} else {
			house = "Incorrect Spelling";
		}
	}
}
