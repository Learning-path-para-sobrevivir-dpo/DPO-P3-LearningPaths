package gui.interfazProfesor;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.GUIManejoDatos;
import gui.PanelHeader;
import gui.VentanaPrincipal;
import gui.interfazProfesor.Creador.VentanaProfCreadorLP;
import gui.interfazProfesor.seguimiento.VentanaSeguimientoProfesor;
import modelo.LearningPath;
import modelo.Profesor;
import modelo.Progreso;
import modelo.actividades.Actividad;


@SuppressWarnings("serial")
public class VentanaProfesor extends JFrame{
	
	private VentanaPrincipal ventanaInicio;
	private VentanaProfCreadorLP ventanaCreador;
	private VentanaSeguimientoProfesor ventanaSeguimiento;
	
	private Profesor prof;
	private GUIManejoDatos datos;
	
	private PanelBotonesOpcionesProf panelBotones;
	
	
	public VentanaProfesor(Profesor p, GUIManejoDatos datos, VentanaPrincipal ventanaInicio) {
		// TODO Auto-generated constructor stub
		this.prof = p;
		this.datos = datos;
		this.ventanaInicio = ventanaInicio;
		setLayout(new BorderLayout());
		
		PanelHeader intro = new PanelHeader("Profesor(a), que desea hacer hoy: ");
		
		
		panelBotones = new PanelBotonesOpcionesProf(this);
		
		add(intro, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.CENTER);
		

		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 600, 400 );
        setTitle( "Profesor" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
	}

	
	
	public void mostrarVentanaCreador() {
		// TODO Auto-generated method stub
		if (ventanaCreador == null || !ventanaCreador.isVisible())
		{
			ventanaCreador = new VentanaProfCreadorLP(this, prof, datos);
			ventanaCreador.setVisible(true);
			setVisible(false);
		}
	}



	public void mostrarVentanaSeguimiento() {
		// TODO Auto-generated method stub
		if (ventanaSeguimiento == null || !ventanaSeguimiento.isVisible())
		{
			ventanaSeguimiento = new VentanaSeguimientoProfesor(this);
			ventanaSeguimiento.setVisible(true);
			setVisible(false);
		}
	}



	public void cerrarSesion() {
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(this, "Esta seguro de que quiere cerrar sesion?",
                "Cerrar Sesion", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION)
		{
			ventanaInicio.setVisible(true);
			dispose();
		}
		
	}
	
	public List<LearningPath> getLearningPathsCreados()
	{
		Map<String, LearningPath> lpCreados = prof.getLearningPathsCreados();
		List<LearningPath> lps = new ArrayList<LearningPath>();
		for(String nomPath: lpCreados.keySet())
		{
			LearningPath path = lpCreados.get(nomPath);
			lps.add(path);
		}
		return lps;
	}
	
	public List<Progreso> getEstudiantesPorLP(LearningPath path)
	{
		List<Progreso> progresos = new ArrayList<Progreso>();
		Map<String, Progreso>progresosLp = path.getProgresosEstudiantiles();
		Progreso progreso;
		for (String nomProg: progresosLp.keySet())
		{
			progreso = progresosLp.get(nomProg);
			progresos.add(progreso);
		}
		return progresos;
	}
	
	public List<Progreso> getProgresosEstudiantes()
	{
		List<LearningPath> paths = this.getLearningPathsCreados();
		List<Progreso> progresos = new ArrayList<Progreso>();
		for (LearningPath path: paths)
		{
			progresos.addAll(this.getEstudiantesPorLP(path));
		}
		return progresos;
	}
	
	public Map<Actividad, Progreso> getActividadesPendientesCalificar()
	{
		List<Progreso> progresos = this.getProgresosEstudiantes();
		Map<Actividad, Progreso> actividades = new HashMap<Actividad, Progreso>();
		List<Actividad> completadas;
		for (Progreso p: progresos)
		{
			completadas = p.getActCompletadas();
			for (Actividad actCompletada: completadas)
			{
				if (actCompletada.getEstado().equals("Sin completar") || actCompletada.getEstado().equals("No Exitosa") )
				{
					actCompletada.setEstudiante(p.getEstudiante());
					actividades.put(actCompletada, p);
				}
			}
		}
		
		return actividades;
	}

	public void calificarActividad(Actividad actividad, Progreso progreso) {
		// TODO Auto-generated method stub
		if (progreso != null)
		{
			if (progreso.getActividadesPath().containsValue(actividad))
			{
				if (actividad.getEstado().equals("No Exitosa"))
				{
					progreso.descompletarActividad(actividad);
				}
				
				datos.actualizarProgreso(progreso);
				datos.actualizarActividad(actividad);
			}
			else
			{
				ventanaSeguimiento.problemaCalificando();
			}
		}
		else
			ventanaSeguimiento.problemaCalificando();
	}
}
