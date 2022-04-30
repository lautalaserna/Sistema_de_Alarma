package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import connection.Connection;
import connection.TimeOut;
import model.Emisor;
import model.Event;
import model.MsgFactory;
import view.VConfirmation;

public class ControllerConfirmation implements ActionListener {
	private VConfirmation viewConfirmation = null;
	private Event event;
	private ControllerEmisor ce;
	
	public ControllerConfirmation(ControllerEmisor ce, Event event)
	{
		this.event = event;
		this.ce = ce;
		this.viewConfirmation = new VConfirmation();
		this.viewConfirmation.addActionListener(this);
		this.viewConfirmation.addActionListener(ce);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.viewConfirmation.setVisible(false);
		
		if (e.getActionCommand().equals("ACCEPT")) {
			Connection c;
			try {
				TimeOut timeOut = new TimeOut();
				ce.addObservable(timeOut);
				
				c = new Connection(timeOut);
				ce.addObservable(c);
				
				c.connect(MsgFactory.getMessage(Emisor.getInstance().getLocation(), event), Emisor.getInstance().getLocation().getPort());
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
