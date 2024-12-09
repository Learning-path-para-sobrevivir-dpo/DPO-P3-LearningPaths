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
	
	private VentanaActividad ventanaPendiente;
	private VentanaMostrarPreguntasExamen ventanaPreguntas;
	
	private PanelHeader header;
	private PanelCalificarTarea panelTarea;
	private PanelCalificarExamen panelExamen;
	private PanelBotonesCalificar panelBotones;
	private boolean calificado;
	private Actividad actividad;
	
	public VentanaCalificarActividad(VentanaActividad ventanaPendiente, Actividad actividad)
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
			calificado = true;
		}
		else if (actividad instanceof Examen)
		{
			calificado = false;
			panelExamen = new PanelCalificarExamen(this, (Examen) actividad);
			add(panelExamen, BorderLayout.CENTER);
		}
		
		add(header, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.SOUTH);
		setTitle( "Calificar" );
        setSize( 400, 300 );
        setLocationRelativeTo( null );
        setVisible( true );
	}
	
	public void setCalificado(boolean calificado)
	{
		this.calificado = calificado;
	}

	public void cerrar() {
		// TODO Auto-generated method stub
		dispose();
	}

	public void calificarActividad() {
		// TODO Auto-generated method stub
		if (calificado)
		{
			if(JOptionPane.showConfirmDialog(this, "Esta seguro de que quiere calificar esta actividad",
	                "Confirmacion para calificar", JOptionPane.YES_NO_OPTION,
	                JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION)
			{
				if (actividad instanceof Tarea)
				{
					String marcada = panelTarea.getMarcar();
					actividad.setEstado(marcada);
				}
				else if (actividad instanceof Examen)
				{
					
				}
				ventanaPendiente.calificarActividad();
				dispose();
			}
		}
		
	}

	public void mostrarVentanaPreguntasExamen() {
		// TODO Auto-generated method stub
		if (ventanaPreguntas == null || !ventanaPreguntas.isVisible())
		{
			ventanaPreguntas = new VentanaMostrarPreguntasExamen(this, (Examen) actividad);
		}
	}
	
	
}
