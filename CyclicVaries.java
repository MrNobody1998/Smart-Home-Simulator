import java.util.Random;

/** Class for Cyclic Varies Appliances
 *  Children of Appliance Class */

public class CyclicVaries extends Appliance {
	
	Random rand = new Random();
	
	private int periodactive; //active hours of the appliance
	private int currenttime = 0; //current time
	private float maxunits; //max units per hour
	private float minunits; // min units per hour
	
	/** Constructor Method for Cyclic Varies Appliances
	 * @param appliancename
	 * @param minunits
	 * @param maxunits
	 * @param periodactive
	 *  throws expection if active period is not between 1 and 24 hours
	 */
	
	public CyclicVaries(String appliancename,float minunits,float maxunits,int periodactive) {
		super(appliancename);
		this.periodactive = periodactive;
		this.maxunits = maxunits;
		this.minunits = minunits;
		if (periodactive>24 || (periodactive<=1)) {
			throw new IllegalArgumentException("It must be between 1 and 24 hours");
		}

	}

	/** Method timePasses()
	 *  calculates units used by the appliance 
	 *  using the maximum and minimum range given 
	 *  Increases the current time by 1
	 */	
	
	public void timePasses() {
		float units = 0  ;
		int actualtime = currenttime % 24; 
		
		if ((actualtime>=0) && (actualtime<periodactive)) {
			units = rand.nextFloat() * (maxunits-minunits) + minunits ;
			tellMeterToConsumeUnits(units);
		}

		currenttime ++;

	}
	

}
