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

public class VServer extends JFrame {
	private JPanel contentPane;
	private JLabel lblRegistro;
	private JScrollPane scrollPane;
	private DefaultListModel<String> modelEvent = new DefaultListModel<String>();
	private JTextPane listReceptores;
	private JScrollPane scrollPane_1;
	private JLabel lblReceptores;
	private JPanel panel;
	private JPanel panel_1;
	private JTextPane list;

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
		setBounds(400, 300, 1300, 500);
		
		setResizable(false);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setSize(900, 600);
		setContentPane(this.contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {950, 10, 300};
		gbl_contentPane.rowHeights = new int[] {50, 400};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		this.lblRegistro = new JLabel("Registro de eventos");
		this.lblRegistro.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		this.lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblRegistro = new GridBagConstraints();
		gbc_lblRegistro.fill = GridBagConstraints.BOTH;
		gbc_lblRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegistro.gridx = 0;
		gbc_lblRegistro.gridy = 0;
		this.contentPane.add(this.lblRegistro, gbc_lblRegistro);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		lblReceptores = new JLabel("Receptores Disponibles");
		lblReceptores.setHorizontalAlignment(SwingConstants.CENTER);
		lblReceptores.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		GridBagConstraints gbc_lblReceptores = new GridBagConstraints();
		gbc_lblReceptores.fill = GridBagConstraints.BOTH;
		gbc_lblReceptores.insets = new Insets(0, 0, 5, 0);
		gbc_lblReceptores.gridx = 2;
		gbc_lblReceptores.gridy = 0;
		contentPane.add(lblReceptores, gbc_lblReceptores);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);
		
		list = new JTextPane();
		list.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		scrollPane_1.setViewportView(list);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		this.contentPane.add(this.scrollPane, gbc_scrollPane);
		
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
		list.setText(str);
	}
	
	public void refreshReceptores(ArrayList<ReceptorData> receptores) {
		String str = "";
		for(ReceptorData rd : receptores) {
			str += rd.getInfo() + "\n";
		}
		listReceptores.setText(str);
	}
}
