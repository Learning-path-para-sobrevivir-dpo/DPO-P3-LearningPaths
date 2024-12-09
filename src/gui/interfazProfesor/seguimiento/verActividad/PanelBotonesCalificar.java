package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.FlowLayout;
import java.awt.Font;
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
		
		butCancelar = new JButton("Cancelar");
		butCancelar.setActionCommand(CANCELAR);
		butCancelar.addActionListener(this);
		butCancelar.setFont(new Font("Calibri", Font.BOLD, 15));
		
		butCalificar = new JButton("Calificar");
		butCalificar.setActionCommand(CALIFICAR);
		butCalificar.addActionListener(this);
		butCalificar.setFont(new Font("Calibri", Font.BOLD, 15));
		
		add(butCancelar);
		add(butCalificar);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
