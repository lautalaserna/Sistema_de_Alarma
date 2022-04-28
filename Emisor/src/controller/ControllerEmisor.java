package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import model.*;
import view.VEmisor;

public class ControllerEmisor implements ActionListener {

	private VEmisor viewEmisor = null;
	
	public ControllerEmisor()
	{
		this.viewEmisor = new VEmisor();
		this.viewEmisor.addActionListener(this);
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
