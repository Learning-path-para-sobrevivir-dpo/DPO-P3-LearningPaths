package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import excepciones.RespuestasInconsistentesPruebaException;
import gui.PanelHeader;
import modelo.actividades.Examen;
import modelo.actividades.PreguntaAbierta;

@SuppressWarnings("serial")
public class VentanaMostrarPreguntasExamen extends JFrame {
	
	private VentanaCalificarActividad ventanaCalificar;
	private PanelVerPregunta panelPregunta;
	private PanelHeader header;
	private PanelBotonesCalificarPreguntas panelBotones;
	
	private Examen examen;
	private List<String> calificaciones;
	private boolean calificar;
	
	public VentanaMostrarPreguntasExamen(VentanaCalificarActividad ventanaCalificar, Examen examen) {
		this.ventanaCalificar = ventanaCalificar;
		this.examen = examen;
		this.calificaciones = new ArrayList<String>();
		List<PreguntaAbierta> preguntas = examen.getPreguntas();
		setLayout(new BorderLayout());
		
		header = new PanelHeader("Calificar Preguntas");
		add(header, BorderLayout.NORTH);
		
		panelPregunta = new PanelVerPregunta(this, preguntas);
		JScrollPane scroll = new JScrollPane(panelPregunta);
		scroll.createVerticalScrollBar();
		add(scroll, BorderLayout.CENTER);
		
		panelBotones = new PanelBotonesCalificarPreguntas(this);
		add(panelBotones, BorderLayout.SOUTH);
		
		setSize( 650, 500 );
		setTitle( "Preguntas Examen" );
        setLocationRelativeTo( null );
        setVisible( true );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        panelPregunta.actualizar();
	}
	
	public void dejarCalificarExamen()
	{
		ventanaCalificar.setCalificado(true);
	}
	
	public void setCalificar(boolean calificar)
	{
		this.calificar = calificar;
	}

	public void cerrar() {
		// TODO Auto-generated method stub
		dispose(); 
	}

	public void calificar() {
		// TODO Auto-generated method stub
		if (!calificar)
			JOptionPane.showMessageDialog(this, "No se puede calificar hasta que califique todas las preguntas");
		else
		{
			dejarCalificarExamen();
			try {
				examen.calificar(calificaciones);
			} catch (RespuestasInconsistentesPruebaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ventanaCalificar.setVisible(true);
			cerrar();
		}
	}

	public void siguiente() {
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(this, "Esta seguro de que quiere pasar a la siguiente? no se podra devolver",
                "Confirmacion para calificar", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION)
		{
			if (panelPregunta.haySiguiente())
			{
				calificaciones.add(panelBotones.getMarcar());
				panelPregunta.actualizar();
			}
		}
	}
	
}
