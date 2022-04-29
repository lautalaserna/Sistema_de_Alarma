package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.SocketException;
import java.util.ArrayList;

import connection.Connection;
import connection.Filter;
import model.Event;
import model.Receptor;
import persistence.Persistence;
import view.VAccept;

public class ControllerAccept implements ActionListener, WindowListener {
	private VAccept viewAccept = null;
	private ArrayList<Event> acceptedTypes = new ArrayList<Event>();

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
			Persistence.setFilterToBin("filter.bin", f);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.viewAccept.setVisible(false);
		
		ControllerReceptor cr = new ControllerReceptor();
		
		Connection c = Connection.getInstace(cr, viewAccept.getPort());
		c.listen();
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
