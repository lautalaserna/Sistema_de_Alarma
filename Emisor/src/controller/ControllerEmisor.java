package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import model.*;
import view.VEmisor;

public class ControllerEmisor implements ActionListener, WindowListener{

	private VEmisor viewEmisor = null;
	
	public ControllerEmisor()
	{
		this.viewEmisor = new VEmisor();
		this.viewEmisor.addActionListener(this);
		this.viewEmisor.addWindowListener(this);
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
		if(e.getActionCommand().equals("FI")){
			ControllerConfirmation cc = new ControllerConfirmation(this, new EventFocoIncendio());
		}
		else if (e.getActionCommand().equals("AM")){
			ControllerConfirmation cc = new ControllerConfirmation(this, new EventAsistenciaMedica());
		}
		else if (e.getActionCommand().equals("PS")){ 
			ControllerConfirmation cc = new ControllerConfirmation(this, new EventPersonalSeguridad());
		} else if (e.getActionCommand().equals("ACCEPT")){ 
			this.viewEmisor.setLblConfirm("WAIT");
		} 
		
	}

}
