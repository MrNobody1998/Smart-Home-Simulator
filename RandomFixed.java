import java.util.Random;

/**Class for RandomFixed Appliances
 * Children of Appliance Class */
public class RandomFixed extends Appliance {

	private float unitsperhour ; //units per hour
	private float probability ; //probability switched on
	
	/**
	 * Constructor Method for random fixed appliances
	 * @param appliancename
	 * @param unitshour
	 * @param probabilityco
	 * @throws exception if active period is not between 1 and 24 hours
	 */
	
	public RandomFixed(String appliancename,float unitshour,int probabilityco){
		super(appliancename);
		this.probability =  ((float) 1 / probabilityco) ;
		this.unitsperhour = unitshour ;	
	}
	
	int unitsused = 0 ; //units used by the appliance 

	Random rand = new Random();
	
	/** Method timePasses()
	 * calculates consumed units used by the appliance
	 * if probability being switched on is bigger than random probability
	 * then the device is turned on 
	 * calls tellMeterToConsumeUnits and passes units used
	 */
	
	public void timePasses() {
		float randomprob = rand.nextFloat();
		
		if(probability>randomprob) {
			tellMeterToConsumeUnits(unitsperhour);
		}

	}

	
	
}
