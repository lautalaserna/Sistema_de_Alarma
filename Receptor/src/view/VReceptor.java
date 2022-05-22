package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerReceptor;

public class VReceptor extends JFrame{

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
		JPanel PanelPrincipal = new JPanel();
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
				"Ubicaci\u00F3n", "Fecha", "Tipo de Emergencia", "Estado"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		scrollPane.setViewportView(table);
		
		this.setVisible(true); //Para que cuandos se cree, sea visible.
	}

	public void addActionListener(ControllerReceptor controllerReceptor) {
		this.btnConfirmar.addActionListener(controllerReceptor);
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public void enableBtn() {
		this.btnConfirmar.setEnabled(true);
	}
	
	public void disableBtn() {
		this.btnConfirmar.setEnabled(false);
	}
}
