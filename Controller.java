package hw3Attempt2;

import javax.swing.SwingUtilities;

public class Controller {
	
	private static Controller controller = new Controller();
	private Model model;
	private View view;
	private GUIViewWithEditor guiView;
	
	private Controller() {
		model = new Model();
		view = new View(model);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				guiView = new GUIViewWithEditor(model);
			} // end method run
		});
	} // end ctor
	
	public static Controller getInstance() {
		return controller;
	} // end getInstance
	
	public void addDevice() {
		model.addDevice(model.createDevice());
	} // end addStudent
	
	public void addDevice(Device inc) {
		model.addDevice(inc);
	} // end addStudent overload
	
	public void addDevice(int index, Device inc) {
		model.addDevice(index, inc);
	} // end addStudent overload
	
	public void removeDevice(int inc) {
		model.removeDevice(inc);
	} // end removeStudent
	
	public void refresh() {
		for (Device eachOne: model.getInventory()) {
			view.showData(eachOne.toString());
		} // end for
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				guiView.showData();
			} // end method run
		});
	} // end refresh
	
	public void sortData() {
		model.sortInventory();
		refresh();
	} // end sortData

} // end Controller