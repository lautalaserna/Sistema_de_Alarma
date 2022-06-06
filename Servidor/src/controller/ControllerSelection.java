package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import connection.Connection;
import view.VSelection;
import view.VServer;

public class ControllerSelection implements ActionListener{
	private VSelection viewSelection = null;
	private Connection connection;
	
	public ControllerSelection(Connection connection) {
		this.viewSelection = new VSelection();
		this.viewSelection.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Confirmar")) {
			if (this.viewSelection.getRdbtnPrimario().isSelected()) {
				//Si fue seleccionado como Servidor primario.
			}
			else if (this.viewSelection.getRdbtnSecundario().isSelected()) {
				//Si fue seleccionado como Servidor secundario.
			}
		}
		this.viewSelection.setVisible(false);
		new ControllerServer(connection);
	}

}
