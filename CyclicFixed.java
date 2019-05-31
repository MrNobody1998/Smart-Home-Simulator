/*Class for CyclicFixed Appliances
 * Child of Appliance Class */

public class CyclicFixed extends Appliance {

	private float unitsperhour; //units per hour
	private float periodactive; //active hours of the appliance
	private int currenttime = 0; //current time
	
	/* 
	 *  
	 */
	
	/**
	 * Constructor Method for Cyclic Fixed Appliances
	 * @param appliancename
	 * @param unitshour
	 * @param periodactive
	 * @throws expection if active period is not between 1 and 24 hours
	 */
	public CyclicFixed(String appliancename,float unitshour,int periodactive) {
		super(appliancename);
		this.unitsperhour = unitshour ;
		this.periodactive = periodactive;
		if (periodactive>24 || (periodactive<=1)) {
			throw new IllegalArgumentException("It must be between 1 and 24 hours");
		}
	}
	
	int unitsused = 0 ; //units used by the appliance 

	/** Method timePasses()
	 *  Calculates units used by the appliance 
	 *  Increases the current time by 1
	 */
	
	public void timePasses() {
		int actualtime = currenttime % 24; //time within the day
		
		if ((actualtime>=0) && (actualtime<periodactive)) {
			tellMeterToConsumeUnits(unitsperhour);
		}

		currenttime ++;

	}
	
	
}
