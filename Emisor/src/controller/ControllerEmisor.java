package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.EventAsistenciaMedica;
import model.EventFocoIncendio;
import model.EventPersonalSeguridad;
import view.VEmisor;

public class ControllerEmisor implements ActionListener, Observer {
	private ArrayList<Observable> obs = new ArrayList<Observable>();
	private VEmisor viewEmisor = null;

	public ControllerEmisor() {
		this.viewEmisor = new VEmisor();
		this.viewEmisor.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("FI")) {
			this.viewEmisor.setEnabled(false);
			new ControllerConfirmation(this, new EventFocoIncendio());
		} else if (e.getActionCommand().equals("AM")) {
			this.viewEmisor.setEnabled(false);
			new ControllerConfirmation(this, new EventAsistenciaMedica());
		} else if (e.getActionCommand().equals("PS")) {
			this.viewEmisor.setEnabled(false);
			new ControllerConfirmation(this, new EventPersonalSeguridad());
		} else if (e.getActionCommand().equals("ACCEPT")) {
			this.viewEmisor.setEnabled(true);
			this.viewEmisor.setLblConfirm("WAIT");
			this.viewEmisor.disableBtns();
		} else if (e.getActionCommand().equals("CANCEL")) {
			this.viewEmisor.setEnabled(true);
		}
	}
	
	public void enableBtns() {
		this.viewEmisor.enableBtns();
	}
	
	public void setConfirmation(String str) {
		this.viewEmisor.setLblConfirm(str);
	}

	public void addObservable(Observable obs) {
		this.obs.add(obs);
		obs.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o.getClass().getName().equalsIgnoreCase("connection.Connection")) {
			setConfirmation((String) arg);
			
		} else if (o.getClass().getName().equalsIgnoreCase("connection.TimeOut")) {
			setConfirmation("IGNORED");
		}
		enableBtns();
	}

}
