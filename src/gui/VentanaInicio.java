package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.ManejoDatos;

public class VentanaInicio extends JFrame {
	
	private PanelBotonesInicio panelBotones;
	private ManejoDatos datos;

	public VentanaInicio(ManejoDatos datos) throws HeadlessException {
		this.datos = datos;
		setLayout(new BorderLayout());
		
		JLabel bienvenida = new JLabel("Bienvenido a la Aplicación!!!");
		bienvenida.setHorizontalAlignment(JLabel.CENTER);
		bienvenida.setFont(new Font("Javanese Text", Font.BOLD, 40));
		
		JLabel sub = new JLabel("Seleccione lo que quiere hacer");
		sub.setHorizontalAlignment(JLabel.CENTER);
		sub.setFont(new Font("Calibri", Font.BOLD, 20));
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		header.setBorder(new EmptyBorder(10, 0, 20, 0));
		header.setBackground(new Color(236, 145, 146));
		header.add(bienvenida, BorderLayout.NORTH);
		header.add(sub, BorderLayout.SOUTH);
		add(header, BorderLayout.NORTH);
		
		panelBotones = new PanelBotonesInicio(this);
		add(panelBotones, BorderLayout.CENTER);
		
		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 600, 400 );
        setTitle( "Bienvenidos" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
	}

	public void crearUsuario() {
		// TODO Auto-generated method stub
		
	}

	public void iniciarSesion() {
		// TODO Auto-generated method stub
		
	}

	public void salir() {
		// TODO Auto-generated method stub
		dispose();
	}
}
