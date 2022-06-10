package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import connection.Connection;
import connection.ConnectionAux;
import connection.ConnectionMain;
import view.VSelection;

public class ControllerSelection implements ActionListener{
	private VSelection viewSelection = null;
	
	public ControllerSelection() {
		this.viewSelection = new VSelection();
		this.viewSelection.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection conn = null;
		try {
			if (e.getActionCommand().equals("Confirmar")) {
				if (this.viewSelection.getRdbtnPrimario().isSelected()) {
					conn = new Connection(new ConnectionMain());
				}
				else if (this.viewSelection.getRdbtnSecundario().isSelected()) {
					conn = new Connection(new ConnectionAux());
				}
			}
			this.viewSelection.setVisible(false);
		} catch (Exception ex) {
			System.out.println("Error al crear las conexiones");
		}
		new ControllerServer(conn);
	}
	
	
	
}
