package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import model.Emisor;
import model.Location;
import view.VLocation;

public class ControllerLocation implements ActionListener, WindowListener{
	
	private VLocation viewLocation = null;
	
	public ControllerLocation()
	{
		this.viewLocation = new VLocation();
		this.viewLocation.addActionListener(this);
		this.viewLocation.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		//ControllerEmisor controllerEmisor = new ControllerEmisor();
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
		try {
			Location loc = new Location(this.viewLocation.getLocationText(), this.viewLocation.getPortText());
			Emisor.getInstance().setLocation(loc);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.viewLocation.setVisible(false);
		ControllerEmisor controllerEmisor = new ControllerEmisor();
		
		//FALTA LO DEL ARCHIVO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! (XML)
	}

}
