import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Class House.
 */

public class House {

	private Meter electricMeter;

	private Meter waterMeter ; 

	/**
	 * Main Method
	 * @param args
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	
	public static void main(String[] args) throws IOException {

		System.out.println("Enter 1 to reload, Enter 2 to read new file");
		Scanner cscanner = new Scanner(System.in);
		String answer = cscanner.next();	
		String fileName = "";
		String hours = "168";
		
		if (answer.equals("1")) {
			Reload reload = new Reload();		
			reload.reload();
		
		}else if(answer.equals("2")) {
			try {
			System.out.println("Enter file name");
			Scanner cscanner1 = new Scanner(System.in);
			fileName = cscanner1.next().toString();
			System.out.println("Give hours");
			hours = cscanner1.next().toString();
			cscanner1.close();
			
			}
			catch (Exception exc) {
				System.err.println("File not Found");
			}
		}
		
		
		ArrayList<String> lines = new ArrayList<String>() ; 		//ArrayList that equals all the lines of the file
		ArrayList<String> appliances = new ArrayList<String>() ; 	//ArrayList that equals all the appliances
		ArrayList<String> subclasses = new ArrayList<String>() ; 	//ArrayList that equals all the subclasses
		ArrayList<String> meters = new ArrayList<String>() ; 		//ArrayList that equals all the meters
		ArrayList<String> minUnits = new ArrayList<String>() ;		//ArrayList that equals all the minUnits
		ArrayList<String> maxUnits = new ArrayList<String>() ; 		//ArrayList that equals all the maxUnits
		ArrayList<String> fixedUnits = new ArrayList<String>() ; 	//ArrayList that equals all the fixedUnits
		ArrayList<String> probabilities = new ArrayList<String>() ; //ArrayList that equals all the probabilities
		ArrayList<String> cycleLength = new ArrayList<String>() ; 	//ArrayList that equals all the cycleLengths

		
				/*
		 * Reads each line of the given file
		 * Adds each line to the ArrayList lines
		 * Until the file is empty
		 * */
		
		Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
		while(scanner.hasNextLine()) {
			lines.add((scanner.nextLine()));
		}
		scanner.close();

		
		// The following methods check the syntax of the given file
		
				
		ErrorChecks cErrorChecks = new ErrorChecks();
		cErrorChecks.nameOfApplianceChecks(lines, appliances);
		cErrorChecks.subclassesChecking(lines, subclasses);
		cErrorChecks.meterChecking(lines,meters);
		cErrorChecks.minUnitsChecking(lines,minUnits);
		cErrorChecks.maxUnitsChecking(lines,maxUnits);
		cErrorChecks.fixedUnitsChecking(lines,fixedUnits);
		cErrorChecks.probabilityChecking(lines,probabilities);
		cErrorChecks.cycleLengthsChecking(lines,cycleLength);
		cErrorChecks.relationApplianceAndMeter(appliances,meters);
		
		
		BatteryMeter Electric = new BatteryMeter("Electric", 0.013 , 0.0f ,new Battery(0.0f));
		Meter Water = new Meter("Water", 0.002, 0.0f);

		House testHouse = new House(Electric,Water);

		cErrorChecks.cMeterChecking2(appliances,subclasses,meters,testHouse,minUnits,maxUnits,fixedUnits,cycleLength,probabilities);
		
		
		testHouse.activate(Integer.parseInt(hours));
		
		
		
		 // Writes the session to a file
		 PrintStream writer = new PrintStream(new File("currentstate.txt"));
		 
		 for(int c = 0; c <appliances.size();c++) {
			 writer.print(appliances.get(c) + ";");
			 writer.print(subclasses.get(c) + ";" );		
			 writer.print(meters.get(c) + ";" );
			 writer.print(minUnits.get(c) + ";" );		
			 writer.print(maxUnits.get(c) + ";" );
			 writer.print(fixedUnits.get(c) + ";" );		
			 writer.print(probabilities.get(c) + ";" );
			 writer.print(cycleLength.get(c) + ";"); 
		}
		 
		 writer.close();
	

		System.out.println();
		System.out.println("Number of Appliances used in the house: "+ testHouse.numAppliances() + " appliances");


	}


	/**
	 * Constructor
	 * Instantiates a new Meters.
	 */
	
	public House() {
		electricMeter = new Meter("Electric", 0.013, 0); //electric cost per unit 0.13, 0 units balance 
		waterMeter = new Meter("Water",0.002,0); //water cost per unit , 0 units balance
	}

	/**
	 * Overloaded Constructor
	 * @param electricMeter the electric meter
	 * @param waterMeter the water meter
	 */
	
	public House(Meter electricMeter,Meter waterMeter) {
		this.electricMeter = electricMeter;
		this.waterMeter = waterMeter;
	}

	
	//Arraylist for all Appliances
	private ArrayList<Appliance> allAppliances = new ArrayList<Appliance>();

		
	
	/**
	 * Method to add electric Appliances
	 * @param electricAppliance
	 */
	
	public void addElectricAppliance(Appliance electricAppliance) {
		allAppliances.add(electricAppliance);
		electricAppliance.setMeter(electricMeter);
	}

	/**
	 * Method to add Water Appliances
	 * 
	 * Adds a water appliance.
	 *
	 * @param waterAppliance
	 */
	public void addWaterAppliance(Appliance waterAppliance) {
		allAppliances.add(waterAppliance);
		waterAppliance.setMeter(waterMeter);
	}
	
	/**
	 * Method to remove an appliance from the house
	 * @param appliance
	 */
	
	public void removeAppliance(Appliance appliance) {
		allAppliances.remove(appliance);
	}

	/**
	 * numAppliances method
	 *
	 * @return the number of appliances in the house.
	 */

	public int numAppliances() {
		return allAppliances.size() ;
	}

	/**
	 * activate method 
	 * 
	 * @return the total cost of all appliances
	 *
	 */

	public double activate() {
		for(Appliance appliance : allAppliances) {
			appliance.timePasses();
		}

		double cost = electricMeter.report() +  waterMeter.report();

		return cost;
	}

	/**
	 * activate method
	 * Simulates each appliance for each hour
	 *
	 * @param hours : numbers of hours to simulate
	 * @return the totalCost 
	 */
	
	public double activate(int hours) {
		double totalCost = 0.0;

		for(int counter=1;counter<=hours;counter++) {
			for(Appliance appliance : allAppliances) {
				appliance.timePasses();
			}					
			totalCost += electricMeter.report()+  waterMeter.report();	
		}
		System.out.printf("The total Cost is: £ %.2f", totalCost);
		return totalCost;
	}
	
	

}
