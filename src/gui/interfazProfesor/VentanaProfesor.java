package gui.interfazProfesor;

import javax.swing.JFrame;

import gui.GUIManejoDatos;
import gui.VentanaPrincipal;
import modelo.Profesor;

public class VentanaProfesor extends JFrame {
	
	private VentanaPrincipal ventanaInicio;
	
	private Profesor prof;
	private GUIManejoDatos datos;
	
	public VentanaProfesor(Profesor p, GUIManejoDatos datos, VentanaPrincipal ventanaInicio) {
		// TODO Auto-generated constructor stub
		this.prof = p;
		this.datos = datos;
		this.ventanaInicio = ventanaInicio;
	}

}
