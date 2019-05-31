import java.io.IOException;
import java.util.ArrayList;

/**
 * The following methods check the syntax of the given file
 * if something is not following the syntax
 * then an IOException is thrown that informs the user a syntax error
 * else
 * the parameter given for the specific field is added to the arrayList that corresponds
 * 
 **/

public class ErrorChecks {
	
	/**
	 * The following method checks the names of the appliances
	 * @param lines : all the lines of the file
	 * @param appliances : all the appliances
	 * @throws IOException 
	 */
	
	public void nameOfApplianceChecks(ArrayList<String> lines, ArrayList<String> appliances) throws IOException {
		for(int counter=0;counter<(lines.size()-1);counter+=9) {
			String tempName = lines.get(counter).substring(lines.get(counter).indexOf(" ")+1, lines.get(counter).length());

			if(lines.get(counter).startsWith("name:") == false) {
				int currentline = counter + 1;
				throw new IOException("Syntax Error at line " + currentline + ". It should start with the follow syntax 'name: applianceName'");
			}

			if(lines.get(counter).length() < 8) {
				throw new IOException("No name provided"); 
			}
			appliances.add(tempName);
		}
	}
	
	/**
	 * The following method checks the subclasses of the appliances
	 * @param lines : all the lines of the file
	 * @param subclasses 
	 * @throws IOException
	 */
	public void subclassesChecking(ArrayList<String> lines, ArrayList<String> subclasses) throws IOException {
		for(int counter=1;counter<(lines.size()-1);counter+=9) {
			String tempSubclass = lines.get(counter).substring(lines.get(counter).indexOf(" ")+1, lines.get(counter).length());

			if(lines.get(counter).startsWith("subclass: ") == false) {
				int currentline = counter + 1;
				throw new IOException("Syntax Error at line " + currentline + ". It should start with the follow syntax 'subclass: subclassName'");
			}

			if(lines.get(counter).length() < 12) {
				int currentline = counter + 1;
				throw new IOException("Subclass error on line:" + currentline); 
			}
			subclasses.add(tempSubclass);
		}
	}

	/**
	 * The following method checks the meters of the appliances
	 * @param lines : all the lines of the file
	 * @param meters : all the meters of the appliance
	 * @throws IOException
	 */
	public void meterChecking(ArrayList<String> lines, ArrayList<String> meters) throws IOException {
		for(int counter=2;counter<(lines.size()-1);counter+=9) {
			String tempMeter = lines.get(counter).substring(lines.get(counter).lastIndexOf(" ")+1, lines.get(counter).length());
			
			if(lines.get(counter).startsWith("meter: ") == false) {
				int currentline = counter + 1;
				throw new IOException("Syntax Error at line " + currentline + ". It should start with the follow syntax 'meter: meterName'");
			}

			if(lines.get(counter).length() < 8 ) {
				throw new IOException("No Meter provided"); 
			}
			
			int currentFileLine = counter + 1 ;
			if(tempMeter.equals("electric") == false && tempMeter.equals("water") == false) {
				throw new IOException("Error at line: "+ currentFileLine + " There is not such a type of Meter");
			}
		

			meters.add(tempMeter);
		}
		
	}

	/**
	 * The following method checks the minimum Units of each appliance
	 * @param lines : all the lines of the file
	 * @param minUnits : the minimum units of each appliance
	 * @throws IOException
	 */
	public void minUnitsChecking(ArrayList<String> lines, ArrayList<String> minUnits) throws IOException {
		for(int counter=3;counter<(lines.size()-1);counter+=9) {
			String tempMinUnits = "";

			if(lines.get(counter).startsWith("Min units consumed:") == false) {
				int currentline = counter + 1;
				throw new IOException("Syntax Error at line " + currentline + ". It should start with the follow syntax 'Min units consumed: units'");
			}

			if (lines.get(counter).length() > 19) {
				tempMinUnits = lines.get(counter).substring(lines.get(counter).indexOf(":")+1, lines.get(counter).length());
			}

			minUnits.add(tempMinUnits);
		}
		
	}

