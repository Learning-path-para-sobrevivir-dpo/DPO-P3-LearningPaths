package gui.interfazProfesor.seguimiento.verProgreso;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.PanelHeader;
import gui.interfazProfesor.seguimiento.VentanaSeguimientoProfesor;
import gui.interfazProfesor.seguimiento.verActividad.VentanaActividad;
import modelo.Progreso;
import modelo.actividades.Actividad;
import modelo.actividades.Examen;
import modelo.actividades.Tarea;

@SuppressWarnings("serial")
public class VentanaProgresoEstudiante extends JFrame {
	
	private VentanaSeguimientoProfesor ventanaSeguimiento;
	private PanelHeader header;
	private PanelBotonesProgreso panelBotones;
	private PanelListaActividades panelLista;
	private VentanaActividad ventanaActividad;
	
	private Progreso progresoEstudiante;
	private Actividad actividadSeleccionada;
	
	public VentanaProgresoEstudiante(VentanaSeguimientoProfesor ventanaSeguimiento, Progreso progresoEstudiante) throws HeadlessException {
		this.ventanaSeguimiento = ventanaSeguimiento;
		this.progresoEstudiante = progresoEstudiante;
		
		header = new PanelHeader("Progreso de "+progresoEstudiante.getEstudiante());
		panelBotones = new PanelBotonesProgreso(this);
		panelLista = new PanelListaActividades(this);
		
		JPanel panelAbajo = new JPanel();
		panelAbajo.setLayout(new GridLayout(3,2));
		panelAbajo.setBorder(new EmptyBorder(10,30,10,30));
		
		JLabel labLP = new JLabel("Learning Path: ");
		JLabel lp = new JLabel(progresoEstudiante.getLearningPath());
		labLP.setFont(new Font("Calibri", Font.BOLD, 20));
		lp.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		JLabel labProgresoTotal = new JLabel("Progreso total: ");
		JLabel pTotal = new JLabel(Integer.toString(progresoEstudiante.getProgresoTotal()) + "%");
		labProgresoTotal.setFont(new Font("Calibri", Font.BOLD, 20));
		pTotal.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		JLabel labProgresoObligatorio = new JLabel("Progreso Obligatorio: ");
		JLabel pObligatorio = new JLabel(Integer.toString(progresoEstudiante.getProgresoObligatorio()) + "%");
		labProgresoObligatorio.setFont(new Font("Calibri", Font.BOLD, 20));
		pObligatorio.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		panelAbajo.add(labLP);
		panelAbajo.add(lp);
		panelAbajo.add(labProgresoTotal);
		panelAbajo.add(pTotal);
		panelAbajo.add(labProgresoObligatorio);
		panelAbajo.add(pObligatorio);
		
		add(header, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.WEST);
		add(panelLista, BorderLayout.CENTER);
		add(panelAbajo, BorderLayout.SOUTH);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 1000, 700 );
        setTitle( "Progreso del Estudiante" );
        setLocationRelativeTo( null );
	}

	public void mostrarPendientes() {
		// TODO Auto-generated method stub
		List<Actividad> acts = progresoEstudiante.getActPendientes();
		panelLista.actualizar(acts);
		if (acts.isEmpty())
			JOptionPane.showMessageDialog(this, "No tiene actividades pendientes");
	}

	public void mostrarObligatoriasPendientes() {
		// TODO Auto-generated method stub
		List<Actividad> acts = progresoEstudiante.getActObligatoriasPendientes();
		panelLista.actualizar(acts);
		if (acts.isEmpty())
			JOptionPane.showMessageDialog(this, "No tiene actividades obligatorias pendientes");
	}

	public void mostrarCompletadas() {
		// TODO Auto-generated method stub
		List<Actividad> acts = progresoEstudiante.getActCompletadas();
		panelLista.actualizar(acts);
		if (acts.isEmpty())
			JOptionPane.showMessageDialog(this, "No tiene actividades completadas");
	}

	public void mostrarObligatoriasCompletadas() {
		// TODO Auto-generated method stub
		List<Actividad> acts = progresoEstudiante.getActObligatoriasCompletadas();
		panelLista.actualizar(acts);
		if (acts.isEmpty())
			JOptionPane.showMessageDialog(this, "No tiene actividades obligatorias completadas");
	}

	public void mostrarActividadProgreso() {
		// TODO Auto-generated method stub
		List<Actividad> acts = new ArrayList<Actividad>();
		panelLista.actualizar(acts);
		
		actividadSeleccionada = progresoEstudiante.getActividadEnProgreso();
		if (actividadSeleccionada == null)
			JOptionPane.showMessageDialog(this, "Actualmente, el estudiante no tiene una actividad en progreso");
		else
			verActividadSeleccionada();
	}

	public void cerrar() {
		// TODO Auto-generated method stub
		ventanaSeguimiento.setVisible(true);
		dispose();
	}

	public void setActividadSeleccionada(Actividad act) {
		// TODO Auto-generated method stub
		this.actividadSeleccionada = act;
	}

	public void verActividadSeleccionada() {
		// TODO Auto-generated method stub
		if (actividadSeleccionada == null)
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una actividad");
		else
		{
			if (ventanaActividad == null || !ventanaActividad.isVisible())
			{
				ventanaActividad = new VentanaActividad(ventanaSeguimiento, actividadSeleccionada, "Actividad");
				
				if (!(actividadSeleccionada instanceof Tarea) && !(actividadSeleccionada instanceof Examen))
					ventanaActividad.deshabilitarCalificar();
				
				ventanaActividad.setVisible(true);
			}
		}
	}
	
	
}
