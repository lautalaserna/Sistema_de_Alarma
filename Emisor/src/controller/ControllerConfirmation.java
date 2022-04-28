package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
			// Emisor.getInstance().sendMsg(this.event);
		}
		this.viewConfirmation.setVisible(false); //Esto lo hace siempre.
		
	}

}
