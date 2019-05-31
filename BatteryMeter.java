/**
 * Battery Meter Class
 * Decides if the battery will be charged or discharged
 * Reports the consumption on a battery
 * Reports the rest of the units that will be credited 
 */

public class BatteryMeter extends Meter {
	
	private Battery battery;
	
	/**
	 * @param utilityName
	 * @param unitCost
	 * @param meterReading
	 * @param battery
	 */
	
	public BatteryMeter(String utilityName, double unitCost, float meterReading,Battery battery) {
		super(utilityName, unitCost, meterReading);
		this.battery = battery ;
	}
	
	/** 
	 * Method consumeUnits()
	 * Adds the consumed units to the meterReading
	 * @param units : the number of units consumed
	*/
	
	public void consumeUnits(float units ) {
		meterReading += units  ;
	}
	
	/** Method report()
	 * Prints out: Utility Name , the balance of Units , theMeter reading and the units used by the battery
	 * @returns the final cost
	 */ 		
	@Override
	public double report() {
		float unitsToBeCredit = currentChargeHandler(meterReading);
		
		double cost = this.unitCost * unitsToBeCredit;		
		System.out.println("Utility Name: " + this.utilityName);
		System.out.println("The Balance of Units used is: " + this.meterReading + " kW/h");
			
		System.out.println("Units Consumed by the Battery: " + (unitsToBeCredit - meterReading));
		
		this.meterReading = 0 ;
		
		/* This check satisfies the scenario 
		   when the production is more than the consumption*/
		
		if (cost < 0) { 
			cost = 0;
		}
		
		return cost;
	}
	
	
	/**
	 * currentChargeHandler method that checks if the battery will be charged or discharged 
	 * @param meterReading
	 * @return the units that have to be used by the Meter
	 */
	
	public float currentChargeHandler(float meterReading) {
				
		if (meterReading <= 0) {		
			battery.chargeBattery(meterReading);
			return 0;
		}else {
			return battery.dischargeBattery(meterReading);
		}
		
	}
	
}
