package gui.interfazEstudiante;

import javax.swing.JFrame;

import gui.GUIManejoDatos;
import gui.VentanaPrincipal;
import modelo.Estudiante;

public class VentanaEstudiante extends JFrame {
	
	private VentanaPrincipal ventanaInicio;
	
	private Estudiante est;
	private GUIManejoDatos datos;
	
	public VentanaEstudiante(Estudiante e, GUIManejoDatos datos, VentanaPrincipal ventanaInicio) {
		this.datos = datos;
		this.est = e;
		this.ventanaInicio = ventanaInicio;
	}

}
