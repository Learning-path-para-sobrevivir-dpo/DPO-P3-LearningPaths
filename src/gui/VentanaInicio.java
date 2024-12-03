package gui;

import java.awt.BorderLayout;
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
		bienvenida.setFont(new Font("Brush Script MT", Font.PLAIN, 40));
		
		JLabel sub = new JLabel("Seleccione lo que quiere hacer");
		sub.setHorizontalAlignment(JLabel.CENTER);
		
		
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
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
	}

	public void crearUsuario() {
		// TODO Auto-generated method stub
		
	}

	public void iniciarSesion() {
		// TODO Auto-generated method stub
		
	}

	public void salir() {
		// TODO Auto-generated method stub
		
	}
}
