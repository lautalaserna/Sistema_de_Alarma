package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerSelection;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;

public class VSelection extends JFrame implements MouseListener{

	private JPanel contentPane;
	private JLabel lblSelection;
	private JRadioButton rdbtnPrimario;
	private JRadioButton rdbtnSecundario;
	private JButton btnConfirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VSelection frame = new VSelection();
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
	public VSelection() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 400, 300, 150);
		setResizable(false);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		this.lblSelection = new JLabel("Seleccione el Rol del Servidor:");
		this.lblSelection.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		this.lblSelection.setBounds(20, 10, 219, 18);
		this.contentPane.add(this.lblSelection);
		
		this.rdbtnPrimario = new JRadioButton("Primario");
		this.rdbtnPrimario.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		this.rdbtnPrimario.setBounds(20, 35, 87, 21);
		this.contentPane.add(this.rdbtnPrimario);
		rdbtnPrimario.addMouseListener(this);
		
		this.rdbtnSecundario = new JRadioButton("Secundario");
		this.rdbtnSecundario.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		this.rdbtnSecundario.setBounds(20, 59, 110, 21);
		this.contentPane.add(this.rdbtnSecundario);
		rdbtnSecundario.addMouseListener(this);
		
		this.btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setEnabled(false);
		btnConfirmar.setActionCommand("Confirmar");
		this.btnConfirmar.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		this.btnConfirmar.setBounds(164, 75, 110, 25);
		this.contentPane.add(this.btnConfirmar);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnPrimario);
		group.add(rdbtnSecundario);
		
		this.setVisible(true);
	}
	
	public JRadioButton getRdbtnPrimario() {
		return rdbtnPrimario;
	}

	public JRadioButton getRdbtnSecundario() {
		return rdbtnSecundario;
	}

	public void addActionListener(ControllerSelection controller) {
		this.btnConfirmar.addActionListener(controller);
	}

	public void check() {
		if (!rdbtnPrimario.isSelected() && !rdbtnSecundario.isSelected())
			btnConfirmar.setEnabled(false);
		else 
			btnConfirmar.setEnabled(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		check();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
