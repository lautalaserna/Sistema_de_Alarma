package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerLocation;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class VLocation extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	private JTextField textFieldLocation;
	private JTextField textFieldIP;
	private JTextField textFieldPort;
	private JButton btnConfirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VLocation frame = new VLocation();
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
	public VLocation() {
		setTitle("Configurar Ubicación");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 400, 450, 250);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {100, 300};
		gbl_panel.rowHeights = new int[] {0, 0, 0, 50};
		gbl_panel.columnWeights = new double[]{0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setVgap(10);
		flowLayout_2.setHgap(15);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panel.add(panel_4, gbc_panel_4);
		
		JLabel lblNewLabel = new JLabel("Ubicacón");
		panel_4.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 6));
		
		textFieldLocation = new JTextField();
		textFieldLocation.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_1.add(textFieldLocation);
		textFieldLocation.setColumns(20);
		textFieldLocation.setPreferredSize(new Dimension(0,28));
		textFieldLocation.addKeyListener(this);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setVgap(10);
		flowLayout_3.setHgap(15);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.anchor = GridBagConstraints.NORTH;
		gbc_panel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 1;
		panel.add(panel_5, gbc_panel_5);
		
		JLabel lblNewLabel_1 = new JLabel("IP");
		panel_5.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(6);
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		
		textFieldIP = new JTextField();
		textFieldIP.setEditable(false);
		textFieldIP.setText("localhost");
		textFieldIP.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_2.add(textFieldIP);
		textFieldIP.setColumns(20);
		textFieldIP.setPreferredSize(new Dimension(0,28));
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_6.getLayout();
		flowLayout_4.setVgap(8);
		flowLayout_4.setHgap(15);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 2;
		panel.add(panel_6, gbc_panel_6);
		
		JLabel lblNewLabel_2 = new JLabel("Puerto");
		panel_6.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(4);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 2;
		panel.add(panel_3, gbc_panel_3);
		
		textFieldPort = new JTextField();
		textFieldPort.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_3.add(textFieldPort);
		textFieldPort.setColumns(20);
		textFieldPort.setPreferredSize(new Dimension(0,28));
		textFieldPort.addKeyListener(this);
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 1;
		gbc_panel_7.gridy = 3;
		panel.add(panel_7, gbc_panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.RIGHT, 32, 10));
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setEnabled(false);
		btnConfirmar.addActionListener(this);
		btnConfirmar.setActionCommand("Confirmar ubicacion");
		panel_7.add(btnConfirmar);
		btnConfirmar.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		btnConfirmar.setPreferredSize(new Dimension(120,30));
		
		checkText();
	}

	public void addActionListener(ControllerLocation controller) {
		this.btnConfirmar.addActionListener(controller);
	}

	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) { //Si hay algo en los textFields
		checkText();
	}
	
	public void checkText() {
		if (!this.textFieldLocation.getText().isEmpty() && !this.textFieldPort.getText().isEmpty())
			btnConfirmar.setEnabled(true);
	}
	
	public String getLocationText() {
		return (this.textFieldLocation.getText());
	}
	
	public String getPortText() {
		return (this.textFieldPort.getText());
	}
}
