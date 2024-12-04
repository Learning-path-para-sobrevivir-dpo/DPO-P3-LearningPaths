package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.GUIManejoDatos;
import gui.VentanaPrincipal;
import gui.interfazProfesor.VentanaProfesor;
import modelo.LearningPath;
import modelo.Profesor;

public class VentanaProfCreadorLP extends JFrame {
	
	private VentanaProfesor ventanaProf;
	
	private Profesor prof;
	private GUIManejoDatos datos;
	private PanelListaPaths listaPaths; 
	
	public VentanaProfCreadorLP(VentanaProfesor ventanaProf, Profesor prof, GUIManejoDatos datos)
			throws HeadlessException {
		super();
		this.ventanaProf = ventanaProf;
		this.prof = prof;
		this.datos = datos;
		setLayout(new BorderLayout());

		JPanel pBotones = new JPanel();
	    
		JButton bVerLPCreados = new JButton("Ver todos mis Learning Paths");
		JButton bCrearLP = new JButton("Crear Learning Path");
		JButton bVerReviews = new JButton("Ver reseñas");
		JButton bEditarLP = new JButton("Editar Learning Path");
		JButton bClonarAct = new JButton("Clonar actividad");
		JButton bCrearAct = new JButton("Crear actividad");
		JButton bEditarAct = new JButton("Editar Actividad");
		JButton bAddReview = new JButton("Añadir reseña a Actividad");
		
		pBotones.add(bVerLPCreados);
		pBotones.add(bCrearLP);
		pBotones.add(bCrearAct);
		pBotones.add(bClonarAct);
		pBotones.add(bEditarLP);
		pBotones.add(bEditarAct);
		pBotones.add(bVerReviews);
		pBotones.add(bAddReview);

		
		
		add(pBotones, BorderLayout.CENTER);
		
        listaPaths = new PanelListaPaths( this );
        add( listaPaths , BorderLayout.SOUTH);
        
		
		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 600, 400 );
        setTitle( "Bienvenidos" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
	    
	}
	
	public void actualizarPathsCreador() {
		
		List<LearningPath> listPaths = new ArrayList<LearningPath>();

		Map<String, LearningPath>lps = prof.getLearningPathsCreados();
		if (!lps.isEmpty())
		{
			
			for (LearningPath path: lps.values())
			{

				listPaths.add(path);
			}
			
		}
		
		listaPaths.actualizarLearningPaths(listPaths);
	}
	

	
}
