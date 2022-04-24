import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VentanaReceptor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaReceptor frame = new VentanaReceptor();
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
	public VentanaReceptor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.45);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro de solicitudes:");
		lblNewLabel.setBounds(10, 0, 119, 25);
		panel.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 24, 242, 483);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list.setBounds(10, 21, 222, 439);
		panel_3.add(list);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Detalle: ");
		lblNewLabel_1.setBounds(10, 0, 49, 25);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 24, 200, 483);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo alerta:");
		lblNewLabel_2.setBounds(11, 56, 63, 14);
		panel_2.add(lblNewLabel_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(84, 51, 101, 22);
		panel_2.add(textArea);
		
		JLabel lblNewLabel_3 = new JLabel("Horario:");
		lblNewLabel_3.setBounds(11, 131, 63, 14);
		panel_2.add(lblNewLabel_3);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(84, 126, 101, 22);
		panel_2.add(textArea_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Ubicacion: ");
		lblNewLabel_3_1.setBounds(11, 209, 63, 14);
		panel_2.add(lblNewLabel_3_1);
		
		JTextArea textArea_1_1 = new JTextArea();
		textArea_1_1.setBounds(84, 204, 101, 22);
		panel_2.add(textArea_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Estado:");
		lblNewLabel_4.setBounds(11, 350, 46, 14);
		panel_2.add(lblNewLabel_4);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(84, 345, 101, 22);
		panel_2.add(textArea_2);
		
		JButton btnNewButton = new JButton("CONFIRMAR");
		btnNewButton.setBounds(36, 410, 119, 53);
		panel_2.add(btnNewButton);
	}
}
