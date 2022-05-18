package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class VServer extends JFrame {

	private JPanel contentPane;
	private JLabel lblRegistro;
	private JScrollPane scrollPane;
	private JList list;
	private DefaultListModel modelEvent = new DefaultListModel<String>();

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
		setBounds(100, 100, 900, 400);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setSize(900, 600);
		setContentPane(this.contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{776, 0};
		gbl_contentPane.rowHeights = new int[] {40, 410, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
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
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		this.contentPane.add(this.scrollPane, gbc_scrollPane);
		
		this.list = new JList();
		this.list.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
		this.list.setModel(modelEvent);
		this.scrollPane.setViewportView(this.list);
		
		this.setVisible(true);
	}
	
	public void addLog(String log) {
		System.out.println("LLEGÓ AL ADDLOG");
		this.modelEvent.addElement(log);
		this.list.repaint();
	}
}
