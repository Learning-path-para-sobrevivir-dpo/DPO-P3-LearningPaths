package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import gui.PanelHeader;
import gui.interfazProfesor.seguimiento.VentanaSeguimientoProfesor;
import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class VentanaActividad extends JFrame {

	private VentanaSeguimientoProfesor ventanaSeguimiento;
	private PanelBotonesActividadPendiente panelBotones;
	private PanelDetallesActividad panelActividad;
	private PanelHeader header;
	private VentanaCalificarActividad ventanaCalificar;
	
	private Actividad actividad;
	
	public VentanaActividad(VentanaSeguimientoProfesor ventanaSeguimiento, Actividad actividad, String titulo) throws HeadlessException {
		this.ventanaSeguimiento = ventanaSeguimiento;
		this.actividad = actividad;
		setLayout(new BorderLayout());
		
		header = new PanelHeader(titulo);
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
        setTitle( "Ver Actividad" );
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

	public void calificarActividad() {
		// TODO Auto-generated method stub
		ventanaSeguimiento.calificarActividad(actividad);
		cerrar();
	}
	
	public void deshabilitarCalificar()
	{
		panelBotones.deshabilitarCalificar();
	}
}
