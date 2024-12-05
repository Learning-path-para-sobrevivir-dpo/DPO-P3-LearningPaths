package gui.interfazProfesor.seguimiento;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import gui.PanelHeader;
import gui.interfazProfesor.VentanaProfesor;

@SuppressWarnings("serial")
public class VentanaSeguimientoProfesor extends JFrame {
	private VentanaProfesor ventanaProf;
	private PanelHeader header;
	private PanelBotonesSeguimiento panelBotones;

	public VentanaSeguimientoProfesor(VentanaProfesor ventanaProf) throws HeadlessException {
		this.ventanaProf = ventanaProf;
		setLayout(new BorderLayout());
		
		header = new PanelHeader("Seguimiento de estudiantes");
		
		panelBotones = new PanelBotonesSeguimiento(this);
		
		add(header, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.EAST);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 1000, 700 );
        setTitle( "Seguimiento de Estudiantes" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
	}

	public void regresar() {
		// TODO Auto-generated method stub
		ventanaProf.setVisible(true);
		dispose();
	}
	
	
}
