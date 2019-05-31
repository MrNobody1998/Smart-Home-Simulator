
/**Class Meter
 * Represents the meters 
 * manages the consumption of an utility
 */
public class Meter {
	
	//Private properties of Meters
	protected String utilityName; //Type of the utility
	protected double unitCost; //Cost per unit
	protected float meterReading;  //Balance of units since the last meter reading
	
	/**
	 * @param utilityName
	 * @param unitCost
	 * @param meterReading
	 */
	
	public Meter(String utilityName, double unitCost, float meterReading) {
		this.utilityName = utilityName;
		this.unitCost = unitCost;
		this.meterReading = meterReading;
	} 

	/** Method consumeUnits()
	 *  @param units : the number of units
	 * adding up to consumed units
	*/
	/**
	
	 */
	public void consumeUnits(float units ) {
		meterReading += units  ;
	}
	
	/** Method report()
	 * Prints out: Utility Name , balance of units and meter reading
	 * @returns cost : the final cost
	 */ 
	
	public double report() {
		double cost = this.unitCost * this.meterReading;
		System.out.println("Utility Name: " + this.utilityName);
		System.out.println("The Balance of Units used is: " + this.meterReading + " kW/h");
		System.out.println("Total Cost: £ " +  cost  );
		this.meterReading = 0 ;
		return cost;
	}
	
	
}
