package gui.iniciarSesion;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.GUIManejoDatos;
import gui.PanelHeader;
import gui.VentanaPrincipal;

@SuppressWarnings("serial")
public class VentanaInicioSesion extends JFrame {
	
	private VentanaPrincipal ventanaInicio;
	private GUIManejoDatos datos;
	private PanelHeader header;
	private PanelInfoUsuario detallesUsuario;
	private PanelBotonesInicioSesion panelBotones;
	
	public VentanaInicioSesion(VentanaPrincipal ventanaInicio, GUIManejoDatos datos) throws HeadlessException {
		
		this.ventanaInicio = ventanaInicio;
		this.datos = datos;
		setLayout(new BorderLayout());
		
		header = new PanelHeader("Iniciar Sesion");
		detallesUsuario = new PanelInfoUsuario();
		panelBotones = new PanelBotonesInicioSesion(this);
		
		add(header, BorderLayout.NORTH);
		add(detallesUsuario, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		
		setTitle( "Crear Usuario" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 300 );
        setLocationRelativeTo( null );
        setVisible( true );
	}

	public void volverInicio() {
		ventanaInicio.setVisible(true);
		dispose( );
	}

	public void iniciarSesion() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
