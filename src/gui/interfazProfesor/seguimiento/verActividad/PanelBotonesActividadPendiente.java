package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBotonesActividadPendiente extends JPanel implements ActionListener {
	
	private static final String CALIFICAR = "calificar";
	private static final String CERRAR = "cerrar";
	
	private VentanaActividad ventanaCalificar;
	private JButton butCalificar;
	private JButton butCerrar;
	
	
	
	public PanelBotonesActividadPendiente(VentanaActividad ventanaCalificar) {
		this.ventanaCalificar = ventanaCalificar;
		setLayout(new FlowLayout());
		
		butCalificar = new JButton("(Re)Calificar Actividad");
		butCalificar.addActionListener(this);
		butCalificar.setActionCommand(CALIFICAR);
		butCalificar.setFont(new Font("Calibri", Font.BOLD, 15));
		butCalificar.setVisible(true);
		
		butCerrar = new JButton("Cerrar");
		butCerrar.addActionListener(this);
		butCerrar.setActionCommand(CERRAR);
		butCerrar.setFont(new Font("Calibri", Font.BOLD, 15));
		
		add(butCerrar);
		add(butCalificar);
	}
	
	public void deshabilitarCalificar()
	{
		butCalificar.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		if (comando.equals(CERRAR))
		{
			ventanaCalificar.cerrar();
		}
		else if (comando.equals(CALIFICAR))
		{
			ventanaCalificar.mostrarVentanaCalificarActividad();
		}
	}

}
