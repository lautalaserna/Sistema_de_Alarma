package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerEmisor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;

public class VEmisor extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnFocoIncendio;
	private JButton btnAsistenciaMedica;
	private JButton btnPersonalSeguridad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VEmisor frame = new VEmisor();
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
	public VEmisor() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sistema de Alarma");
		setBounds(800, 400, 450, 280);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 72, 416, 120);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
		
		btnFocoIncendio = new JButton("");
		btnFocoIncendio.setIcon(new ImageIcon(VEmisor.class.getResource("/img/fuego.png")));
		btnFocoIncendio.setSelectedIcon(null);
		btnFocoIncendio.setPreferredSize(new Dimension(80,80));
		panel_1.add(btnFocoIncendio);
		btnFocoIncendio.addActionListener(this);
		btnFocoIncendio.setActionCommand("Foco de Incendio");
		
		btnAsistenciaMedica = new JButton("");
		btnAsistenciaMedica.setIcon(new ImageIcon(VEmisor.class.getResource("/img/hospital.png")));
		btnAsistenciaMedica.setPreferredSize(new Dimension(80,80));
		panel_1.add(btnAsistenciaMedica);
		btnAsistenciaMedica.addActionListener(this);
		btnAsistenciaMedica.setActionCommand("Asistencia Medica");
		
		btnPersonalSeguridad = new JButton("");
		btnPersonalSeguridad.setIcon(new ImageIcon(VEmisor.class.getResource("/img/policia.png")));
		btnPersonalSeguridad.setPreferredSize(new Dimension(80,80));
		panel_1.add(btnPersonalSeguridad);
		btnPersonalSeguridad.addActionListener(this);
		btnPersonalSeguridad.setActionCommand("Personal de Seguridad");
		
		JLabel lblNewLabel = new JLabel("Indique su emergencia");
		lblNewLabel.setBounds(10, 26, 416, 36);
		contentPane.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Esperando Confirmacion...");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("MS Reference Sans Serif", Font.ITALIC, 14));
		lblNewLabel_1.setBounds(10, 194, 416, 22);
		contentPane.add(lblNewLabel_1);
		
		this.setVisible(true);
	}

	public void addActionListener(ControllerEmisor controller) {
		this.btnFocoIncendio.addActionListener(controller);
		this.btnAsistenciaMedica.addActionListener(controller);
		this.btnPersonalSeguridad.addActionListener(controller);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
