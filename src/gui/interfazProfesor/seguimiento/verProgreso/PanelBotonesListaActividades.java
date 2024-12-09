package gui.interfazProfesor.seguimiento.verProgreso;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBotonesListaActividades extends JPanel implements ActionListener {

	private static final String VER = "ver";
	
	private JButton butVerActividad;
	private PanelListaActividades panelLista;
	
	public PanelBotonesListaActividades(PanelListaActividades panelLista) {
		this.panelLista = panelLista;
		setLayout(new FlowLayout());
		
		butVerActividad = new JButton("Ver Actividad");
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
