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
			this.viewEmisor.setEnabled(false);
			ControllerConfirmation cc = new ControllerConfirmation(this, new EventFocoIncendio());
		}
		else if (e.getActionCommand().equals("AM")){
			this.viewEmisor.setEnabled(false);
			ControllerConfirmation cc = new ControllerConfirmation(this, new EventAsistenciaMedica());
		}
		else if (e.getActionCommand().equals("PS")){ 
			this.viewEmisor.setEnabled(false);
			ControllerConfirmation cc = new ControllerConfirmation(this, new EventPersonalSeguridad());
		} else if (e.getActionCommand().equals("ACCEPT")){ 
			this.viewEmisor.setEnabled(true);
			this.viewEmisor.setLblConfirm("WAIT");
		}  else if(e.getActionCommand().equals("CANCEL")) {
			this.viewEmisor.setEnabled(true);
		}
	}
	
	public void setConfirmation(String str) {
		this.viewEmisor.setLblConfirm(str);
	}

}
