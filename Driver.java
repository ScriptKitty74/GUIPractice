package hw3Attempt2;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Controller controller = Controller.getInstance();
		while (true) {
			doMenu(controller);
		} // end while
	} // end main
	
	public static void doMenu(Controller inc) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please make your selection: ");
		System.out.println("1: Refresh the data.");
		System.out.println("2: Sort the data.");
		System.out.println("3: Add a new Device.");
		System.out.println("0: Exit the application.");
		try {
			int selection = Integer.valueOf(input.next());
			switch (selection) {
				case 1: inc.refresh(); break;
				case 2: inc.sortData(); break;
				case 3: inc.addDevice(); break;
				default: System.exit(0);
			} // end switch
		} catch (Exception e) {
			System.out.println("ERROR: Type in a number only.");
		} // end catch
	} // end doMenu
} // end Driver