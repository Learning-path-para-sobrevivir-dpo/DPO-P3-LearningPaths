package gui.interfazProfesor.seguimiento;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import modelo.LearningPath;
import modelo.Progreso;

@SuppressWarnings("serial")
public class PanelListaSeguimiento extends JPanel {
	
	private JList<Progreso> listaProgresos;
	private DefaultListModel<Progreso> modeloProgreso;
	
	private JList<LearningPath> listaLearningPaths;
	private DefaultListModel<LearningPath> modeloLp;
	
	private VentanaSeguimientoProfesor ventanaSeguimientoProfesor;
	private JScrollPane scroll;
	
	public PanelListaSeguimiento(VentanaSeguimientoProfesor ventanaSeguimientoProfesor) {
		this.ventanaSeguimientoProfesor = ventanaSeguimientoProfesor;
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(20, 50, 20, 50));
		
		listaProgresos = new JList<Progreso>();
		listaProgresos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloProgreso = new DefaultListModel<Progreso>();
		
		listaLearningPaths = new JList<LearningPath>();
		listaLearningPaths.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloLp = new DefaultListModel<LearningPath>();
		
		scroll = new JScrollPane();
		scroll.setBounds(20, 120,500, 500);
		scroll.setViewportView(listaProgresos);
		
		add(scroll);
	}
	
}
