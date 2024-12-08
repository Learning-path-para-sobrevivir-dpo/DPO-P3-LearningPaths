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
	private PanelBotonesActividadPendiente panelBotones;
	private PanelDetallesActividad panelActividad;
	private PanelHeader header;
	private VentanaCalificarActividad ventanaCalificar;
	
	private Actividad actividad;
	
	public VentanaActividadPendienteCalificar(VentanaSeguimientoProfesor ventanaSeguimiento, Actividad actividad) throws HeadlessException {
		this.ventanaSeguimiento = ventanaSeguimiento;
		this.actividad = actividad;
		setLayout(new BorderLayout());
		
		header = new PanelHeader("Por calificar: ");
		panelActividad = new PanelDetallesActividad(actividad);
		JScrollPane scroll = new JScrollPane(panelActividad);
		scroll.createVerticalScrollBar();
		
		panelBotones = new PanelBotonesActividadPendiente(this);
		
		add(header, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setSize( 650, 500 );
        setTitle( "Ver Actividad Pendiente por Calificar" );
        setLocationRelativeTo( null );
	}

	public void cerrar() {
		// TODO Auto-generated method stub
		dispose();
	}

	public void mostrarVentanaCalificarActividad() {
		// TODO Auto-generated method stub
		if (ventanaCalificar == null || !ventanaCalificar.isVisible())
		{
			ventanaCalificar = new VentanaCalificarActividad(this, actividad);
			ventanaCalificar.setVisible(true);
		}
	}
	
	
}
