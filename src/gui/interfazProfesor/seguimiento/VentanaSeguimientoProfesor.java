package gui.interfazProfesor.seguimiento;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.PanelHeader;
import gui.interfazProfesor.VentanaProfesor;
import gui.interfazProfesor.seguimiento.verActividad.VentanaActividad;
import gui.interfazProfesor.seguimiento.verProgreso.VentanaProgresoEstudiante;
import modelo.LearningPath;
import modelo.Progreso;
import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class VentanaSeguimientoProfesor extends JFrame {
	private VentanaProfesor ventanaProf;
	private PanelHeader header;
	private PanelBotonesSeguimiento panelBotones;
	private PanelListaSeguimiento panelLista;
	
	private Progreso estudianteSeleccionado;
	private Actividad actividadSeleccionada;
	private LearningPath lpSeleccionado;
	private Map<Actividad, Progreso> actividadesPendientes;
	
	private VentanaActividad ventanaActividad;
	private VentanaProgresoEstudiante ventanaProgreso;

	public VentanaSeguimientoProfesor(VentanaProfesor ventanaProf) throws HeadlessException {
		this.ventanaProf = ventanaProf;
		setLayout(new BorderLayout());
		
		header = new PanelHeader("Seguimiento de estudiantes");
		
		panelBotones = new PanelBotonesSeguimiento(this);
		panelLista = new PanelListaSeguimiento(this);
		
//		JPanel panelAbajo = new JPanel();
//		panelAbajo.setBorder(new EmptyBorder(50, 0, 50, 0));
		
		add(header, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.WEST);
		add(panelLista, BorderLayout.CENTER);
		//add(panelAbajo, BorderLayout.SOUTH);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 1000, 500 );
        setTitle( "Seguimiento de Estudiantes" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
	}

	public void regresar() {
		// TODO Auto-generated method stub
		ventanaProf.setVisible(true);
		dispose();
	}

	public void verEstudiantesTodos() {
		// TODO Auto-generated method stub
		List<Progreso> progresos = ventanaProf.getProgresosEstudiantes();
		if (progresos.isEmpty())
			JOptionPane.showMessageDialog(this, "No tiene estudiantes inscritos");
		panelLista.actualizarProgresos(progresos);
		panelLista.mostrarProgresos();
	}

	public void verLPs() {
		// TODO Auto-generated method stub
		List<LearningPath> lps = ventanaProf.getLearningPathsCreados();
		if (lps.isEmpty())
			JOptionPane.showMessageDialog(this, "No tiene Learning Paths creados hasta el momento");
		panelLista.actualizarLPs(lps);
		panelLista.mostrarLps();
	}
	
	public void verEstudiantesLPs() 
	{
		if (this.lpSeleccionado != null)
		{
			List<Progreso> progresos = ventanaProf.getEstudiantesPorLP(lpSeleccionado);
			if (progresos.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "No tiene estudiantes inscritos a este Learning Path");
			}
			panelLista.actualizarProgresos(progresos);
		}
		else
			JOptionPane.showMessageDialog(this, "Por favor, seleccione un Learning Path");
	}

	public void verActividadesPendientes() {
		// TODO Auto-generated method stub
		actividadesPendientes = ventanaProf.getActividadesPendientesCalificar();
		List<Actividad> acts = new ArrayList<Actividad>();
		acts.addAll(actividadesPendientes.keySet());
		if (acts.isEmpty())
			JOptionPane.showMessageDialog(this, "No tiene actividades pendientes por calificar");
		panelLista.actualizarActividades(acts);
		panelLista.mostrarActividades();
	}

	public void setEstudianteSeleccionado(Progreso estudianteSeleccionado) {
		// TODO Auto-generated method stub
		this.estudianteSeleccionado = estudianteSeleccionado;
	}

	public void mostrarVentanaProgresoEstudiante() {
		// TODO Auto-generated method stub
		if (estudianteSeleccionado == null)
			JOptionPane.showMessageDialog(this, "Por favor, seleccione un estudiante");
		else
		{
			if (ventanaProgreso == null || !ventanaProgreso.isVisible())
			{
				ventanaProgreso = new VentanaProgresoEstudiante(this, estudianteSeleccionado);
				ventanaProgreso.setVisible(true);
				setVisible(false);
			}
		}
	}

	public void setActividadSeleccionada(Actividad actSeleccionada) {
		// TODO Auto-generated method stub
		this.actividadSeleccionada = actSeleccionada;
	}

	public void mostrarActividadPendiente() {
		// TODO Auto-generated method stub
		if (actividadSeleccionada == null)
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una actividad");
		else
		{
			if (ventanaActividad == null || !ventanaActividad.isVisible())
			{
				ventanaActividad = new VentanaActividad(this, actividadSeleccionada, "Por calificar:");
				ventanaActividad.setVisible(true);
			}
		}
	}
	
	public void setLpSeleccionado(LearningPath lpSeleccionado)
	{
		this.lpSeleccionado = lpSeleccionado;
	}

	public void calificarActividad(Actividad actividad) {
		// TODO Auto-generated method stub
		Progreso p = actividadesPendientes.get(actividad);
		ventanaProf.calificarActividad(actividad, p);
		verActividadesPendientes();
	}

	public void problemaCalificando() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Hubo un problema calificando la actividad. Intentelo de nuevo");
	}
	
}
