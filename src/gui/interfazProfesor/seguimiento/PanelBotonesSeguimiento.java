package gui.interfazProfesor.seguimiento;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PanelBotonesSeguimiento extends JPanel implements ActionListener {
	
	private static final String VERTODOS = "ver todos";
	private static final String VERESTLP = "ver estudiantes de un lp";
	private static final String VERACTPENDIENTES = "ver actividades pendientes";
	private static final String REGRESAR = "regresar";
	
	private VentanaSeguimientoProfesor ventanaSeg;
	
	private JButton butVerTodosEstudiantes;
	private JButton butVerEstudiantesLP;
	private JButton butVerActPendientesCalificar;
	private JButton butRegresar;
	
	
	
	public PanelBotonesSeguimiento(VentanaSeguimientoProfesor ventanaSeg) {
		this.ventanaSeg = ventanaSeg;
		setLayout(new GridLayout(4,1,30,30));
		setBorder(new EmptyBorder(50,100,50,50));
		
		butVerTodosEstudiantes = new JButton("Ver todos mis estudiantes");
		butVerTodosEstudiantes.addActionListener(this);
		butVerTodosEstudiantes.setActionCommand(VERTODOS);
		butVerTodosEstudiantes.setFont(new Font("Calibri", Font.BOLD, 15));
		
		butVerEstudiantesLP = new JButton("Ver los estudiantes inscritos \n a un Learning Path");
		butVerEstudiantesLP.addActionListener(this);
		butVerEstudiantesLP.setActionCommand(VERESTLP);
		butVerEstudiantesLP.setFont(new Font("Calibri", Font.BOLD, 15));
		
		butVerActPendientesCalificar = new JButton("Ver actividades pendientes por calificar");
		butVerActPendientesCalificar.addActionListener(this);
		butVerActPendientesCalificar.setActionCommand(VERACTPENDIENTES);
		butVerActPendientesCalificar.setFont(new Font("Calibri", Font.BOLD, 15));
		
		butRegresar = new JButton("Regresar al menu de opciones");
		butRegresar.addActionListener(this);
		butRegresar.setActionCommand(REGRESAR);
		butRegresar.setFont(new Font("Calibri", Font.BOLD, 15));
		
		add(butVerTodosEstudiantes);
		add(butVerEstudiantesLP);
		add(butVerActPendientesCalificar);
		add(butRegresar);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		if (comando.equals(VERTODOS))
		{
			ventanaSeg.verEstudiantesTodos();
		}
		else if (comando.equals(VERESTLP))
		{
			ventanaSeg.verEstudiantesLP();
		}
		else if (comando.equals(VERACTPENDIENTES))
		{
			ventanaSeg.verActividadesPendientes();
		}
		else if (comando.equals(REGRESAR))
		{
			ventanaSeg.regresar();
		}
	}

}
