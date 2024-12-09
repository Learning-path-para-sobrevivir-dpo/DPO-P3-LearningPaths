package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JFrame;

import gui.GUIManejoDatos;
import modelo.Profesor;
import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class VentanaClonarAct  extends JFrame {

	private PanelListActsSistema actsSistema;
	private GUIManejoDatos datos;
    private PanelDetallesActClonar pDetalles;
    private Profesor prof;
    private VentanaProfCreadorLP ventanaCreador;
    
	public VentanaClonarAct(VentanaProfCreadorLP ventanaCreador, Profesor prof, GUIManejoDatos datos) throws HeadlessException {
		super();
		this.datos = datos;
		this.prof = prof;
		this.ventanaCreador = ventanaCreador;
		
		
        actsSistema = new PanelListActsSistema(this);
        add( actsSistema, BorderLayout.CENTER);
        
        pDetalles = new PanelDetallesActClonar(this);
        add( pDetalles, BorderLayout.SOUTH );
        
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
	
	public void cambiarActSelected(Actividad seleccionado) {
		pDetalles.actualizarActividad(seleccionado );
		
	}
	
    public void cerrarVentana( )
    {
    	ventanaCreador.setVisible(true);
        dispose( );
    }


	public void clonarActividad() {
		// TODO Auto-generated method stub
		Actividad act = actsSistema.getActSelected();
		Actividad actClonada = prof.clonarActividad(act);
		datos.addActividad(actClonada);
		datos.actualizarUsuario(prof);
	}
	
}
