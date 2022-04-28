package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import view.VReceptor;

public class ControllerReceptor implements ActionListener, WindowListener{

	private VReceptor viewReceptor = null;
	
	public ControllerReceptor()
	{
		this.viewReceptor = new VReceptor();
		this.viewReceptor.addActionListener(this);
		this.viewReceptor.addWindowListener(this);
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
		//Cuando le llegue el "Confirmar evento", Va a hacer que se envíe la confirmación al Emisor.
		//También va a triggerear el timer para que corte la alarma (¿y actualice el registro?).
		
	}
}
