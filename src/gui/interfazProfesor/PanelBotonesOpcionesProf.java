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
	private static final String SEGUIMIENTO = "seguimiento";
	private static final String CERRAR = "cerrar sesion";
	
	private VentanaProfesor ventanaProf;
	private JButton butCreadorLP;
	private JButton butSeguimiento;
	private JButton butCerrarSesion;

	
	public PanelBotonesOpcionesProf(VentanaProfesor ventanaProf) {
		this.ventanaProf = ventanaProf;
		setLayout(new GridLayout(3,1,30,30));
		setBorder(new EmptyBorder(50,100,50,100));
		
		butCreadorLP = new JButton("Gestionar Learning Paths");
		butCreadorLP.addActionListener(this);
		butCreadorLP.setActionCommand(CREADOR);
		
		butSeguimiento = new JButton("Hacer seguimiento de mis estudiantes");
		butSeguimiento.addActionListener(this);
		butSeguimiento.setActionCommand(SEGUIMIENTO);
		
		butCerrarSesion = new JButton("Cerrar Sesión");
		butCerrarSesion.addActionListener(this);
		butCerrarSesion.setActionCommand(CERRAR);
		
		add(butCreadorLP);
		add(butSeguimiento);
		add(butCerrarSesion);
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		if (comando.equals(CREADOR))
		{
			ventanaProf.mostrarVentanaCreador();
		}
		else if (comando.equals(SEGUIMIENTO))
		{
			ventanaProf.mostrarVentanaSeguimiento();
		}
		else if (comando.equals(CERRAR))
		{
			ventanaProf.cerrarSesion();
		}
		
	}

}
