import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Andreas
 * Reload Class
 * Reloads the last session of the House
 */
public class Reload {

	private List<String> meters;

	/**
	 * Reload Method
	 * Reads the currentstate file
	 * Runs the previous Session
	 */
	public void reload(){

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("currentstate.txt"));
			String read = null;
			while ((read = in.readLine()) != null) {
				String[] splited = read.split(";");

				List<String> nl = new ArrayList<>();
				nl = Arrays.asList(splited);

				List<String> appliances = new ArrayList<>();
				List<String> subclass = new ArrayList<>();
				List<String> meter = new ArrayList<>();
				List<String> minUnits = new ArrayList<>();
				List<String> maxUnits = new ArrayList<>();
				List<String> fixedUnits = new ArrayList<>();
				List<String> probabilities = new ArrayList<>();
				List<String> cycleLength = new ArrayList<>();

				for(int c=0 ; c<nl.size();c+=8) {
					appliances.add(nl.get(c));
				}

				for(int c=1 ; c<nl.size();c+=8) {
					subclass.add(nl.get(c));
				}

				for(int c=2 ; c<nl.size();c+=8) {
					meter.add(nl.get(c));
				}

				for(int c=3 ; c<nl.size();c+=8) {
					minUnits.add(nl.get(c));
				}

				for(int c=4 ; c<nl.size();c+=8) {
					maxUnits.add(nl.get(c));
				}

				for(int c=5 ; c<nl.size();c+=8) {
					fixedUnits.add(nl.get(c));
				}

				for(int c=6 ; c<nl.size();c+=8) {
					probabilities.add(nl.get(c));
				}

				for(int c=7 ; c<nl.size();c+=8) {
					cycleLength.add(nl.get(c));
				}

				BatteryMeter Electric = new BatteryMeter("Electric", 0.013 , 0.0f ,new Battery(0.0f));
				Meter Water = new Meter("Water", 0.002, 0.0f);
				House testHouse = new House(Electric,Water);

				for(int counter=0; counter<subclass.size(); counter++) {
					if (subclass.get(counter).equals("CyclicFixed")) {
						CyclicFixed cCyclicFixedElectric = new CyclicFixed(appliances.get(counter), Float.parseFloat(fixedUnits.get(counter).trim()), Integer.parseInt(cycleLength.get(counter).trim()));
						if(meter.get(counter).equals("electric")) {
							testHouse.addElectricAppliance(cCyclicFixedElectric);
						}
						else if(meter.get(counter).equals("water")){
							testHouse.addWaterAppliance(cCyclicFixedElectric);
						}

					}else if(subclass.get(counter).equals("CyclicVaries")) {
						CyclicVaries cCyclicVaries = new CyclicVaries(appliances.get(counter),Float.parseFloat(minUnits.get(counter).trim()),Float.parseFloat(maxUnits.get(counter).trim()),Integer.parseInt(cycleLength.get(counter).trim()));
						if(meter.get(counter).equals("electric")) {
							testHouse.addElectricAppliance(cCyclicVaries);
						}
						else if(meters.get(counter).equals("water")){
							testHouse.addWaterAppliance(cCyclicVaries);
						}

					}else if(subclass.get(counter).equals("RandomFixed")) {
						RandomFixed cRandomFixed = new RandomFixed(appliances.get(counter),Float.parseFloat(fixedUnits.get(counter).trim()),Integer.parseInt(probabilities.get(counter)));
						if(meter.get(counter).equals("electric") ) 
							testHouse.addElectricAppliance(cRandomFixed);
						else if(meter.get(counter).equals("water")){
							testHouse.addWaterAppliance(cRandomFixed);
						}

					}
					else if(subclass.get(counter).equals("RandomVaries")) {
						RandomVaries cRandomVaries = new RandomVaries(appliances.get(counter),Float.parseFloat(minUnits.get(counter).trim()),Float.parseFloat(maxUnits.get(counter).trim()),Integer.parseInt(probabilities.get(counter)));
						if(meter.get(counter).equals("electric") ) {
							testHouse.addElectricAppliance(cRandomVaries);
						}
						else if(meter.get(counter).equals("water")){
							testHouse.addWaterAppliance(cRandomVaries);
						}
					}
				}
				String hours = "168";

				try {
					Scanner cscanner1 = new Scanner(System.in);

					System.out.println("Give hours");
					hours = cscanner1.next().toString();
					cscanner1.close();
					
					if (Integer.parseInt(hours)<0) {
						System.err.println("Hours cannot be negative. The default of 7 days will be simulated.");
						hours = "168";
					}
					
					


				}
				catch (Exception exc) {
					System.err.println("You have to put a number. The default of 7 days will be simulated.");
				}

				testHouse.activate(Integer.parseInt(hours));				


				System.exit(0);
			}



		}catch (Exception FileNotFound) {
			System.err.println("File not Found");
		}

	}


}
