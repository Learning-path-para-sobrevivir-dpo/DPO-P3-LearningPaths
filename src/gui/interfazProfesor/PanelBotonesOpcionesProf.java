package gui.interfazProfesor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.interfazProfesor.Creador.VentanaProfCreadorLP;

@SuppressWarnings("serial")
public class PanelBotonesOpcionesProf extends JPanel implements ActionListener {
	
	private static final String CREADOR = "creador";
	
	private VentanaProfesor ventanaProf;
	private JButton butCreadorLP;

	
	public PanelBotonesOpcionesProf(VentanaProfesor ventanaProf) {
		this.ventanaProf = ventanaProf;
		setLayout(new GridLayout(3,1,20,20));
		setBorder(new EmptyBorder(50,200,50,200));
		
		butCreadorLP = new JButton("Gestionar Learning Paths");
		butCreadorLP.addActionListener(this);
		butCreadorLP.setActionCommand(CREADOR);
		
		add(butCreadorLP);
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		if (comando.equals(CREADOR))
		{
			ventanaProf.mostrarVentanaCreador();
		}
		
	}

}
