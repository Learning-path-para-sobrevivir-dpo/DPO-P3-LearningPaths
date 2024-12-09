package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.actividades.Actividad;
import modelo.actividades.Examen;

@SuppressWarnings("serial")
public class PanelCalificarExamen extends JPanel implements ActionListener {
	
	private static final String CALIFICAR = "calificar";
	
	private JButton butCalificarPreguntas;
	private VentanaCalificarActividad ventanaCalificar;
	private JComboBox<String> marcar;
	private Examen examen;
	private JLabel calificacion;
	
	public PanelCalificarExamen(VentanaCalificarActividad ventanaCalificar, Examen examen) {
		this.ventanaCalificar = ventanaCalificar;
		this.examen = examen;
		setLayout(new GridLayout(3, 1, 40, 10));
		setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel titulo = new JLabel("Haz click al boton para calificar las preguntas:");
		titulo.setFont(new Font("Calibri", Font.BOLD, 20));
		
		butCalificarPreguntas = new JButton("Calificar Examen");
		butCalificarPreguntas.addActionListener(this);
		butCalificarPreguntas.setActionCommand(CALIFICAR);
		butCalificarPreguntas.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JPanel miniPanel = new JPanel();
		miniPanel.setBorder(new EmptyBorder(10,30,10,30));
		miniPanel.add(butCalificarPreguntas);
		
		marcar = new JComboBox<String>(new String[] {"Exitosa", "No Exitosa"});
		JLabel labMarcar = new JLabel("Marcar el examen como:");
		
		calificacion = new JLabel(Float.toString(examen.getCalificacion()));
		JLabel labCalificacion = new JLabel("Calificación Actual:");
		
		JPanel miniPanel2 = new JPanel();
		miniPanel2.setLayout(new GridLayout(2,2));
		miniPanel2.add(labCalificacion);
		miniPanel2.add(calificacion);
		miniPanel2.add(labMarcar);
		miniPanel2.add(marcar);
		
		add(titulo);
		add(miniPanel);
		add(miniPanel2);
	}

	public String getMarcar()
	{
		return (String) marcar.getSelectedItem();
	}
	
	public void actualizar()
	{
		calificacion.setText(Float.toString(examen.getCalificacion()));
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		if (comando.equals(CALIFICAR))
		{
			ventanaCalificar.mostrarVentanaPreguntasExamen();
		}
	}
	
}
