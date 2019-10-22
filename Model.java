package hw3Attempt2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import hw3.Laptop;

public class Model {
	private ArrayList<Device> inventory = new ArrayList();
	
	public Model() {
		makeDemoData();
	} // end ctor
	
	public void addDevice(Device inc) {
		inventory.add(inc);
	} // end addDevice
	
	public void addDevice(int index, Device inc) {
		inventory.add(index, inc);
	} // end addDevice overload
	
	public void removeDevice(int inc) {
		inventory.remove(inc);
	} // end removeDevice
	
	public void sortInventory() {
		Collections.sort(inventory);
	} // end sortInventory
	
	public ArrayList<Device> getInventory() {
		return inventory;
	} // end getInventory
	
	public Device createDevice() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the device type: ");
		String description = input.next();
		System.out.println("Enter the date the device was acquired: ");
		String dateAcquired = input.next();
		System.out.println("Enter the devices price: ");
		String price = input.next();
		System.out.println("Enter the devices memory: ");
		String memory = input.next();
		System.out.println("Enter the devices cpu: ");
		String cpu = input.next();
		System.out.println("Enter the devices operating system: ");
		String os = input.next();
		return new Device(description, dateAcquired, price, memory, cpu, os);
	} // end createDevice
	
	private void makeDemoData() {
		inventory.add(new Device("Laptop", "07/01/2015", "459.99", "8GB", "Intel Core i3-8100", "Windows 8"));
//		inventory.add(new Device("Cellular","09/14/2015", 899.99, "16GB", "octa-core (4x2.1GHz + 4x1.5GHz)", "Android Pie 9.0", 2160, 4));
//		inventory.add(new Device("Desktop","01/01/2014", 799.99, "16GB", "Intel Core i7-8086K", "Windows 10", "512GB"));
//		inventory.add(new Device("Tablet","01/15/2018", 349.99, "32GB", "Apple A9", "iOS 12.2", 720, false, 0));
		
	} // end makeDemoData
} // end Model