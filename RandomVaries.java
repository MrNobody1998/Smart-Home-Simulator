import java.util.Random;

/** Class for RandomVaries Appliances
 * Children of Appliance Class
 *
 */

public class RandomVaries extends Appliance {

Random rand = new Random();
	
	private float maxunits; //max units per hour
	private float minunits; // min units per hour
	private float probability = 0; 
	
	/**
	 * Constructor Method for cyclic varies appliances
	 * @param appliancename 
	 * @param maxunits
	 * @param minunits
	 * @param probability
	 */
	
	public RandomVaries(String appliancename,float maxunits,float minunits,int probability) {
		super(appliancename);
		this.minunits = minunits;
		this.maxunits = maxunits;
		this.probability = (float)(1/probability);
	}

	/**
	 *  Method timePasses()
	 * if probability of the appliance being switched on is bigger than a random probabity
	 * then it calculates units consumed by the appliance 
	 * using the maximum and minimum range given 
	 * calls tellMeterToConsumeUnits and passes the calculated units.
	 */
	
	public void timePasses() {
		
		float randomprob = rand.nextFloat();
		float units = 0  ;
		
		if(probability > randomprob) {
			units = rand.nextFloat() * (maxunits-minunits) + minunits ;
			tellMeterToConsumeUnits(units);
		}
		
	}

}
