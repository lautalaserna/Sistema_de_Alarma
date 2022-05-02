package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VConfirmation extends JFrame {
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JLabel lblConfirmation;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VConfirmation frame = new VConfirmation();
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
	public VConfirmation() {
		setTitle("Confirmación");
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 450, 450, 150);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {420, 0};
		gbl_contentPane.rowHeights = new int[] {55, 50, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblConfirmation = new JLabel("¿Desea enviar la solicitud?");
		lblConfirmation.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblConfirmation, gbc_lblNewLabel);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(30);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel.add(btnConfirmar);
		btnConfirmar.setPreferredSize(new Dimension(110,30));
		btnConfirmar.setActionCommand("ACCEPT");
		
		btnCancelar = new JButton(" Cancelar ");
		btnCancelar.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel.add(btnCancelar);
		btnCancelar.setPreferredSize(new Dimension(110,30));
		btnCancelar.setActionCommand("CANCEL");
		
		this.setVisible(true);
	}

	public void addActionListener(ActionListener controller) {
		this.btnConfirmar.addActionListener(controller);
		this.btnCancelar.addActionListener(controller);
	}
	
	public void setLblConfirmation(String str) {
		this.lblConfirmation.setText("¿Desea enviar una solicitud de " + str + "?");
	}

}
