package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import connection.Connection;
import connection.ServerData;
import model.Filter;
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
			Persistence.setFilterToBin("data/filter.bin",f);			
			
			ServerData svd = new ServerData(InetAddress.getByName(viewAccept.getSvIP()),viewAccept.getSvPort());
			Persistence.setServerDataToBin("data/server.bin",svd);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.viewAccept.setVisible(false);
		
		Connection c = new Connection(f, viewAccept.getPort());
		try {
			c.suscribe(InetAddress.getByName(viewAccept.getSvIP()), viewAccept.getSvPort());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		new ControllerReceptor(c);
	}

	private int getPort() {
		return this.viewAccept.getPort();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		Filter f = Receptor.getInstance().getFilter();
		ServerData svd = Receptor.getInstance().getServerData();

		if (f.isAcceptAM())
			this.viewAccept.selectAM();

		if (f.isAcceptFI())
			this.viewAccept.selectFI();

		if (f.isAcceptPS())
			this.viewAccept.selectPS();

		this.viewAccept.setPort(Receptor.getInstance().getFilter().getPort());

		this.viewAccept.setSvIP(svd.getAddress().getHostAddress());
		
		this.viewAccept.setSvPort(svd.getPort());
		
		this.viewAccept.check();
	}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

}
