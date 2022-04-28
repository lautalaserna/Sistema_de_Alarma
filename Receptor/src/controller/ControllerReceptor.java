package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import connection.Connection;
import model.Message;
import model.Receptor;
import view.VReceptor;

public class ControllerReceptor implements ActionListener, WindowListener{

	private VReceptor viewReceptor = null;
	
	public ControllerReceptor()
	{
		this.viewReceptor = new VReceptor();
		this.viewReceptor.addActionListener(this);
		this.viewReceptor.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		this.viewReceptor.getTable().clearSelection();
		
		ArrayList<Message> msgs = Receptor.getInstance().getReg();
		
		for (Message msg : msgs) {

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
}
