package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import connection.Connection;
import connection.Filter;
import model.Receptor;
import persistence.Persistence;
import view.VAccept;

public class ControllerAccept implements ActionListener, WindowListener {
	private VAccept viewAccept = null;

	public ControllerAccept() {
		this.viewAccept = new VAccept();
		this.viewAccept.addActionListener(this);
		this.viewAccept.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Filter f = new Filter(viewAccept.isAMSelected(), viewAccept.isFISelected(), viewAccept.isPSSelected(),getPort());
		Receptor.getInstance().setFilter(f);
		try {
			Persistence.setFilterToBin(f);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.viewAccept.setVisible(false);
		
		Connection c = new Connection(f, viewAccept.getPort());
		new ControllerReceptor(c);
	}

	private int getPort() {
		return this.viewAccept.getPort();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		Filter f = Receptor.getInstance().getFilter();

		if (f.isAcceptAM())
			this.viewAccept.selectAM();

		if (f.isAcceptFI())
			this.viewAccept.selectFI();

		if (f.isAcceptPS())
			this.viewAccept.selectPS();

		this.viewAccept.setPort(Receptor.getInstance().getFilter().getPort());

		this.viewAccept.check();
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

}
