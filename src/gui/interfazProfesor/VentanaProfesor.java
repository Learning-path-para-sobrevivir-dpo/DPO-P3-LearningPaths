package gui.interfazProfesor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.GUIManejoDatos;
import gui.PanelHeader;
import gui.VentanaPrincipal;
import gui.crearUsuario.VentanaCrearUsuario;
import gui.interfazProfesor.Creador.VentanaProfCreadorLP;
import modelo.Profesor;


@SuppressWarnings("serial")
public class VentanaProfesor extends JFrame{
	
	private VentanaPrincipal ventanaInicio;
	private VentanaProfCreadorLP ventanaCreador;
	
	private Profesor prof;
	private GUIManejoDatos datos;
	
	private PanelBotonesOpcionesProf panelBotones;
	
	
	public VentanaProfesor(Profesor p, GUIManejoDatos datos, VentanaPrincipal ventanaInicio) {
		// TODO Auto-generated constructor stub
		this.prof = p;
		this.datos = datos;
		this.ventanaInicio = ventanaInicio;
		setLayout(new BorderLayout());
		
		PanelHeader intro = new PanelHeader("Profesor(a), que desea hacer hoy: ");
		
		
		panelBotones = new PanelBotonesOpcionesProf(this);
		
		add(intro, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.CENTER);
		

		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 600, 400 );
        setTitle( "Profesor" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
	}

	
	
	public void mostrarVentanaCreador() {
		// TODO Auto-generated method stub
		if (ventanaCreador == null || !ventanaCreador.isVisible())
		{
			ventanaCreador = new VentanaProfCreadorLP(this, prof, datos);
			ventanaCreador.setVisible(true);
			setVisible(false);
		}
	}

}
