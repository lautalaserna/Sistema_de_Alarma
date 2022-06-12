package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import connection.ReceptorData;
import model.Servidor;
import java.awt.Color;

public class VServer extends JFrame {
	private DefaultListModel<String> modelEvent = new DefaultListModel<String>();
	private JTextPane listReceptores;
	private JTextPane listLogs;
	private JLabel lblServidor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VServer frame = new VServer();
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
	public VServer() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 300, 1300, 500);
		
		setResizable(false);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setSize(900, 600);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {990, 10, 250};
		gbl_contentPane.rowHeights = new int[] {40, 400};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		contentPane.add(panel_2, gbc_panel_2);
		
		JLabel lblRegistro = new JLabel("Registro de Eventos");
		lblRegistro.setBounds(277, -1, 254, 35);
		panel_2.add(lblRegistro);
		lblRegistro.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblServidor = new JLabel("");
		lblServidor.setForeground(Color.GRAY);
		lblServidor.setHorizontalAlignment(SwingConstants.LEFT);
		lblServidor.setFont(new Font("MS Reference Sans Serif", Font.ITALIC, 12));
		lblServidor.setBounds(0, 0, 130, 35);
		panel_2.add(lblServidor);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		JLabel lblReceptores = new JLabel("Receptores");
		lblReceptores.setHorizontalAlignment(SwingConstants.CENTER);
		lblReceptores.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		GridBagConstraints gbc_lblReceptores = new GridBagConstraints();
		gbc_lblReceptores.fill = GridBagConstraints.BOTH;
		gbc_lblReceptores.insets = new Insets(0, 0, 5, 0);
		gbc_lblReceptores.gridx = 2;
		gbc_lblReceptores.gridy = 0;
		contentPane.add(lblReceptores, gbc_lblReceptores);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);
		
		listLogs = new JTextPane();
		listLogs.setEditable(false);
		listLogs.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		scrollPane_1.setViewportView(listLogs);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		listReceptores = new JTextPane();
		listReceptores.setEditable(false);
		listReceptores.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		scrollPane.setViewportView(listReceptores);
		
		this.setVisible(true);
		refreshLogs(Servidor.getInstance().getLogs());
		refreshReceptores(Servidor.getInstance().getReceptors());
	}

	public void refreshLogs(ArrayList<String> logs) {
		String str = "";
		for (String log : logs) {
			str += log + "\n";
		}
		listLogs.setText(str);
	}
	
	public void refreshReceptores(ArrayList<ReceptorData> receptores) {
		String str = "";
		for(ReceptorData rd : receptores) {
			str += rd.getInfo() + "\n";
		}
		listReceptores.setText(str);
	}
	
	public void setLblServidor(String str) {
		this.lblServidor.setText(str);
	}
}
