package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class VAccept extends JFrame implements ActionListener, KeyListener, MouseListener {

	private JPanel contentPane;
	private JTextField textFieldPort;
	private JButton btnConfirmar;
	private JRadioButton radioBtnFI;
	private JRadioButton radioBtnAM;
	private JRadioButton radioBtnPS;
	
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
		setBounds(800, 400, 500, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("  Indique Alertas a recibir:");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setPreferredSize(new Dimension(100,30));
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));
		
		radioBtnAM = new JRadioButton("Alerta medica");
		radioBtnAM.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		radioBtnAM.addMouseListener(this);
		panel.add(radioBtnAM);
		
		radioBtnPS = new JRadioButton("Alerta seguridad");
		radioBtnPS.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		radioBtnPS.addMouseListener(this);
		panel.add(radioBtnPS);
		
		radioBtnFI = new JRadioButton("Alerta incendio");
		radioBtnFI.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		radioBtnFI.addMouseListener(this);
		panel.add(radioBtnFI);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.LEADING);
		panel_1.add(panel_2);
		
		JLabel lblPort = new JLabel("  Puerto:");
		lblPort.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_2.add(lblPort);
		
		textFieldPort = new JTextField();
		panel_2.add(textFieldPort);
		textFieldPort.setColumns(15);
		textFieldPort.setPreferredSize(new Dimension(0,26));
		textFieldPort.addKeyListener(this);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(this);
		btnConfirmar.setActionCommand("Confirmar tipos");
		btnConfirmar.setPreferredSize(new Dimension(120, 26));
		btnConfirmar.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		btnConfirmar.setPreferredSize(new Dimension(120,30));
		panel_3.add(btnConfirmar);
		
		this.setVisible(true); //Para que cuandos se cree, sea visible.
	}

	public void addActionListener(ControllerAccept controllerAccept) {
		this.btnConfirmar.addActionListener(controllerAccept);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) { //Si hay algo en los textFields
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
		if (!this.textFieldPort.getText().isEmpty() && (isFISelected() || isAMSelected() || isPSSelected()))
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		check();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
