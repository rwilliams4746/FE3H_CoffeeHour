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
	private String fullName;
	private String house; //church, golden deer, blue lions, black eagles, ashen wolves
	private String picPath;
	private String housePath;
	private ArrayList<String> favTea = new ArrayList<>();
	
	private ArrayList<String> firstAnswers = new ArrayList<>();
	private ArrayList<String> fourthQ = new ArrayList<>();

	/**
	 * constructor
	 */
	public FeUnit(String name, String fullName, String houseAbbrev) {
		this.name = name;
		this.fullName = fullName;
		if (houseAbbrev.equals("blk")) {
			house = "Black Eagles";
			housePath = "C:\\Users\\Becca\\eclipse-workspace\\FE3H_CoffeeHour\\images\\banners\\BlackEagles.png";
		} else if (houseAbbrev.equals("blue")) {
			house = "Blue Lions";
			housePath = "C:\\Users\\Becca\\eclipse-workspace\\FE3H_CoffeeHour\\images\\banners\\BlueLions.png";
		} else if (houseAbbrev.equals("gold")) {
			house = "Golden Deer";
			housePath = "C:\\Users\\Becca\\eclipse-workspace\\FE3H_CoffeeHour\\images\\banners\\GoldenDeer.png";
		} else if (houseAbbrev.equals("seir")) {
			house = "Church of Seiros";
			housePath = "C:\\Users\\Becca\\eclipse-workspace\\FE3H_CoffeeHour\\images\\banners\\ChurchofSeiros.png";
		} else if (houseAbbrev.equals("ash")) {
			house = "Ashen Wolves";
			housePath = "C:\\Users\\Becca\\eclipse-workspace\\FE3H_CoffeeHour\\images\\banners\\AshenWolves.png";
		} else {
			house = "Incorrect Spelling";
			housePath = "try spelling for once in your goddamn life";
		}
		findProfilePic();
	}
	
	private void findProfilePic() {
		picPath = "C:\\Users\\Becca\\eclipse-workspace\\FE3H_CoffeeHour\\images\\profiles\\" + name + ".png";
	}
	
	/*
	 * getters and setters below
	 */
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
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

	public String getPicPath() {
		return picPath;
	}
	
	public String getHousePath() {
		return housePath;
	}
	
	public ArrayList<String> getFavTea() {
		return favTea;
	}
	
	public void addFirstAnswers(String line) {
		firstAnswers.add(line);
	}
	
	public void setFirstAnswers(ArrayList<String> firstAnswers) {
		this.firstAnswers = firstAnswers;
	}
	
	public void addFavTea(String teaName) {
		favTea.add(teaName);
	}
	
	public void addFourthQ(String fourthQ) {
		this.fourthQ.add(fourthQ);
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
