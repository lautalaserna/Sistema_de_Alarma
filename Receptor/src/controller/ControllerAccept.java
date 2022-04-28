package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import connection.Filter;
import model.Event;
import model.Receptor;
import view.VAccept;

public class ControllerAccept implements ActionListener, WindowListener{
	private VAccept viewAccept = null;
	private ArrayList<Event> acceptedTypes = new ArrayList<Event>();
	
	public ControllerAccept()
	{
		this.viewAccept = new VAccept();
		this.viewAccept.addActionListener(this);
		this.viewAccept.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		Filter f = Receptor.getInstance().getFilter();
		
		//if(f.getAccept().contains())
		//viewAccept.check();
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
	
}
