package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.DefaultTableModel;

import connection.Alarm;
import connection.Connection;
import model.Message;
import model.Receptor;
import view.VReceptor;

public class ControllerReceptor implements ActionListener, WindowListener, MouseListener, Observer {
	private ArrayList<Observable> obs = new ArrayList<Observable>();
	private VReceptor viewReceptor = null;
	private Connection connection;
	private Alarm alarm;

	public ControllerReceptor(Connection connection) {
		this.viewReceptor = new VReceptor();
		this.viewReceptor.addActionListener(this);
		this.viewReceptor.addWindowListener(this);
		this.viewReceptor.getTable().addMouseListener(this);

		this.connection = connection;
		this.connection.listen();

		this.obs.add(Receptor.getInstance());
		Receptor.getInstance().addObserver(this);

		this.obs.add(connection);
		connection.addObserver(this);

		this.alarm = new Alarm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = this.viewReceptor.getTable().getSelectedRow();
		if (index != -1) {
			Message msg = Receptor.getInstance().getReg().get(index);
			msg.setState("Recibido");
			refreshList();
			this.viewReceptor.disableBtn();
			connection.getTimeOuts().get(index).stopTimer();
			connection.response(true, msg.getInetAddress(), msg.getLoc().getPort());
		}
	}

	private void refreshList() {
		ArrayList<Message> reg = Receptor.getInstance().getReg();
		DefaultTableModel dm = (DefaultTableModel) viewReceptor.getTable().getModel();
		dm.getDataVector().removeAllElements();
		dm.fireTableDataChanged();

		for (Message msg : reg) {
			DefaultTableModel model = (DefaultTableModel) viewReceptor.getTable().getModel();
			List<String> list = new ArrayList<String>();
			list.add(msg.getLoc().getName());
			list.add(msg.getDate().getHour() + ":" + msg.getDate().getMinute() + ":" + msg.getDate().getSecond());
			list.add(msg.getEvent().getEventType());
			list.add(msg.getState());

			model.addRow(list.toArray());

			this.viewReceptor.getTable().setModel(model);
		}
		refreshAlarm();
	}

	private void refreshAlarm() {
		if (isActive()) {
			try {
				alarm.loop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			this.alarm.stop();
		}
	}

	private boolean isActive() {
		ArrayList<Message> reg = Receptor.getInstance().getReg();
		for (Message msg : reg) {
			if (msg.getState().equals("Pendiente"))
				return true;
		}
		return false;
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(o.getClass().getName());
		if (o.getClass().getName().equalsIgnoreCase("connection.Connection")) {
			System.out.println("Entro al update por la connection");
			Message msg = (Message) arg;
			msg.setState("Expirado");
			connection.response(false, msg.getInetAddress(), msg.getLoc().getPort());
		}
		refreshList();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int index = this.viewReceptor.getTable().getSelectedRow();
		if (index != -1) {
			Message msg = Receptor.getInstance().getReg().get(index);
			if (msg.getState().equals("Pendiente")) {
				this.viewReceptor.enableBtn();
			} else {
				this.viewReceptor.disableBtn();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
