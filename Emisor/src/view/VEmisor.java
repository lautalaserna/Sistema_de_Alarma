package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class VEmisor extends JFrame {

	private JPanel contentPane;

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
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(VEmisor.class.getResource("/img/fuego.png")));
		btnNewButton.setSelectedIcon(null);
		btnNewButton.setPreferredSize(new Dimension(80,80));
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(VEmisor.class.getResource("/img/hospital.png")));
		btnNewButton_1.setPreferredSize(new Dimension(80,80));
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(VEmisor.class.getResource("/img/policia.png")));
		btnNewButton_2.setPreferredSize(new Dimension(80,80));
		panel_1.add(btnNewButton_2);
		
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
	}

}
