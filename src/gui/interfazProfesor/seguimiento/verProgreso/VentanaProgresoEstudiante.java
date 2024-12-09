package gui.interfazProfesor.seguimiento.verProgreso;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import gui.PanelHeader;
import gui.interfazProfesor.seguimiento.VentanaSeguimientoProfesor;
import modelo.Progreso;
import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class VentanaProgresoEstudiante extends JFrame {
	
	private VentanaSeguimientoProfesor ventanaSeguimiento;
	private PanelHeader header;
	private PanelBotonesProgreso panelBotones;
	private PanelListaActividades panelLista;
	
	private Progreso progresoEstudiante;
	private Actividad actividadSeleccionada;
	
	public VentanaProgresoEstudiante(VentanaSeguimientoProfesor ventanaSeguimiento, Progreso progresoEstudiante) throws HeadlessException {
		this.ventanaSeguimiento = ventanaSeguimiento;
		this.progresoEstudiante = progresoEstudiante;
		
		header = new PanelHeader("Progreso de "+progresoEstudiante.getEstudiante());
		panelBotones = new PanelBotonesProgreso(this);
		
		add(header, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.WEST);
		//add(panelLista, BorderLayout.CENTER);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 1000, 500 );
        setTitle( "Progreso del Estudiante" );
        setLocationRelativeTo( null );
	}

	public void mostrarPendientes() {
		// TODO Auto-generated method stub
		
	}

	public void mostrarObligatoriasPendientes() {
		// TODO Auto-generated method stub
		
	}

	public void mostrarCompletadas() {
		// TODO Auto-generated method stub
		
	}

	public void mostrarObligatoriasCompletadas() {
		// TODO Auto-generated method stub
		
	}

	public void mostrarActividadProgreso() {
		// TODO Auto-generated method stub
		
	}

	public void cerrar() {
		// TODO Auto-generated method stub
		ventanaSeguimiento.setVisible(true);
		dispose();
	}

	public void setActividadSeleccionada(Actividad act) {
		// TODO Auto-generated method stub
		this.actividadSeleccionada = act;
	}
	
	
}
