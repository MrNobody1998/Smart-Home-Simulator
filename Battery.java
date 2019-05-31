
/**
 * Battery Class
 * Holds the capacity of the battery and its current status
 * Charges or discharges the battery based on the meterReading
 */
public class Battery {
	
	private float capacity = 50.0f; //maximum capacity of the battery
	float batteryStatus= 0; //current battery status
	
	public Battery(float capacity) {
		this.capacity = capacity;
	}
	
	//Method that charges the battery
	public void chargeBattery(float currentCharge) {
		
		float temporaryCharge = this.batteryStatus + currentCharge; 
		
		if (temporaryCharge >= capacity) {
			currentCharge = capacity ;
		}
	}
	
	
	/** dischargeBattery method discharges the battery
	 * checks if there are available units in the battery
	 * then if any available then they are consumed
	 * returns the rest of the units that have to be credited*/ 
	
	public float dischargeBattery(float unitsToConsume) {
		
		float temporarydisCharge = batteryStatus - unitsToConsume ;
		float unitsToBeCredit = 0 ; //the rest of the units that will be credited
		
		if (temporarydisCharge < 0) {
			batteryStatus = 0 ;
			unitsToBeCredit = Math.abs(temporarydisCharge);

		} else {
			batteryStatus -= unitsToConsume ;
		}
	
		return unitsToBeCredit;
		
	}
	
}
