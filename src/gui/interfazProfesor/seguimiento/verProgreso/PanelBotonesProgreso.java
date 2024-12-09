package gui.interfazProfesor.seguimiento.verProgreso;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PanelBotonesProgreso extends JPanel implements ActionListener {
	
	private static final String PENDIENTES = "pendientes";
	private static final String PENDOBLIG = "pendientes obligatorias";
	private static final String PROGRESO = "en progreso";
	private static final String COMPLETADAS = "completadas";
	private static final String COMPLOBLIG = "completadas obligatorias";
	private static final String REGRESAR = "regresar";
	
	private VentanaProgresoEstudiante ventanaProgreso;
	
	private JButton butVerActividadesPendientes;
	private JButton butVerActividadesPendientesObligatorias;
	private JButton butVerActividadProgreso;
	private JButton butVerActividadesCompletadas;
	private JButton butVerActividadesCompletadasObligatorias;
	private JButton butRegresar;
	
	
	public PanelBotonesProgreso(VentanaProgresoEstudiante ventanaProgreso) {
		this.ventanaProgreso = ventanaProgreso;
		setLayout(new GridLayout(6,1,20,20));
		setBorder(new EmptyBorder(50,100,50,50));
		
		butVerActividadProgreso = new JButton("Ver actividad en Progreso");
		butVerActividadProgreso.addActionListener(this);
		butVerActividadProgreso.setActionCommand(PROGRESO);
		butVerActividadProgreso.setFont(new Font("Calibri", Font.BOLD, 15));
		
		butVerActividadesPendientes = new JButton("Ver actividades pendientes");
		butVerActividadesPendientes.addActionListener(this);
		butVerActividadesPendientes.setActionCommand(PENDIENTES);
		butVerActividadesPendientes.setFont(new Font("Calibri", Font.BOLD, 15));
		
		butVerActividadesPendientesObligatorias = new JButton("Ver actividades pendientes obligatorias");
		butVerActividadesPendientesObligatorias.addActionListener(this);
		butVerActividadesPendientesObligatorias.setActionCommand(PENDOBLIG);
		butVerActividadesPendientesObligatorias.setFont(new Font("Calibri", Font.BOLD, 15));
		
		butVerActividadesCompletadas = new JButton("Ver actividades completadas");
		butVerActividadesCompletadas.addActionListener(this);
		butVerActividadesCompletadas.setActionCommand(COMPLETADAS);
		butVerActividadesCompletadas.setFont(new Font("Calibri", Font.BOLD, 15));
		
		butVerActividadesCompletadasObligatorias = new JButton("Ver actividades completadas obligatorias");
		butVerActividadesCompletadasObligatorias.addActionListener(this);
		butVerActividadesCompletadasObligatorias.setActionCommand(COMPLOBLIG);
		butVerActividadesCompletadasObligatorias.setFont(new Font("Calibri", Font.BOLD, 15));
		
		butRegresar = new JButton("Regresar");
		butRegresar.addActionListener(this);
		butRegresar.setActionCommand(REGRESAR);
		butRegresar.setFont(new Font("Calibri", Font.BOLD, 15));
		
		add(butVerActividadProgreso);
		add(butVerActividadesPendientes);
		add(butVerActividadesPendientesObligatorias);
		add(butVerActividadesCompletadas);
		add(butVerActividadesCompletadasObligatorias);
		add(butRegresar);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		if (comando.equals(PENDIENTES))
		{
			ventanaProgreso.mostrarPendientes();
		}
		else if (comando.equals(PENDOBLIG))
		{
			ventanaProgreso.mostrarObligatoriasPendientes();
		}
		else if (comando.equals(COMPLETADAS))
		{
			ventanaProgreso.mostrarCompletadas();
		}
		else if (comando.equals(COMPLOBLIG))
		{
			ventanaProgreso.mostrarObligatoriasCompletadas();
		}
		else if (comando.equals(PROGRESO))
		{
			ventanaProgreso.mostrarActividadProgreso();
		}
		else if (comando.equals(REGRESAR))
		{
			ventanaProgreso.cerrar();
		}
	}

}
