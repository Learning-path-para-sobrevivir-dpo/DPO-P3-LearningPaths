package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import gui.GUIManejoDatos;
import modelo.LearningPath;
import modelo.actividades.Actividad;

public class VentanaAddActividad extends JFrame{
	
	private VentanaLPCreados ventanaCreados;
	private PanelListActsSistema actsSistema;
	private GUIManejoDatos datos;

	public VentanaAddActividad(VentanaLPCreados ventanaCreados, GUIManejoDatos datos) throws HeadlessException {
		super();
		this.ventanaCreados = ventanaCreados;
		this.datos = datos;
		
        actsSistema = new PanelListActsSistema(this);
        add( actsSistema, BorderLayout.CENTER);
        
        mostrarActsSistema();
        
		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 900, 600 );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
        
	    
		
	}
	
	
	public void mostrarActsSistema() {
		
		List<Actividad> listActs = datos.getActividadesSistema();
		
		actsSistema.mostrarActs(listActs);
	}
	
	
	
	

}
