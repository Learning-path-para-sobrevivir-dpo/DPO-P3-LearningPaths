package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JFrame;

import modelo.actividades.Examen;
import modelo.actividades.PreguntaAbierta;

@SuppressWarnings("serial")
public class VentanaMostrarPreguntasExamen extends JFrame {
	
	private VentanaCalificarActividad ventanaCalificar;
	private Examen examen;
	private List<PreguntaAbierta> preguntas;
	
	public VentanaMostrarPreguntasExamen(VentanaCalificarActividad ventanaCalificar, Examen examen) {
		this.ventanaCalificar = ventanaCalificar;
		this.examen = examen;
	}
	
	
	
}
