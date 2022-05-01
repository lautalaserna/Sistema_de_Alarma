package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import model.Emisor;
import model.Location;
import persistence.Persistence;
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
		viewLocation.setLocationText(Emisor.getInstance().getLocation().getName());
		viewLocation.setIPText(Emisor.getInstance().getLocation().getIp());
		viewLocation.setPortText(Emisor.getInstance().getLocation().getPort());
		viewLocation.checkText();
	}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Location loc = new Location(this.viewLocation.getLocationText(), Integer.parseInt(this.viewLocation.getPortText()));
			Emisor.getInstance().setLocation(loc);
			Persistence.setLocationToBin(loc);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.viewLocation.setVisible(false);
		new ControllerEmisor();
	}

}
