package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBotonesCalificarPreguntas extends JPanel implements ActionListener {
	
	private static final String CANCELAR = "cancelar";
	private static final String CALIFICAR = "calificar";
	private static final String SIGUIENTE = "siguiente";
	
	private VentanaMostrarPreguntasExamen ventana;
	private JComboBox<String> marcar;
	private JButton butSiguiente;
	private JButton butCalificar;
	private JButton butCancelar;
	
	public PanelBotonesCalificarPreguntas(VentanaMostrarPreguntasExamen ventana) {
		this.ventana = ventana;
		setLayout(new FlowLayout());
		
		marcar = new JComboBox<String>(new String[] {"correcta", "incorrecta"});
		
		butSiguiente = new JButton("Siguiente");
		butSiguiente.setActionCommand(SIGUIENTE);
		butSiguiente.addActionListener(this);
		butSiguiente.setFont(new Font("Calibri", Font.BOLD, 15));
		
		butCancelar = new JButton("Cancelar");
		butCancelar.setActionCommand(CANCELAR);
		butCancelar.addActionListener(this);
		butCancelar.setFont(new Font("Calibri", Font.BOLD, 15));
		
		butCalificar = new JButton("Calificar");
		butCalificar.setActionCommand(CALIFICAR);
		butCalificar.addActionListener(this);
		butCalificar.setFont(new Font("Calibri", Font.BOLD, 15));
		
		add(marcar);
		add(butCancelar);
		add(butSiguiente);
		add(butCalificar);
	}

	public String getMarcar()
	{
		return (String) marcar.getSelectedItem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		if (comando.equals(CANCELAR))
		{
			ventana.cerrar();
		}
		else if (comando.equals(CALIFICAR))
		{
			ventana.calificar();
		}
		else if (comando.equals(SIGUIENTE))
		{
			ventana.siguiente();
		}
	}

}
