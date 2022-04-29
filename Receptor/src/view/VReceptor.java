package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ControllerReceptor;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VReceptor extends JFrame implements ActionListener{

	private JPanel PanelPrincipal;
	private JTable table;
	private JButton btnConfirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VReceptor frame = new VReceptor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		});
	}

	/**
	 * Create the frame.
	 */
	public VReceptor() {
		setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
		setTitle("Sistema de Alarma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 350, 720, 480);
		PanelPrincipal = new JPanel();
		PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelPrincipal);
		PanelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		PanelPrincipal.add(panel, BorderLayout.NORTH);
		
		JLabel Cabecera = new JLabel("Registro de eventos");
		Cabecera.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel.add(Cabecera);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		flowLayout_1.setHgap(20);
		PanelPrincipal.add(panel_2, BorderLayout.SOUTH);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setPreferredSize(new Dimension(120, 30));
		btnConfirmar.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		btnConfirmar.addActionListener(this);
		btnConfirmar.setActionCommand("CONFIRM");
		btnConfirmar.setEnabled(false);
		panel_2.add(btnConfirmar);
		
		JScrollPane scrollPane = new JScrollPane();
		PanelPrincipal.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ubicación", "Fecha", "Tipo de Emergencia", "Estado"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		scrollPane.setViewportView(table);
		
		this.setVisible(true); //Para que cuandos se cree, sea visible.
	}

	public void addActionListener(ControllerReceptor controllerReceptor) {
		this.btnConfirmar.addActionListener(controllerReceptor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public void check() {
		//El botón inicia deshabilitado. Una vez clickea una fila, evalúa:
		//Si el estado de la fila seleccionada (de la tabla) es "Aceptado", debería deshabilitarse.
		//Si es "Expirado" también deshabilitado.
		//Si es "Pendiente", debería habilitarse.
	}

	public void enableBtn() {
		this.btnConfirmar.setEnabled(true);
	}
	
	public void disableBtn() {
		this.btnConfirmar.setEnabled(false);
	}
}
