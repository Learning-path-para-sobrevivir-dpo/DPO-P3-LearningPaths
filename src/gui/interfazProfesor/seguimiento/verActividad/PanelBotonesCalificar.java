package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBotonesCalificar extends JPanel implements ActionListener {
	
	private static final String CANCELAR = "cancelar";
	private static final String CALIFICAR = "calificar";
	
	private VentanaCalificarActividad ventanaCalificar;
	private JButton butCancelar;
	private JButton butCalificar;
	
	public PanelBotonesCalificar(VentanaCalificarActividad ventanaCalificar) {
		this.ventanaCalificar = ventanaCalificar;
		setLayout(new FlowLayout());
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
