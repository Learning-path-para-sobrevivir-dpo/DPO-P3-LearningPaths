package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private PanelBotonesCalificar panelBotones;
	private Actividad actividad;
	
	public VentanaCalificarActividad(VentanaActividadPendienteCalificar ventanaPendiente, Actividad actividad)
	{
		this.ventanaPendiente = ventanaPendiente;
		this.actividad = actividad;
		setLayout(new BorderLayout());
		header = new PanelHeader("Calificar");
		panelBotones = new PanelBotonesCalificar(this);
		
		if (actividad instanceof Tarea)
		{
			panelTarea = new PanelCalificarTarea();
			add(panelTarea, BorderLayout.CENTER);
		}
		else if (actividad instanceof Examen)
		{
			
		}
		
		add(header, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.SOUTH);
		setTitle( "Calificar" );
        setSize( 400, 300 );
        setLocationRelativeTo( null );
        setVisible( true );
	}

	public void cerrar() {
		// TODO Auto-generated method stub
		dispose();
	}

	public void calificarActividad() {
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(this, "Esta seguro de que quiere calificar esta actividad",
                "Confirmacion para calificar", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION)
		{
			if (actividad instanceof Tarea)
			{
				String marcada = panelTarea.getMarcar();
				actividad.setEstado(marcada);
			}
			ventanaPendiente.calificarActividad();
			dispose();
		}
	}
	
	
}