	/**
	 * The following method checks the maximum units of each appliance
	 * @param lines : all the lines of the file
	 * @param maxUnits : the maximum units of each appliance
	 * @throws IOException
	 */
	public void maxUnitsChecking(ArrayList<String> lines, ArrayList<String> maxUnits) throws IOException {

		for(int counter=4;counter<(lines.size()-1);counter+=9) {
			String tempMaxUnits = "";

			if(lines.get(counter).startsWith("Max units consumed:") == false) {
				int currentline = counter + 1;
				throw new IOException("Syntax Error at line " + currentline + ". It should start with the follow syntax 'Max units consumed: units'");
			}

			if (lines.get(counter).length() > 19) {
				tempMaxUnits = lines.get(counter).substring(lines.get(counter).indexOf(":")+1, lines.get(counter).length());
			}
			maxUnits.add(tempMaxUnits);
		}
	}
	
	/**
	 * The following method checks the fixed units of each appliance
	 * @param lines : all the lines of the file
	 * @param fixedUnits : the fixed units of each appliance
	 * @throws IOException
	 */
	
	public void fixedUnitsChecking(ArrayList<String> lines, ArrayList<String> fixedUnits) throws IOException {
		for(int counter=5;counter<(lines.size()-1);counter+=9) {
			String tempFixedUnits = "";

			if(lines.get(counter).startsWith("Fixed units consumed:") == false) {
				int currentline = counter + 1;
				throw new IOException("Syntax Error at line " + currentline + ". It should start with the follow syntax 'Fixed units consumed: units'");
			}

			if (lines.get(counter).length()>22) {
				tempFixedUnits = lines.get(counter).substring(lines.get(counter).indexOf(": ")+1, lines.get(counter).length());
			}
			fixedUnits.add(tempFixedUnits);
		}
	}
	
	/**
	 * The following method checks the probability of an appliance being switched on
	 * @param lines : all the lines of the file
	 * @param probabilities : the probability of an appliance being switched on
	 * @throws IOException
	 */
	public void probabilityChecking(ArrayList<String> lines, ArrayList<String> probabilities) throws IOException {
		for(int counter=6;counter<(lines.size()-1);counter+=9) {
			String tempProbability = "";
			int currentLine = counter + 1;
			
			if(lines.get(counter).startsWith("Probability switched on:") == false) {
				
				throw new IOException("Syntax Error at line: " + currentLine + ". It should start with the follow syntax 'Probability switched on: '");
			}

			if (lines.get(counter).length()>25) {
				tempProbability = lines.get(counter).substring(lines.get(counter).lastIndexOf(" ")+1, lines.get(counter).length());
			} 
			
			if(tempProbability.startsWith("-")) {
				
				throw new IOException("Error at line: " + currentLine + ". Probability cannot be negative.");
			}

			probabilities.add(tempProbability);
		}
	}
	
	/**
	 * The following method checks the cycle lengths of each appliance
	 * @param lines : all the lines of the file
	 * @param cycleLength : the cycle lengths of each appliance
	 * @throws IOException
	 */
	public void cycleLengthsChecking(ArrayList<String> lines, ArrayList<String> cycleLength) throws IOException {
		for(int counter=7;counter<(lines.size()-1);counter+=9) {
			String tempCycleLength = "";

			if(lines.get(counter).startsWith("Cycle length:") == false) {
				int currentline = counter + 1;
				throw new IOException("Syntax Error at line " + currentline + ". It should start with the follow syntax 'Cycle length: cycleLength'");
			}

			try {
				if (lines.get(counter).length() > 14) {
					tempCycleLength = lines.get(counter).substring(lines.get(counter).indexOf(": ")+1, lines.get(counter).indexOf("/"));
				}
			}
			catch (Exception e){
				System.err.println("Cycle Length has to be in the format. Cycle length: hours/24");	
			}
			
			
			cycleLength.add(tempCycleLength);
		}
	}

	/**
	 * @param appliances : all the appliances
	 * @param meters : all the meters of the appliance
	 * @throws IOException
	 */
	public void relationApplianceAndMeter(ArrayList<String> appliances, ArrayList<String> meters) throws IOException {
		for(int counter=0;counter<(appliances.size()-1);counter++){	
			switch (appliances.get(counter)) {
			case "Lights": 
			case "Fridge":
			case "Digital clock":
			case "Treadmill":
			case "Cooker":
			case "Solar panels":
			case "TV":
			case "Kettle":
			case "Computer":
			case "Wind turbine":{
				if (meters.get(counter).equals("electric") == false ) {
					throw new IOException(appliances.get(counter) + " cannot be assigned to a water meter.");
				}
			}
			}
			
			switch (appliances.get(counter)) {
			case "Toilet":
			case "Hose": 
			case "Kitchen taps":{
				if (meters.get(counter).equals("water") == false) {
					throw new IOException(appliances.get(counter) + " cannot be assigned to an electric meter.");
				}
			}
			}
		}
		
	}

