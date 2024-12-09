package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.GUIManejoDatos;
import gui.PanelHeader;
import modelo.Profesor;
import modelo.actividades.Tarea;

@SuppressWarnings("serial")
public class VentanaCrearActividad extends JFrame implements ActionListener {

	private VentanaProfCreadorLP ventanaCreador;
	private Profesor prof;
	private GUIManejoDatos datos;
	
	private JButton bTarea;
	private static final String TAREA = "tarea";

	private JButton bRecursoEducativo;
	private static final String RECED = "recurso educativo";

	private JButton bQuizMultiple;
	private static final String QUIZMUL = "quiz multiple";

	private JButton bQuizVF;
	private static final String QUIZVF = "quiz verdadero falso";

	private JButton bExamen;
	private static final String EXAMEN = "examen";
	
	private JButton bEncuesta;
	private static final String ENCU = "encuesta";

	private JPanel pCrear;

	public VentanaCrearActividad(VentanaProfCreadorLP ventanaCreador, Profesor prof, GUIManejoDatos datos) throws HeadlessException {
		super();
		this.ventanaCreador=ventanaCreador;
		this.prof = prof;
		this.datos = datos;
		
		setLayout(new BorderLayout());
		PanelHeader head = new PanelHeader("Crear Actividad");
		add(head, BorderLayout.NORTH);
		
		JPanel pBotones = new JPanel();
		
		pBotones.setLayout(new GridLayout(6,1));
		
		bTarea = new JButton("Crear Tarea");
		bTarea.addActionListener(this);
		bTarea.setActionCommand(TAREA);
		
		bRecursoEducativo = new JButton("Crear Recurso Educativo");
		bRecursoEducativo.addActionListener(this);
		bRecursoEducativo.setActionCommand(RECED);
		
		bQuizMultiple = new JButton("Crear Quiz opción múltiple");
		bQuizMultiple.addActionListener(this);
		bQuizMultiple.setActionCommand(QUIZMUL);
		
		bQuizVF = new JButton("Crear Quiz verdadero o falso");
		bQuizVF.addActionListener(this);
		bQuizVF.setActionCommand(QUIZVF);
		
		bExamen = new JButton("Crear Examen");
		bExamen.addActionListener(this);
		bExamen.setActionCommand(EXAMEN);
		
		bEncuesta = new JButton("Crear Encuesta");
		bEncuesta.addActionListener(this);
		bEncuesta.setActionCommand(ENCU);
		
		pBotones.add(bTarea);
		pBotones.add(bRecursoEducativo);
		pBotones.add(bQuizMultiple);
		pBotones.add(bQuizVF);
		pBotones.add(bExamen);
		pBotones.add(bEncuesta);
		
		this.add(pBotones, BorderLayout.WEST);
		
		pCrear = new JPanel();
		add(pCrear, BorderLayout.CENTER);

		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 1000, 700 );
        setTitle( "Gestion Learning Paths" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
	    
	}
	
	public void panelTarea() {
	    // Elimina el panel existente si ya hay uno
	    this.remove(pCrear);

	    // Crea y agrega el nuevo panel
	    pCrear = new PanelCrearTarea(this);
	    add(pCrear, BorderLayout.CENTER);

	    // Revalida y repinta el JFrame para reflejar los cambios
	    revalidate();
	    repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand( );
		
		if (comando.equals(TAREA))
		{
			panelTarea();
		}		
	}
	
	public void crearTarea() {
	    if (pCrear instanceof PanelCrearTarea) {
	        PanelCrearTarea panelTarea = (PanelCrearTarea) pCrear;

	        String nombre = panelTarea.getNombre();
	        String descripcion = panelTarea.getDescripcion();
	        int dificultad = panelTarea.getDificultad();
	        int duracion = Integer.parseInt(panelTarea.getDuracion());
	        boolean obligatorio = panelTarea.getObligatorio();
	        int tiempoSugerido = Integer.parseInt(panelTarea.getTiempo());
	        String contenido = panelTarea.getContenido();
	        String medioEntrega = panelTarea.getMedioEntrega();
	        String tipo = "Tarea";

	        // Llama al método del Profesor para crear la tarea
	        Tarea tarea= prof.crearTarea(nombre, descripcion, dificultad, duracion, obligatorio, tiempoSugerido, tipo, contenido, medioEntrega);

			datos.addActividad(tarea);
			datos.actualizarUsuario(prof);
	        
	        cerrarVentana();
	    } else {
	        System.err.println("Error: El panel actual no es PanelCrearTarea.");
	    }
	}

	
    public void cerrarVentana( )
    {
    	ventanaCreador.setVisible(true);
        dispose( );
    }



	

}
