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
			setSize( 400, 300 );
		}
		else if (actividad instanceof Examen)
		{
			calificado = false;
			panelExamen = new PanelCalificarExamen(this, (Examen) actividad);
			add(panelExamen, BorderLayout.CENTER);
			setSize( 450, 400 );
		}
		
		add(header, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.SOUTH);
		setTitle( "Calificar" );
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
					String marcada = panelExamen.getMarcar();
					actividad.setEstado(marcada);
				}
				ventanaPendiente.calificarActividad();
				dispose();
			}
		}
		else
			JOptionPane.showMessageDialog(this, "Tiene que calificar primero el examen");
		
	}

	public void mostrarVentanaPreguntasExamen() {
		// TODO Auto-generated method stub
		Examen e = (Examen) actividad;
		if (e.getPreguntas().isEmpty())
		{
			JOptionPane.showMessageDialog(ventanaPreguntas, "No hay preguntas para mostrar");
			setCalificado(true);
		}
		else
		{
			if (ventanaPreguntas == null || !ventanaPreguntas.isVisible())
			{
				ventanaPreguntas = new VentanaMostrarPreguntasExamen(this, e);
				ventanaPreguntas.setVisible(true);
			}
		}
		
	}
	
	
}
