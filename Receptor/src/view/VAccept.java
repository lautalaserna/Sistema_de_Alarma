package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ControllerAccept;

public class VAccept extends JFrame implements KeyListener, MouseListener {
	private JRadioButton radioBtnFI;
	private JRadioButton radioBtnAM;
	private JRadioButton radioBtnPS;
	private JButton btnConfirmar;
	private JTextField textFieldPort;
	private JTextField textFieldSvIP;
	private JTextField textFieldSvPort;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					VAccept frame = new VAccept();
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
	public VAccept() {
		setResizable(false);
		setTitle("Sistema de Alarma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 400, 500, 350);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("  Indique Alertas a recibir:");
		lblNewLabel.setBounds(0, 30, 476, 30);
		panel_1.add(lblNewLabel);
		lblNewLabel.setPreferredSize(new Dimension(100,30));
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		radioBtnAM = new JRadioButton("Alerta medica");
		radioBtnAM.setBounds(20, 16, 125, 27);
		radioBtnAM.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		radioBtnAM.addMouseListener(this);
		panel.setLayout(null);
		panel.add(radioBtnAM);
		
		radioBtnPS = new JRadioButton("Alerta seguridad");
		radioBtnPS.setBounds(159, 16, 143, 27);
		radioBtnPS.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		radioBtnPS.addMouseListener(this);
		panel.add(radioBtnPS);
		
		radioBtnFI = new JRadioButton("Alerta incendio");
		radioBtnFI.setBounds(322, 16, 133, 27);
		radioBtnFI.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		radioBtnFI.addMouseListener(this);
		panel.add(radioBtnFI);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblIpservidor_2 = new JLabel("  IP (Sv):");
		lblIpservidor_2.setBounds(20, 10, 121, 50);
		lblIpservidor_2.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_4.add(lblIpservidor_2);
		
		textFieldSvIP = new JTextField();
		textFieldSvIP.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textFieldSvIP.setBounds(145, 25, 304, 26);
		textFieldSvIP.setPreferredSize(new Dimension(0, 26));
		textFieldSvIP.setColumns(15);
		textFieldSvIP.addKeyListener(this);
		panel_4.add(textFieldSvIP);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblIpservidor_1_1 = new JLabel("  Puerto (Sv):");
		lblIpservidor_1_1.setBounds(20, 10, 142, 41);
		lblIpservidor_1_1.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_5.add(lblIpservidor_1_1);
		
		textFieldSvPort = new JTextField();
		textFieldSvPort.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textFieldSvPort.setBounds(145, 20, 304, 26);
		textFieldSvPort.setPreferredSize(new Dimension(0, 26));
		textFieldSvPort.setColumns(15);
		textFieldSvPort.addKeyListener(this);
		panel_5.add(textFieldSvPort);
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblPort = new JLabel("  Puerto:");
		lblPort.setBounds(20, 10, 115, 27);
		lblPort.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_7.add(lblPort);
		
		textFieldPort = new JTextField();
		textFieldPort.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textFieldPort.setBounds(145, 12, 141, 27);
		textFieldPort.setPreferredSize(new Dimension(0, 26));
		textFieldPort.setColumns(15);
		textFieldPort.setPreferredSize(new Dimension(0,28));
		textFieldPort.addKeyListener(this);
		panel_7.add(textFieldPort);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setPreferredSize(new Dimension(120, 26));
		btnConfirmar.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		btnConfirmar.setActionCommand("Confirmar tipos");
		btnConfirmar.setBounds(306, 8, 141, 30);
		panel_7.add(btnConfirmar);
		
		this.setVisible(true); //Para que cuandos se cree, sea visible.
	}

	public void addActionListener(ControllerAccept controllerAccept) {
		this.btnConfirmar.addActionListener(controllerAccept);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		check();
	}
	
	public boolean isPSSelected() {
		return this.radioBtnPS.isSelected();
	}
	
	public boolean isAMSelected() {
		return this.radioBtnAM.isSelected();
	}
	
	public boolean isFISelected() {
		return this.radioBtnFI.isSelected();
	}
	
	public void selectPS() {
		this.radioBtnPS.setSelected(true);
	}
	
	public void selectAM() {
		this.radioBtnAM.setSelected(true);
	}
	
	public void selectFI() {
		this.radioBtnFI.setSelected(true);
	}
	
	public void check() {
		if (!this.textFieldPort.getText().isEmpty() && (isFISelected() || isAMSelected() || isPSSelected()) && !this.textFieldSvIP.getText().isEmpty() && !this.textFieldSvPort.getText().isEmpty())
			btnConfirmar.setEnabled(true);
		else 
			btnConfirmar.setEnabled(false);
	}

	public void setPort(int port) {
		this.textFieldPort.setText(Integer.toString(port));
	}
	
	public int getPort() {
		return Integer.parseInt(this.textFieldPort.getText());
	}
	
	public void setSvPort(int port) {
		this.textFieldSvPort.setText(Integer.toString(port));
	}
	
	public int getSvPort() {
		return Integer.parseInt(this.textFieldSvPort.getText());
	}
	
	public void setSvIP(String ip) {
		this.textFieldSvIP.setText(ip);
	}
	
	public String getSvIP() {
		return this.textFieldSvIP.getText();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		check();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
