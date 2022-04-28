package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerAccept;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.GridLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class VAccept extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	private JTextField textFieldPort;
	private JButton btnConfirmar;
	private JRadioButton radioBtnAI;
	private JRadioButton radioBtnAM;
	private JRadioButton radioBtnAS;

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
		setBounds(100, 100, 500, 180);
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
		panel.add(radioBtnAM);
		
		radioBtnAS = new JRadioButton("Alerta seguridad");
		radioBtnAS.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel.add(radioBtnAS);
		
		radioBtnAI = new JRadioButton("Alerta incendio");
		radioBtnAI.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel.add(radioBtnAI);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.LEADING);
		panel_1.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("  Puerto:");
		lblNewLabel_1.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_1);
		
		textFieldPort = new JTextField();
		panel_2.add(textFieldPort);
		textFieldPort.setColumns(15);
		textFieldPort.setPreferredSize(new Dimension(0,26));
		
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
	
	public void check() {
		if (this.textFieldPort.getText().isEmpty() || (this.radioBtnAI.isSelected()==false && this.radioBtnAS.isSelected()==false && this.radioBtnAM.isSelected()==false))
			btnConfirmar.setEnabled(false);
		else 
			btnConfirmar.setEnabled(true);
	}

}
