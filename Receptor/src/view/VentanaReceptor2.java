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

public class VentanaReceptor2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaReceptor2 frame = new VentanaReceptor2();
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
	public VentanaReceptor2() {
		setResizable(false);
		setTitle("Sistema de Alarma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Tipos de alertas a recibir");
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Alerta medica");
		rdbtnNewRadioButton.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Alerta seguridad");
		rdbtnNewRadioButton_1.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
		panel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Alerta incendio");
		rdbtnNewRadioButton_2.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
		panel.add(rdbtnNewRadioButton_2);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.LEADING);
		panel_1.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Puerto:");
		lblNewLabel_1.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(15);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.EAST);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setPreferredSize(new Dimension(120, 30));
		btnNewButton.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_3.add(btnNewButton);
	}

}
