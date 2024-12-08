package gui.interfazProfesor.seguimiento;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBotonesListaSeleccion extends JPanel implements ActionListener {
	
	private static final String VER = "ver";
	private JButton butVerSeleccion;
	private PanelListaSeguimiento panelLista;
	
	public PanelBotonesListaSeleccion(PanelListaSeguimiento panelLista) {
		this.panelLista = panelLista;
		setLayout(new FlowLayout());
		
		butVerSeleccion = new JButton("Ver");
		butVerSeleccion.addActionListener(this);
		butVerSeleccion.setActionCommand(VER);
		butVerSeleccion.setFont(new Font("Calibri", Font.BOLD, 15));
		
		add(butVerSeleccion);
		setVisible(false);
	}

	public void cambiarTextoBoton(String texto)
	{
		butVerSeleccion.setText(texto);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		if (comando.equals(VER))
		{
			panelLista.mostrarVentanaSeleccion();
		}
	}

}
