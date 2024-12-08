package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import gui.PanelHeader;
import gui.interfazProfesor.seguimiento.VentanaSeguimientoProfesor;
import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class VentanaActividadPendienteCalificar extends JFrame {

	private VentanaSeguimientoProfesor ventanaSeguimiento;
	private PanelBotonesCalificar panelBotones;
	private PanelDetallesActividad panelActividad;
	private PanelHeader header;
	
	private Actividad actividad;
	
	public VentanaActividadPendienteCalificar(VentanaSeguimientoProfesor ventanaSeguimiento, Actividad actividad) throws HeadlessException {
		this.ventanaSeguimiento = ventanaSeguimiento;
		this.actividad = actividad;
		setLayout(new BorderLayout());
		
		header = new PanelHeader("Por calificar: ");
		panelActividad = new PanelDetallesActividad(actividad);
		JScrollPane scroll = new JScrollPane(panelActividad);
		scroll.createVerticalScrollBar();
		
		panelBotones = new PanelBotonesCalificar(this);
		
		add(header, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setSize( 650, 500 );
        setTitle( "Ver Actividad Pendiente por Calificar" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
	}

	public void cerrar() {
		// TODO Auto-generated method stub
		dispose();
	}
	
	
}
