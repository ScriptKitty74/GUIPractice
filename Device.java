package hw3Attempt2;



public class Device implements Comparable {
	private final String description;
	private final String dateAcquired;
	private final String price;
	private final String memory;
	private final String cpu;
	private final String os;
	
	public Device(String description, String dateAcquired, String price, String memory, String cpu, String os) {
		this.description = description;
		this.dateAcquired = dateAcquired;
		this.price = price;
		this.memory = memory;
		this.cpu = cpu;
		this.os = os;
	} // end ctor

	public String getDescription() {
		return description;
	} // end getDescription

	public String getDateAcquired() {
		return dateAcquired;
	} // end getDateAcquired

	public String getPrice() {
		return price;
	} // end getPrice
	
	public String getMemory() {
		return memory;
	} // end getMemory

	public String getCpu() {
		return cpu;
	} // end getCpu

	public String getOs() {
		return os;
	} // end getOs

	@Override
	public String toString() {
		return "Device [description=" + description + ", dateAcquired=" + dateAcquired + ", price=" + price + ", memory=" + memory + ", cpu=" + cpu + ", os=" + os + "]";
	} // end toString

	@Override
	public int compareTo(Object inc) {
		return this.dateAcquired.compareTo(((Device)inc).dateAcquired);
	} // end compareTo
} // end Device