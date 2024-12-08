package gui.interfazProfesor.seguimiento.verActividad;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class PanelDetallesActividad extends JPanel {
	
	private Actividad actividad;

	public PanelDetallesActividad(Actividad actividad) {
		this.actividad = actividad;
		JLabel titulo = new JLabel(actividad.titulo);
	}
	
}
