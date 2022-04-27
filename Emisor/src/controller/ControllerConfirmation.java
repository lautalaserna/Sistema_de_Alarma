package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import model.Event;
import view.VConfirmation;
import view.VLocation;

public class ControllerConfirmation implements ActionListener, WindowListener{
	
private VConfirmation viewConfirmation = null;
private Event event;
	
	public ControllerConfirmation(Event event)
	{
		this.event = event;
		this.viewConfirmation = new VConfirmation();
		this.viewConfirmation.addActionListener(this);
		this.viewConfirmation.addWindowListener(this);
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
		if (e.getActionCommand().equals("Confirmar solicitud")) {
			//Crea el mensaje con el tipo de evento pasado por parámetro en el constructor.
			//Realiza la comunicación.
		}
		this.viewConfirmation.setVisible(false); //Esto lo hace siempre.
		
	}

}
