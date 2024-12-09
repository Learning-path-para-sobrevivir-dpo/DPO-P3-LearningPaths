package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.GridLayout;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.actividades.PreguntaAbierta;

@SuppressWarnings("serial")
public class PanelVerPregunta extends JPanel {
	
	private VentanaMostrarPreguntasExamen ventanaPreguntas;
	private List<PreguntaAbierta> preguntas;
	private Iterator<PreguntaAbierta> iterador;
	private PreguntaAbierta preguntaActual;
	private JLabel enunciado;
	private JLabel respuesta;
	
	public PanelVerPregunta(VentanaMostrarPreguntasExamen ventanaPreguntas, List<PreguntaAbierta> preguntas) {
		this.ventanaPreguntas = ventanaPreguntas;
		this.preguntas = preguntas;
		this.iterador = this.preguntas.iterator();
		setLayout(new GridLayout(2, 2));
		setBorder(new EmptyBorder(10,30,10,30));
		
		JLabel labEnunciado = new JLabel("Enunciado:");
		enunciado = new JLabel();
		
		JLabel labRespuesta = new JLabel("Respuesta");
		respuesta = new JLabel();
		
		add(labEnunciado);
		add(enunciado);
		add(labRespuesta);
		add(respuesta);
	}
	
	public void actualizar()
	{
		if (preguntas == null || preguntas.isEmpty())
		{
			ventanaPreguntas.dejarCalificarExamen();
			ventanaPreguntas.cerrar();
		}
		else if (iterador.hasNext())
		{
			preguntaActual = iterador.next();
			enunciado.setText(preguntaActual.getEnunciado());
			respuesta.setText(preguntaActual.getRespuesta());
			if (!iterador.hasNext())
				ventanaPreguntas.setCalificar(true);
		}
	}
	
	public boolean haySiguiente()
	{
		return iterador.hasNext();
	}
	
}
