package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.SocketException;
import java.net.UnknownHostException;

import connection.Connection;
import model.Emisor;
import model.Event;
import view.VConfirmation;
import view.VLocation;

public class ControllerConfirmation implements ActionListener {
	
	private VConfirmation viewConfirmation = null;
	private Event event;
	
	public ControllerConfirmation(ActionListener ce, Event event)
	{
		this.event = event;
		this.viewConfirmation = new VConfirmation();
		this.viewConfirmation.addActionListener(this);
		this.viewConfirmation.addActionListener(ce);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ACCEPT")) {
			System.out.println("Sending");
			Connection c;
			try {
				c = new Connection();
				c.sendMsg();
			} catch (SocketException ex) {
				ex.printStackTrace();
			} catch (UnknownHostException ex) {
				ex.printStackTrace();
			}
		}
		this.viewConfirmation.setVisible(false); //Esto lo hace siempre.
		
	}

}
