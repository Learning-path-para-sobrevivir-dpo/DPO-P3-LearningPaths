package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import gui.PanelHeader;
import modelo.LearningPath;
import modelo.Profesor;

public class VentanaLPCreados extends JFrame{

	private VentanaProfCreadorLP ventanaCreador;
	
	private PanelListaPaths listaPaths; 
	private Profesor prof;


	public VentanaLPCreados(VentanaProfCreadorLP ventanaCreador, Profesor prof) throws HeadlessException {
		super();
		this.ventanaCreador = ventanaCreador;
		this.prof = prof;
		
		setLayout(new BorderLayout());
				
		PanelHeader header = new PanelHeader("Learning Paths Creados");
		add(header, BorderLayout.NORTH);
		
        listaPaths = new PanelListaPaths( this );
        add( listaPaths, BorderLayout.CENTER);
        
        
		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 900, 600 );
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

