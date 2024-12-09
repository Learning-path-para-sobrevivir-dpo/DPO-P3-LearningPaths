package gui.interfazProfesor.seguimiento.verProgreso;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class PanelListaActividades extends JPanel implements ListSelectionListener {
	
	private JList<Actividad> listaActividades;
	private DefaultListModel<Actividad> modeloActividades;
	
	private VentanaProgresoEstudiante ventanaProgreso;
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		Actividad  act = listaActividades.getSelectedValue();
		ventanaProgreso.setActividadSeleccionada(act);
	}

}
