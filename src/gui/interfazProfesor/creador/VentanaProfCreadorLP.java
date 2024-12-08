package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.GUIManejoDatos;
import gui.PanelHeader;
import gui.VentanaPrincipal;
import gui.interfazProfesor.VentanaProfesor;
import gui.interfazProfesor.seguimiento.VentanaSeguimientoProfesor;
import modelo.LearningPath;
import modelo.Profesor;

public class VentanaProfCreadorLP extends JFrame {
	
	private VentanaProfesor ventanaProf;
	
	private Profesor prof;
	private GUIManejoDatos datos;
	private PanelBotonesCreador pBotones;
	private VentanaVerLPCreados ventanaVerLPCreados;
	private VentanaEditarLP ventanaEditarLP;
	private VentanaCrearLP ventanaCrearLP;
	
	public VentanaProfCreadorLP(VentanaProfesor ventanaProf, Profesor prof, GUIManejoDatos datos)
			throws HeadlessException {
		super();
		this.ventanaProf = ventanaProf;
		this.prof = prof;
		this.datos = datos;
		setLayout(new BorderLayout());
		
		PanelHeader banner = new PanelHeader("Gestion de Learning Paths y Actividades");
		
		add(banner, BorderLayout.NORTH);

		pBotones = new PanelBotonesCreador(this);

		
		
		add(pBotones, BorderLayout.WEST);
		
		pBotones.setPreferredSize(new Dimension(900, 600));

		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 1000, 700 );
        setTitle( "Gestion Learning Paths" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
	    
	}
	

	public void mostrarVentanaEditarLP() {
		// TODO Auto-generated method stub
		if (ventanaEditarLP == null || !ventanaEditarLP.isVisible())
		{
			ventanaEditarLP = new VentanaEditarLP(this, prof, datos);
			ventanaEditarLP.setVisible(true);
			setVisible(false);
		}
	}
	
	public void mostrarVentanaCrearLP() {
		// TODO Auto-generated method stub
		if (ventanaCrearLP == null || !ventanaCrearLP.isVisible())
		{
			ventanaCrearLP = new VentanaCrearLP(this);
			ventanaCrearLP.setVisible(true);
			setVisible(false);
		}
	}
	
	public void mostrarVentanaVerPaths() {
		// TODO Auto-generated method stub
		if (ventanaVerLPCreados == null || !ventanaVerLPCreados.isVisible())
		{
			ventanaVerLPCreados = new VentanaVerLPCreados(this,prof);
			ventanaVerLPCreados.setVisible(true);
			setVisible(false);
		}
		
	}
	
	public void regresar() {
		// TODO Auto-generated method stub
		ventanaProf.setVisible(true);
		dispose();
	}
	

	
}