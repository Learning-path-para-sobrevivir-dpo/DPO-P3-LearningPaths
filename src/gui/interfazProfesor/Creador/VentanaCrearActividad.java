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
import modelo.actividades.Encuesta;
import modelo.actividades.Examen;
import modelo.actividades.QuizOpcionMultiple;
import modelo.actividades.QuizVerdaderoFalso;
import modelo.actividades.RecursoEducativo;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand( );
		
		if (comando.equals(TAREA))
		{
			panelTarea();
		} else if (comando.equals(RECED)){
			panelRec();
		} else if (comando.equals(QUIZMUL)){
			panelQuizMul();
		}else if (comando.equals(QUIZVF)){
			panelQuizVF();	
		}else if (comando.equals(EXAMEN)){
			panelExamen();	
		}else if (comando.equals(ENCU)){
			panelEncuesta();	
		}
	}
	
	private void panelEncuesta() {
	    this.remove(pCrear);

	    // Crea y agrega el nuevo panel
	    pCrear = new PanelCrearEncuesta(this);
	    add(pCrear, BorderLayout.CENTER);

	    // Revalida y repinta el JFrame para reflejar los cambios
	    revalidate();
	    repaint();			
	}

	private void panelExamen() {
	    this.remove(pCrear);

	    // Crea y agrega el nuevo panel
	    pCrear = new PanelCrearExamen(this);
	    add(pCrear, BorderLayout.CENTER);

	    // Revalida y repinta el JFrame para reflejar los cambios
	    revalidate();
	    repaint();			
	}

	private void panelQuizVF() {
	    // Elimina el panel existente si ya hay uno
	    this.remove(pCrear);

	    // Crea y agrega el nuevo panel
	    pCrear = new PanelCrearQuizVF(this);
	    add(pCrear, BorderLayout.CENTER);

	    // Revalida y repinta el JFrame para reflejar los cambios
	    revalidate();
	    repaint();	
		
	}

	private void panelQuizMul() {
	    // Elimina el panel existente si ya hay uno
	    this.remove(pCrear);

	    // Crea y agrega el nuevo panel
	    pCrear = new PanelCrearQuizMul(this);
	    add(pCrear, BorderLayout.CENTER);

	    // Revalida y repinta el JFrame para reflejar los cambios
	    revalidate();
	    repaint();		
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
	
	public void panelRec() {
	    // Elimina el panel existente si ya hay uno
	    this.remove(pCrear);

	    // Crea y agrega el nuevo panel
	    pCrear = new PanelCrearRecursoEducativo(this);
	    add(pCrear, BorderLayout.CENTER);

	    // Revalida y repinta el JFrame para reflejar los cambios
	    revalidate();
	    repaint();
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

	
	public void crearRecursoEducativo() {
	    if (pCrear instanceof PanelCrearRecursoEducativo) {
	    	PanelCrearRecursoEducativo panelRec = (PanelCrearRecursoEducativo) pCrear;

	        String nombre = panelRec.getNombre();
	        String descripcion = panelRec.getDescripcion();
	        int dificultad = panelRec.getDificultad();
	        int duracion = Integer.parseInt(panelRec.getDuracion());
	        boolean obligatorio = panelRec.getObligatorio();
	        int tiempoSugerido = Integer.parseInt(panelRec.getTiempo());
	        String contenido = panelRec.getContenido();
	        String tipo = "Recurso Educativo";
	        String tipoRecurso = panelRec.getTipoRecurso();
	        String enlace = panelRec.getEnlace();


	        // Llama al método del Profesor para crear la tarea
	    	RecursoEducativo recCreado = prof.crearRecursoEducativo(nombre, descripcion, dificultad, duracion, obligatorio, tiempoSugerido, tipo, tipoRecurso, contenido, enlace);

			datos.addActividad(recCreado);
			datos.actualizarUsuario(prof);
	        
	        cerrarVentana();
	    } else {
	        System.err.println("Error: El panel actual no es el esperado.");
	    }
	}
	
	
	public void crearQuizMul() {
	    if (pCrear instanceof PanelCrearQuizMul) {
	    	PanelCrearQuizMul panelAct = (PanelCrearQuizMul) pCrear;

	        String nombre = panelAct.getNombre();
	        String descripcion = panelAct.getDescripcion();
	        int dificultad = panelAct.getDificultad();
	        int duracion = Integer.parseInt(panelAct.getDuracion());
	        boolean obligatorio = panelAct.getObligatorio();
	        int tiempoSugerido = Integer.parseInt(panelAct.getTiempo());
	        String tipo = "Prueba";
	        String tipoPrueba = "Quiz Opcion Multiple";
	        float califMin = Float.parseFloat(panelAct.getCalifMin());


	        // Llama al método del Profesor para crear la tarea
			QuizOpcionMultiple quiz = prof.crearQuizMultiple(nombre, descripcion, dificultad, duracion, obligatorio, tiempoSugerido, tipo, tipoPrueba, califMin);

			datos.addActividad(quiz);
			datos.actualizarUsuario(prof);
	        
	        cerrarVentana();
	    } else {
	        System.err.println("Error: El panel actual no es el esperado.");
	    }
	}
	
	public void crearQuizVF() {
	    if (pCrear instanceof PanelCrearQuizVF) {
	    	PanelCrearQuizVF panelAct = (PanelCrearQuizVF) pCrear;

	        String nombre = panelAct.getNombre();
	        String descripcion = panelAct.getDescripcion();
	        int dificultad = panelAct.getDificultad();
	        int duracion = Integer.parseInt(panelAct.getDuracion());
	        boolean obligatorio = panelAct.getObligatorio();
	        int tiempoSugerido = Integer.parseInt(panelAct.getTiempo());
	        String tipo = "Prueba";
	        String tipoPrueba = "Quiz Verdadero Falso";
	        float califMin = Float.parseFloat(panelAct.getCalifMin());


	        // Llama al método del Profesor para crear la tarea
			QuizVerdaderoFalso quiz = prof.crearQuizVoF(nombre, descripcion, dificultad, duracion, obligatorio, tiempoSugerido, tipo, tipoPrueba, califMin);

			datos.addActividad(quiz);
			datos.actualizarUsuario(prof);
	        
	        cerrarVentana();
	    } else {
	        System.err.println("Error: El panel actual no es el esperado.");
	    }
	}
	
	public void crearExamen() {
	    if (pCrear instanceof PanelCrearExamen) {
	    	PanelCrearExamen panelAct = (PanelCrearExamen) pCrear;

	        String nombre = panelAct.getNombre();
	        String descripcion = panelAct.getDescripcion();
	        int dificultad = panelAct.getDificultad();
	        int duracion = Integer.parseInt(panelAct.getDuracion());
	        boolean obligatorio = panelAct.getObligatorio();
	        int tiempoSugerido = Integer.parseInt(panelAct.getTiempo());
	        String tipo = "Prueba";
	        String tipoPrueba = "Examen";

			Examen exam = prof.crearExamen(nombre, descripcion, dificultad, duracion, obligatorio, tiempoSugerido, tipo, tipoPrueba);

			datos.addActividad(exam);
			datos.actualizarUsuario(prof);
	        
	        cerrarVentana();
	    } else {
	        System.err.println("Error: El panel actual no es el esperado.");
	    }
	}

	public void crearEncuesta() {
	    if (pCrear instanceof PanelCrearExamen) {
	    	PanelCrearExamen panelAct = (PanelCrearExamen) pCrear;

	        String nombre = panelAct.getNombre();
	        String descripcion = panelAct.getDescripcion();
	        int dificultad = panelAct.getDificultad();
	        int duracion = Integer.parseInt(panelAct.getDuracion());
	        boolean obligatorio = panelAct.getObligatorio();
	        int tiempoSugerido = Integer.parseInt(panelAct.getTiempo());
	        String tipo = "Prueba";
	        String tipoPrueba = "Encuesta";

	    	Encuesta encuesta = prof.crearEncuesta(nombre, descripcion, dificultad, duracion, obligatorio, tiempoSugerido, tipo, tipoPrueba);

			datos.addActividad(encuesta);
			datos.actualizarUsuario(prof);
	        
	        cerrarVentana();
	    } else {
	        System.err.println("Error: El panel actual no es el esperado.");
	    }
	}

	
    public void cerrarVentana( )
    {
    	ventanaCreador.setVisible(true);
        dispose( );
    }



	

}
