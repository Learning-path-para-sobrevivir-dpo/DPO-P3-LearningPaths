package gui.interfazProfesor.seguimiento;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.PanelHeader;
import gui.interfazProfesor.VentanaProfesor;

@SuppressWarnings("serial")
public class VentanaSeguimientoProfesor extends JFrame {
	private VentanaProfesor ventanaProf;
	private PanelHeader header;
	private PanelBotonesSeguimiento panelBotones;
	private PanelListaSeguimiento panelLista;

	public VentanaSeguimientoProfesor(VentanaProfesor ventanaProf) throws HeadlessException {
		this.ventanaProf = ventanaProf;
		setLayout(new BorderLayout());
		
		header = new PanelHeader("Seguimiento de estudiantes");
		
		panelBotones = new PanelBotonesSeguimiento(this);
		panelLista = new PanelListaSeguimiento(this);
		
//		JPanel panelAbajo = new JPanel();
//		panelAbajo.setBorder(new EmptyBorder(50, 0, 50, 0));
		
		add(header, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.WEST);
		add(panelLista, BorderLayout.CENTER);
		//add(panelAbajo, BorderLayout.SOUTH);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 1000, 500 );
        setTitle( "Seguimiento de Estudiantes" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
	}

	public void regresar() {
		// TODO Auto-generated method stub
		ventanaProf.setVisible(true);
		dispose();
	}

	public void verEstudiantesTodos() {
		// TODO Auto-generated method stub
		
	}

	public void verEstudiantesLP() {
		// TODO Auto-generated method stub
		
	}

	public void verActividadesPendientes() {
		// TODO Auto-generated method stub
		
	}
	
	
}
