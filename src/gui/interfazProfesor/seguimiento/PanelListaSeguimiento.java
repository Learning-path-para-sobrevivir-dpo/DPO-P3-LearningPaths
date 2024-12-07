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
	
	private JList<Progreso> listaProgresos;
	private DefaultListModel<Progreso> modeloProgreso;
	
	private JList<LearningPath> listaLearningPaths;
	private DefaultListModel<LearningPath> modeloLp;
	
	private JList<Actividad> listaActividades;
	private DefaultListModel<Actividad> modeloActividad;
	
	private VentanaSeguimientoProfesor ventanaSeguimientoProfesor;
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
		
		scroll = new JScrollPane();
		scroll.setBounds(20, 120,500, 500);
		scroll.setViewportView(listaProgresos);
		
		add(scroll);
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
	}

	public void mostrarLps() {
		// TODO Auto-generated method stub
		scroll.setViewportView(listaLearningPaths);
	}

	public void mostrarActividades() {
		// TODO Auto-generated method stub
		scroll.setViewportView(listaActividades);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
