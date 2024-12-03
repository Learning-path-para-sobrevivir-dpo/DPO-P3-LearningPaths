package gui;

import javax.swing.JFrame;

import persistencia.ManejoDatos;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	
	private VentanaInicio ventanaInicio;
	
	public static void main(String[] args) {
		ManejoDatos datos = new ManejoDatos();
		VentanaPrincipal ventana = new VentanaPrincipal(datos);
	}
	
	public VentanaPrincipal(ManejoDatos datos) {
		ventanaInicio = new VentanaInicio(datos);
		ventanaInicio.setVisible(true);
		setTitle( "Learning Path App" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 600 );
        setLocationRelativeTo( null );
	}
	
	
}
