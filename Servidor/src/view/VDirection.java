package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class VDirection extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VDirection frame = new VDirection();
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
	public VDirection() {
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 400, 400, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Puerto (Receptores):");
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 80, 152, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Puerto (Emisores):");
		lblNewLabel_1.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 24, 152, 50);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textField.setBounds(180, 92, 180, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(180, 36, 180, 27);
		contentPane.add(textField_1);
	}
}