	/**
	 * @param appliances : all the appliances
	 * @param subclasses : the subclass of each appliance
	 * @param meters : all the meters of the appliance
	 * @param testHouse : the current House
	 * @param minUnits : the minimum units of each appliance
	 * @param maxUnits : the maximum units of each appliance
	 * @param fixedUnits : the fixed units of each appliance
	 * @param cycleLength : the cycle lengths of each appliance
	 * @param probabilities : the probability of an appliance being switched on
	 */
	public void cMeterChecking2(ArrayList<String> appliances, ArrayList<String> subclasses, ArrayList<String> meters, House testHouse, ArrayList<String> minUnits, ArrayList<String> maxUnits, ArrayList<String> fixedUnits, ArrayList<String> cycleLength, ArrayList<String> probabilities) {
		for(int counter=0;counter<appliances.size();counter++) {
			String tempSubClass = subclasses.get(counter);

			if(tempSubClass.equals("CyclicFixed")) {
				 
				try {
					@SuppressWarnings("unused")
					CyclicFixed cCyclicFixedElectric2 = new CyclicFixed(appliances.get(counter), Float.parseFloat(fixedUnits.get(counter).trim()), Integer.parseInt(cycleLength.get(counter).trim())); 

				}catch (Exception e){
					System.err.println("Wrong input data type of the appliance" + appliances.get(counter));
				}
				
				CyclicFixed cCyclicFixedElectric = new CyclicFixed(appliances.get(counter), Float.parseFloat(fixedUnits.get(counter).trim()), Integer.parseInt(cycleLength.get(counter).trim()));
				
				if(meters.get(counter).equals("electric")) {
					testHouse.addElectricAppliance(cCyclicFixedElectric);
				}
				else if(meters.get(counter).equals("water")){
					testHouse.addWaterAppliance(cCyclicFixedElectric);
				}else {
					System.err.println("Wrong type of Meter");
				}

			}else if (tempSubClass.equals("CyclicVaries")){

				try {
					CyclicVaries cCyclicVaries = new CyclicVaries(appliances.get(counter),Float.parseFloat(minUnits.get(counter).trim()),Float.parseFloat(maxUnits.get(counter).trim()),Integer.parseInt(cycleLength.get(counter).trim()));

					if(meters.get(counter).equals("electric")) {
						testHouse.addElectricAppliance(cCyclicVaries);
					}
					else if(meters.get(counter).equals("water")){
						testHouse.addWaterAppliance(cCyclicVaries);
					}
					else {
						System.err.println("Wrong type of Meter");
					}

				}
				catch (Exception IOException){
					System.err.println("Wrong input data of the appliance " + appliances.get(counter));
				}


			}else if (tempSubClass.equals("RandomFixed")) {

				try {

					RandomFixed cRandomFixed = new RandomFixed(appliances.get(counter),Float.parseFloat(fixedUnits.get(counter).trim()),Integer.parseInt(probabilities.get(counter)));

					if(meters.get(counter).equals("electric")) {
						testHouse.addElectricAppliance(cRandomFixed);
					}
					else if(meters.get(counter).equals("water")){
						testHouse.addWaterAppliance(cRandomFixed);
					}else {
						System.err.println("Wrong type of Meter");
					}
				}
				catch (Exception IOException) {
					System.err.println("Wrong input data of appliance " + appliances.get(counter));
				}

			}else if(tempSubClass.equals("RandomVaries")) {
				try {

					RandomVaries cRandomVaries = new RandomVaries(appliances.get(counter),Float.parseFloat(minUnits.get(counter).trim()),Float.parseFloat(maxUnits.get(counter).trim()),Integer.parseInt(probabilities.get(counter)));

					if(meters.get(counter).equals("electric") ) {
						testHouse.addElectricAppliance(cRandomVaries);
					}
					else if(meters.get(counter).equals("water")){
						testHouse.addWaterAppliance(cRandomVaries);
					}else {
						System.err.println("Wrong type of Meter");
					}
				}
				catch (Exception e) {
					System.err.println("Wrong input data of appliance " + appliances.get(counter));
				}
			}
		}
	}
	
}
