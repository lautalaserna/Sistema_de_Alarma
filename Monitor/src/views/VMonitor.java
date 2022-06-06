package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import java.awt.Color;

public class VMonitor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VMonitor frame = new VMonitor();
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
	public VMonitor() {
		setTitle("Monitor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_Encabezado = new JPanel();
		panel_Encabezado.setBounds(0, 0, 354, 42);
		contentPane.add(panel_Encabezado);
		panel_Encabezado.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		
		JLabel lblEncabezado = new JLabel("Estados de los Servidores");
		lblEncabezado.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_Encabezado.add(lblEncabezado);
		
		JPanel panel_Cuerpo = new JPanel();
		panel_Cuerpo.setBounds(0, 42, 354, 128);
		contentPane.add(panel_Cuerpo);
		panel_Cuerpo.setLayout(null);
		
		JPanel panel_ServerPrincipal = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_ServerPrincipal.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setHgap(20);
		flowLayout.setVgap(20);
		panel_ServerPrincipal.setBounds(0, 0, 354, 61);
		panel_Cuerpo.add(panel_ServerPrincipal);
		
		JLabel lblServidorPrincipal = new JLabel("Servidor Principal:");
		lblServidorPrincipal.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_ServerPrincipal.add(lblServidorPrincipal);
		
		JLabel lblEstadoPrincipal = new JLabel("Activo");
		lblEstadoPrincipal.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
		lblEstadoPrincipal.setBackground(Color.WHITE);
		lblEstadoPrincipal.setForeground(Color.BLACK);
		panel_ServerPrincipal.add(lblEstadoPrincipal);
		
		JPanel panel_ServerSecundario = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_ServerSecundario.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setVgap(20);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_ServerSecundario.setBounds(0, 61, 354, 67);
		panel_Cuerpo.add(panel_ServerSecundario);
		
		JLabel lblServidorSecundario = new JLabel("Servidor Secundario:");
		lblServidorSecundario.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_ServerSecundario.add(lblServidorSecundario);
		
		JLabel lblEstadoSecundario = new JLabel("Activo");
		lblEstadoSecundario.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
		panel_ServerSecundario.add(lblEstadoSecundario);
		
		JPanel panel_Aviso = new JPanel();
		panel_Aviso.setBounds(0, 170, 354, 27);
		contentPane.add(panel_Aviso);
		
		JLabel lblAviso = new JLabel("Inicie un Servidor...");
		lblAviso.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
		lblAviso.setForeground(Color.GRAY);
		panel_Aviso.add(lblAviso);
	}
}
