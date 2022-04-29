package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.DefaultTableModel;

import model.Message;
import model.Receptor;
import view.VReceptor;

public class ControllerReceptor implements ActionListener, WindowListener, Observer {
	private ArrayList<Observable> obs = new ArrayList<Observable>(); 
	private VReceptor viewReceptor = null;
	
	public ControllerReceptor()
	{
		this.viewReceptor = new VReceptor();
		this.viewReceptor.addActionListener(this);
		this.viewReceptor.addWindowListener(this);
		obs.add(Receptor.getInstance());
		Receptor.getInstance().addObserver(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("UPDATE");
		refreshList();
	}
	
	public void refreshList() {
		ArrayList<Message> reg = Receptor.getInstance().getReg();
		DefaultTableModel dm = (DefaultTableModel) viewReceptor.getTable().getModel();
		dm.getDataVector().removeAllElements();
		dm.fireTableDataChanged();
		
		for (Message msg : reg) {
			DefaultTableModel model = (DefaultTableModel) viewReceptor.getTable().getModel();
		    List<String> list = new ArrayList<String>();
		    list.add(msg.getLoc().getName());
		    list.add(msg.getDate().getHour() + ":" + msg.getDate().getMinute());
		    list.add(msg.getEvent().getEventType());
			list.add("Pendiente");
			
		    model.addRow(list.toArray());
		    
		    this.viewReceptor.getTable().setModel(model);
		}
	}
	
}
