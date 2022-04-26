package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.GridLayout;

public class VAccept extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Alerta medica");
		rdbtnNewRadioButton.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Alerta seguridad");
		rdbtnNewRadioButton_1.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Alerta incendio");
		rdbtnNewRadioButton_2.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel.add(rdbtnNewRadioButton_2);
		
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
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(15);
		textField.setPreferredSize(new Dimension(0,26));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setPreferredSize(new Dimension(120, 26));
		btnNewButton.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		btnNewButton.setPreferredSize(new Dimension(120,30));
		panel_3.add(btnNewButton);
	}

}
