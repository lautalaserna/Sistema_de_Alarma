package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ControllerEmisor;

public class VEmisor extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnFocoIncendio;
	private JButton btnAsistenciaMedica;
	private JButton btnPersonalSeguridad;
	private JLabel lblConfirm;
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
		btnFocoIncendio.setActionCommand("FI");
		
		btnAsistenciaMedica = new JButton("");
		btnAsistenciaMedica.setIcon(new ImageIcon(VEmisor.class.getResource("/img/hospital.png")));
		btnAsistenciaMedica.setPreferredSize(new Dimension(80,80));
		panel_1.add(btnAsistenciaMedica);
		btnAsistenciaMedica.addActionListener(this);
		btnAsistenciaMedica.setActionCommand("AM");
		
		btnPersonalSeguridad = new JButton("");
		btnPersonalSeguridad.setIcon(new ImageIcon(VEmisor.class.getResource("/img/policia.png")));
		btnPersonalSeguridad.setPreferredSize(new Dimension(80,80));
		panel_1.add(btnPersonalSeguridad);
		btnPersonalSeguridad.addActionListener(this);
		btnPersonalSeguridad.setActionCommand("PS");
		
		JLabel lblNewLabel = new JLabel("Indique su emergencia");
		lblNewLabel.setBounds(10, 26, 416, 36);
		contentPane.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 16));
		
		lblConfirm = new JLabel("");
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setFont(new Font("MS Reference Sans Serif", Font.ITALIC, 14));
		lblConfirm.setBounds(10, 194, 416, 22);
		contentPane.add(lblConfirm);
		
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
	
	public void enableBtns() {
		this.btnAsistenciaMedica.setEnabled(true);
		this.btnFocoIncendio.setEnabled(true);
		this.btnPersonalSeguridad.setEnabled(true);
	}
	
	public void disableBtns() {
		this.btnAsistenciaMedica.setEnabled(false);
		this.btnFocoIncendio.setEnabled(false);
		this.btnPersonalSeguridad.setEnabled(false);
	}
	
	public void setLblConfirm(String str) {
		if("WAIT".equals(str)) {
			this.lblConfirm.setText("Esperando Confirmación...");
		} else if("OK".equals(str)) {
			this.lblConfirm.setText("Mensaje recibido exitosamente!");
		} else if("KO".equals(str)) {
			this.lblConfirm.setText("Mensaje no recibido.");
		} else if("IGNORED".equals(str)) {
			this.lblConfirm.setText("No hay Receptores activos para esta Emergencia.");
		}
	}

}
