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
	private JLabel lblEstadoPrincipal;
	private JLabel lblEstadoSecundario;
	private JLabel lblAviso;
	
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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 400, 350, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_Encabezado = new JPanel();
		panel_Encabezado.setBounds(0, 0, 334, 161);
		contentPane.add(panel_Encabezado);
		panel_Encabezado.setLayout(null);
		
		JLabel lblEncabezado = new JLabel("Estados de los Servidores");
		lblEncabezado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncabezado.setBounds(19, 11, 300, 31);
		lblEncabezado.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		panel_Encabezado.add(lblEncabezado);
		
		JLabel lblServidorPrincipal = new JLabel("Servidor Principal:");
		lblServidorPrincipal.setBounds(19, 53, 126, 18);
		panel_Encabezado.add(lblServidorPrincipal);
		lblServidorPrincipal.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		
		lblAviso = new JLabel("Inicie un Servidor...");
		lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAviso.setBounds(19, 110, 300, 40);
		panel_Encabezado.add(lblAviso);
		lblAviso.setFont(new Font("MS Reference Sans Serif", Font.ITALIC, 12));
		lblAviso.setForeground(Color.GRAY);
		
		lblEstadoSecundario = new JLabel("Offline");
		lblEstadoSecundario.setForeground(new Color(220, 20, 60));
		lblEstadoSecundario.setBounds(190, 83, 80, 16);
		panel_Encabezado.add(lblEstadoSecundario);
		lblEstadoSecundario.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		
		JLabel lblServidorSecundario = new JLabel("Servidor Secundario:");
		lblServidorSecundario.setBounds(19, 82, 148, 18);
		panel_Encabezado.add(lblServidorSecundario);
		lblServidorSecundario.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		
		lblEstadoPrincipal = new JLabel("Offline");
		lblEstadoPrincipal.setBounds(190, 53, 80, 16);
		panel_Encabezado.add(lblEstadoPrincipal);
		lblEstadoPrincipal.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		lblEstadoPrincipal.setBackground(Color.WHITE);
		lblEstadoPrincipal.setForeground(new Color(220, 20, 60));
		
		this.setVisible(true);
	}
	
	public void setPrincipalOnline() {
		lblEstadoPrincipal.setForeground(new Color(46, 139, 87));
		lblEstadoPrincipal.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		lblEstadoPrincipal.setText("Online");
	}
	
	public void setPrincipalOffline() {
		lblEstadoPrincipal.setForeground(new Color(220, 20, 60));
		lblEstadoPrincipal.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		lblEstadoPrincipal.setText("Offline");
	}
	
	public void setSecundarioOnline() {
		lblEstadoSecundario.setForeground(new Color(46, 139, 87));
		lblEstadoSecundario.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		lblEstadoSecundario.setText("Online");
	}
	
	public void setSecundarioOffline() {
		lblEstadoSecundario.setForeground(new Color(220, 20, 60));
		lblEstadoSecundario.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		lblEstadoSecundario.setText("Offline");
	}

}
