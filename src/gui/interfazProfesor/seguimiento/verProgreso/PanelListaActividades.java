package gui.interfazProfesor.seguimiento.verProgreso;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class PanelListaActividades extends JPanel implements ListSelectionListener {
	
	private JList<Actividad> listaActividades;
	private DefaultListModel<Actividad> modeloActividades;
	
	private VentanaProgresoEstudiante ventanaProgreso;
	private PanelBotonesListaActividades panelBotones;
	private JScrollPane scroll;
	
	public PanelListaActividades(VentanaProgresoEstudiante ventanaProgreso) {
		this.ventanaProgreso = ventanaProgreso;
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(20, 50, 20, 50));
		
		modeloActividades = new DefaultListModel<Actividad>();
		listaActividades = new JList<Actividad>(modeloActividades);
		listaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaActividades.addListSelectionListener(this);
		
		scroll = new JScrollPane();
		scroll.setBounds(20, 120,500, 500);
		scroll.setViewportView(listaActividades);
		
		panelBotones = new PanelBotonesListaActividades(this);
		
		add(scroll, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		Actividad  act = listaActividades.getSelectedValue();
		ventanaProgreso.setActividadSeleccionada(act);
	}

	public void verActividad() {
		// TODO Auto-generated method stub
		ventanaProgreso.verActividadSeleccionada();
	}
	
	public void actualizar(List<Actividad> actividades)
	{
		modeloActividades.clear();
		modeloActividades.addAll(actividades);
	}

}
