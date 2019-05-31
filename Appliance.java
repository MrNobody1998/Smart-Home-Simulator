// Abstract class for Appliances
public abstract class Appliance {
	@SuppressWarnings("unused")
	private String applianceName;
	private Meter meter;
	
	/**
	 * @param applianceName
	 */
	
	public Appliance(String applianceName) {
		this.applianceName = applianceName;
	}

	/** Getter Method
	 *  Returns the Meter */
	
	public Meter getMeter() {
		return meter;
	}

	/**
	 * Setter Method for meter
	 * @param meter
	 */
	
	public void setMeter(Meter meter) {
		this.meter = meter;
	}
	
	/**
	 * timePasses()
	 * Calculates units used by the appliance 
	 * Increases the current time by 1
	 */
	public abstract void timePasses();
	
	/**
	 * Calls the consumeUnits Method 
	 * passes the units associated with the appliance that are consumed 
	 * @param units 
	 */
	protected void tellMeterToConsumeUnits(float units) {
		meter.consumeUnits(units);
	
	}
	
}
