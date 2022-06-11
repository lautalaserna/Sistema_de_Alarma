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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Servidor;

import javax.swing.JTextPane;

public class VServer extends JFrame {
	private JPanel contentPane;
	private JLabel lblRegistro;
	private JScrollPane scrollPane;
	private DefaultListModel<String> modelEvent = new DefaultListModel<String>();
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
		setBounds(600, 300, 900, 500);
		
		setResizable(false);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setSize(900, 600);
		setContentPane(this.contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {890};
		gbl_contentPane.rowHeights = new int[] {40, 410, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		this.contentPane.setLayout(gbl_contentPane);
		
		this.lblRegistro = new JLabel("Registro de eventos");
		this.lblRegistro.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		this.lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblRegistro = new GridBagConstraints();
		gbc_lblRegistro.fill = GridBagConstraints.BOTH;
		gbc_lblRegistro.insets = new Insets(0, 0, 5, 0);
		gbc_lblRegistro.gridx = 0;
		gbc_lblRegistro.gridy = 0;
		this.contentPane.add(this.lblRegistro, gbc_lblRegistro);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		this.contentPane.add(this.scrollPane, gbc_scrollPane);
		
		list = new JTextPane();
		list.setEditable(false);
		list.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		scrollPane.setViewportView(list);
		
		this.setVisible(true);
		refreshList(Servidor.getInstance().getLogs());
	}

	public void refreshList(ArrayList<String> logs) {
		String str = "";
		for (String log : logs) {
			str += log + "\n";
		}
		list.setText(str);
	}
}
