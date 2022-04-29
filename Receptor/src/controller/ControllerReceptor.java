package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.DefaultTableModel;

import connection.Connection;
import model.Message;
import model.Receptor;
import view.VReceptor;

public class ControllerReceptor implements ActionListener, WindowListener, MouseListener, Observer {
	private ArrayList<Observable> obs = new ArrayList<Observable>(); 
	private VReceptor viewReceptor = null;
	
	public ControllerReceptor()
	{
		this.viewReceptor = new VReceptor();
		this.viewReceptor.addActionListener(this);
		this.viewReceptor.addWindowListener(this);
		this.viewReceptor.getTable().addMouseListener(this);
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
		int index = this.viewReceptor.getTable().getSelectedRow();
		if(index != -1) {
			Message msg = Receptor.getInstance().getReg().get(index);
			msg.setState("Recibido");
			refreshList();
			Connection.getInstance().response(true, msg.getInetAddress(), msg.getLoc().getPort());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
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
		    list.add(msg.getState());
			
		    model.addRow(list.toArray());
		    
		    this.viewReceptor.getTable().setModel(model);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int index = this.viewReceptor.getTable().getSelectedRow();
		if(index != -1) {
			Message msg = Receptor.getInstance().getReg().get(index);
			if(msg.getState().equals("Pendiente")) {
				this.viewReceptor.enableBtn();
			} else {
				this.viewReceptor.disableBtn();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
