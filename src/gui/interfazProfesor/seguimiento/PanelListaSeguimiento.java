package gui.interfazProfesor.seguimiento;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.LearningPath;
import modelo.Progreso;
import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class PanelListaSeguimiento extends JPanel implements ListSelectionListener{
	
	private static final String TODOSESTUDIANTES = "todos los estudiantes";
	private static final String LPS = "Learning Paths";
	private static final String ACTIVIDADES = "actividades pendientes por calificar";
	
	private String currentScrollView;
	
	private JList<Progreso> listaProgresos;
	private DefaultListModel<Progreso> modeloProgreso;
	
	private JList<LearningPath> listaLearningPaths;
	private DefaultListModel<LearningPath> modeloLp;
	
	private JList<Actividad> listaActividades;
	private DefaultListModel<Actividad> modeloActividad;
	
	private VentanaSeguimientoProfesor ventanaSeguimientoProfesor;
	private PanelBotonesListaSeleccion panelBotones;
	private PanelListaLPs panelLps;
	private JScrollPane scroll;
	
	public PanelListaSeguimiento(VentanaSeguimientoProfesor ventanaSeguimientoProfesor) {
		this.ventanaSeguimientoProfesor = ventanaSeguimientoProfesor;
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(20, 50, 20, 50));
		
		modeloProgreso = new DefaultListModel<Progreso>();
		listaProgresos = new JList<Progreso>(modeloProgreso);
		listaProgresos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaProgresos.addListSelectionListener(this);
		
		modeloLp = new DefaultListModel<LearningPath>();
		listaLearningPaths = new JList<LearningPath>(modeloLp);
		listaLearningPaths.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaLearningPaths.addListSelectionListener(this);
		
		modeloActividad = new DefaultListModel<Actividad>();
		listaActividades = new JList<Actividad>(modeloActividad);
		listaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaActividades.addListSelectionListener(this);
		
		panelLps = new PanelListaLPs(listaLearningPaths, this);
		panelLps.setVisible(false);
		
		scroll = new JScrollPane();
		scroll.setBounds(20, 120,500, 500);
		scroll.setViewportView(listaProgresos);
		
		panelBotones = new PanelBotonesListaSeleccion(this);
		
		add(panelLps, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
	}
	
	public void actualizarProgresos(List<Progreso> progresos)
	{
		modeloProgreso.clear();
		modeloProgreso.addAll(progresos);
	}
	
	public void actualizarLPs(List<LearningPath> lps) {
		modeloLp.clear();
		modeloLp.addAll(lps);
	}
	
	public void actualizarActividades(List<Actividad> acts) {
		// TODO Auto-generated method stub
		modeloActividad.clear();
		modeloActividad.addAll(acts);
	}

	public void mostrarProgresos() {
		// TODO Auto-generated method stub
		scroll.setViewportView(listaProgresos);
		currentScrollView = TODOSESTUDIANTES;
		panelBotones.cambiarTextoBoton("Ver progreso del estudiante seleccionado");
		panelBotones.setVisible(true);
		panelLps.setVisible(false);
	}

	public void mostrarLps() {
		// TODO Auto-generated method stub
		modeloProgreso.clear();
		scroll.setViewportView(listaProgresos);
		currentScrollView = LPS;
		panelBotones.cambiarTextoBoton("Ver progreso del estudiante seleccionado");
		panelBotones.setVisible(true);
		panelLps.setVisible(true);
	}
	
	public void mostrarProgresosLP() {
		// TODO Auto-generated method stub
		ventanaSeguimientoProfesor.verEstudiantesLPs();
	}

	public void mostrarActividades() {
		// TODO Auto-generated method stub
		scroll.setViewportView(listaActividades);
		currentScrollView = ACTIVIDADES;
		panelBotones.cambiarTextoBoton("Ver actividad pendiente");
		panelBotones.setVisible(true);
		panelLps.setVisible(false);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (currentScrollView.equals(TODOSESTUDIANTES) || currentScrollView.equals(LPS))
		{
			Progreso estudianteSeleccionado = listaProgresos.getSelectedValue();
			ventanaSeguimientoProfesor.setEstudianteSeleccionado(estudianteSeleccionado);
		}
		if (currentScrollView.equals(LPS))
		{
			LearningPath path = listaLearningPaths.getSelectedValue();
			ventanaSeguimientoProfesor.setLpSeleccionado(path);
		}
		else if (currentScrollView.equals(ACTIVIDADES))
		{
			Actividad actSeleccionada = listaActividades.getSelectedValue();
			ventanaSeguimientoProfesor.setActividadSeleccionada(actSeleccionada);
		}
	}

	public void mostrarVentanaSeleccion() {
		// TODO Auto-generated method stub
		if (currentScrollView.equals(TODOSESTUDIANTES) || currentScrollView.equals(LPS))
		{
			ventanaSeguimientoProfesor.mostrarVentanaProgresoEstudiante();
		}
		else if (currentScrollView.equals(ACTIVIDADES))
		{
			ventanaSeguimientoProfesor.mostrarActividadPendiente();
		}
	}
	
}
