package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.PanelHeader;
import modelo.actividades.Actividad;
import modelo.actividades.Examen;
import modelo.actividades.Tarea;

@SuppressWarnings("serial")
public class VentanaCalificarActividad extends JFrame {
	
	private VentanaActividadPendienteCalificar ventanaPendiente;
	private PanelHeader header;
	private PanelCalificarTarea panelTarea;
	private Actividad actividad;
	
	public VentanaCalificarActividad(VentanaActividadPendienteCalificar ventanaPendiente, Actividad actividad)
	{
		this.ventanaPendiente = ventanaPendiente;
		this.actividad = actividad;
		setLayout(new BorderLayout());
		header = new PanelHeader("Calificar");
		
		if (actividad instanceof Tarea)
		{
			panelTarea = new PanelCalificarTarea();
			add(panelTarea, BorderLayout.CENTER);
		}
		else if (actividad instanceof Examen)
		{
			
		}
		
		add(header, BorderLayout.NORTH);
		setTitle( "Calificar" );
        setSize( 400, 300 );
        setLocationRelativeTo( null );
        setVisible( true );
	}
	
	
}
