package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import gui.GUIManejoDatos;
import gui.PanelHeader;
import modelo.LearningPath;
import modelo.Profesor;



@SuppressWarnings("serial")
public class VentanaCrearLP extends JFrame{
	
	private VentanaProfCreadorLP ventanaCreador;
	
	private PanelBotonesCrearLP pBotones;
	private PanelDetallesCrearLP pDetalles;
	private Profesor prof;
	private GUIManejoDatos datos;
	
	
	public VentanaCrearLP(VentanaProfCreadorLP ventanaCreador, Profesor prof, GUIManejoDatos datos) {
		super();
		this.ventanaCreador = ventanaCreador;
		this.prof = prof;
		this.datos = datos;

		setLayout(new BorderLayout());
		
		PanelHeader header = new PanelHeader("Crear Learning Path");
		add(header, BorderLayout.NORTH);
		
        pDetalles = new PanelDetallesCrearLP();
        add(pDetalles);
        
        pBotones = new PanelBotonesCrearLP(this);
        add(pBotones, BorderLayout.SOUTH);

		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( true );
        setSize( 500, 400 );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

		
	}

	   public void agregarLearningPath() {
		  String nombre = pDetalles.getNombre(); 
		  String descripcion = pDetalles.getDescripcion(); 
		  String objetivo = pDetalles.getObjetivo();
		  int dificultad = pDetalles.getDificultad();
		  
		  LearningPath path = prof.crearLearningPath(nombre, descripcion, objetivo, dificultad);
		  datos.actualizarUsuario(prof);
		  datos.addLearningPath(path);

	   }

	    /**
	     * Cierra la ventana sin crear un nuevo restaurante
	     */
	    public void cerrarVentana( )
	    {
	    	ventanaCreador.setVisible(true);
	        dispose( );
	    }

	
}
