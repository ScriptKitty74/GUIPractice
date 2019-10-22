package hw3Attempt2;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class GUIViewWithEditor extends JFrame {
	private final Model model;
	private JFrame window;
	private JPanel panel;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton addDevice;
	private JButton sortInventory;
	
	private class Editor extends JDialog {
		
		private JLabel descriptionLabel;
		private JLabel dateAcquiredLabel;
		private JLabel priceLabel;
		private JLabel memoryLabel;
		private JLabel cpuLabel;
		private JLabel osLabel;
		private JTextField description;
		private JTextField dateAcquired;
		private JTextField price;
		private JTextField memory;
		private JTextField cpu;
		private JTextField os;
		private JButton submit;
		private JButton cancel;
		private JPanel panel;
		private boolean newRecord = false;
		
    	private class CancelHandler implements ActionListener {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		Editor.this.dispose();
        	} // end actionPerformed
        } // end CancelHandler
    	
    	private class SubmitHandler implements ActionListener {
    		@Override
    		public void actionPerformed(ActionEvent ae) {
    			Device temp = new Device(description.getText(), dateAcquired.getText(), price.getText(), memory.getText(), cpu.getText(), os.getText());
    			if (!newRecord) {
    				Controller.getInstance().removeDevice(table.getSelectedRow());
    				Controller.getInstance().addDevice(table.getSelectedRow(), temp);
    			} else {
    				Controller.getInstance().addDevice(temp);
    			} // end else
    			Controller.getInstance().refresh();
    			Editor.this.dispose();
    		} // end actionPerformed
    	} // end SubmitHandler
    	
    	public Editor() {
			initComponents();
		} // end ctor
    	
    	public Editor(boolean inc) {
    		this.newRecord = inc;
    		initComponents();
    	} // end ctor overload
		
		private void initComponents() {
			String localDescription = "";
			String localDateAcquired = "";
			String localPrice = "";
			String localMemory = "";
			String localCpu = "";
			String localOs = "";
			if (!newRecord) { 
				Device temp = model.getInventory().get(table.getSelectedRow());
				localDescription = temp.getDescription();
				localDateAcquired = temp.getDateAcquired();
				localPrice = temp.getPrice();
				localMemory = temp.getMemory();
				localCpu = temp.getCpu();
				localOs = temp.getOs();
			} // end if
			
			panel = new JPanel();
			panel.setLayout(null);
			
			descriptionLabel = new JLabel("Description");
			descriptionLabel.setSize(100, 25);
			descriptionLabel.setLocation(10, 10);
			panel.add(descriptionLabel);
			description = new JTextField(localDescription);
			description.setSize(100, 25);
			description.setLocation(110, 10);
			panel.add(description);

			dateAcquiredLabel = new JLabel("Date Acquired");
			dateAcquiredLabel.setSize(100, 25);
			dateAcquiredLabel.setLocation(10, 40);
			panel.add(dateAcquiredLabel);
			dateAcquired = new JTextField(localDateAcquired);
			dateAcquired.setSize(100, 25);
			dateAcquired.setLocation(110, 40);
			panel.add(dateAcquired);

			priceLabel = new JLabel("Price");
			priceLabel.setSize(100, 25);
			priceLabel.setLocation(10, 70);
			panel.add(priceLabel);
			price = new JTextField(localPrice);
			price.setSize(100, 25);
			price.setLocation(110, 70);
			panel.add(price);
			
			memoryLabel = new JLabel("Memory");
			memoryLabel.setSize(100, 25);
			memoryLabel.setLocation(10, 100);
			panel.add(memoryLabel);
			memory = new JTextField(localMemory);
			memory.setSize(100, 25);
			memory.setLocation(110, 100);
			panel.add(memory);

			cpuLabel = new JLabel("CPU");
			cpuLabel.setSize(100, 25);
			cpuLabel.setLocation(10, 130);
			panel.add(cpuLabel);
			cpu = new JTextField(localCpu);
			cpu.setSize(100, 25);
			cpu.setLocation(110, 130);
			panel.add(cpu);

			osLabel = new JLabel("Operating System");
			osLabel.setSize(100, 25);
			osLabel.setLocation(10, 160);
			panel.add(osLabel);
			os = new JTextField(localOs);
			os.setSize(100, 25);
			os.setLocation(110, 160);
			panel.add(os);
			
			submit = new JButton("Submit");
			submit.setSize(75, 20);
			submit.setLocation(30, 190);
			submit.addActionListener(new SubmitHandler());
			panel.add(submit);
			
			cancel = new JButton("Cancel");
			cancel.setSize(75, 20);
			cancel.setLocation(115, 190);
			cancel.addActionListener(new CancelHandler());
			panel.add(cancel);
			
			this.setContentPane(panel);
			this.setTitle("Data Editor");
			this.setSize(450, 400);
			this.setModal(true);
			this.setVisible(true);
		} // end initComponents
	} // end Editor

    private class RowSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (table.getSelectedRow() != -1 && e.getValueIsAdjusting()) {
                new Editor();
            } // end if
        } // end valueChanged
    } // end RowSelectionListener
    
    private class AddDevice implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			new Editor(true);
		} // end actionPerformed
	} // end AddDevice
    
    private class SortInventory implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ae) {
			Controller.getInstance().sortData();
		} // end actionPerformed
	} // end SortInventory
    
	public GUIViewWithEditor(Model inc) {
		model = inc;
		initComponents();
	} // end ctor
		
	public void initComponents() {
		panel = new JPanel();
		panel.setLayout(null);
		
        table = new JTable();
        showData();
        ListSelectionModel rowSelectionModel = table.getSelectionModel();
        rowSelectionModel.addListSelectionListener(new RowSelectionListener()); 
        
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(900, 125);
		panel.add(scrollPane);
		
		addDevice = new JButton("Add Device");
		addDevice.setSize(125, 25);
		addDevice.setLocation(310, 150);
		addDevice.addActionListener(new AddDevice());
		panel.add(addDevice);
		
		sortInventory = new JButton("Sort Inventory");
		sortInventory.setSize(125, 25);
		sortInventory.setLocation(460, 150);
		sortInventory.addActionListener(new SortInventory());
		panel.add(sortInventory);
		
		window = new JFrame();
		window.setSize(917, 400);
		window.setTitle("My demonstration GUI");
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setContentPane(panel);
		window.setVisible(true);
	} // end initComponents
	
	public void showData() {
        String[] columnHeaders = {"Device Type","Date Acquired","Price","Memory","CPU","Operating System"};
        String[][] rows = new String[model.getInventory().size()][3];
        int i = 0;
        for (Device eachOne: model.getInventory()) {
            
            String[] oneRow = {eachOne.getDescription(), eachOne.getDateAcquired(), eachOne.getPrice(), eachOne.getMemory(), eachOne.getCpu(), eachOne.getOs()};
            rows[i++] = oneRow;
        } // end for
        
        DefaultTableModel tableModel = new DefaultTableModel(rows, columnHeaders);
        table.setModel(tableModel);
        table.getColumnModel().getColumn(1).setWidth(300); 
        table.getColumnModel().getColumn(1).setMaxWidth(300); 
        table.getColumnModel().getColumn(1).setMinWidth(300); 
    } // end initTableData
} // end GUIView