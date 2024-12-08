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

@SuppressWarnings("serial")
public class VentanaVerLPCreados extends JFrame{

	private VentanaProfCreadorLP ventanaCreador;
	private Profesor prof;
	private PanelInfoPath pInfoPath;

	private PanelListaPathsVer listaPaths;
	
	public VentanaVerLPCreados(VentanaProfCreadorLP ventanaCreador, Profesor prof) throws HeadlessException {
		super();
		this.ventanaCreador = ventanaCreador;
		this.prof = prof;
		
		setLayout(new BorderLayout());
				
		PanelHeader header = new PanelHeader("Editar Learning Paths");
		add(header, BorderLayout.NORTH);
		
        listaPaths = new PanelListaPathsVer( this );
        add( listaPaths, BorderLayout.CENTER);
        
        pInfoPath = new PanelInfoPath(this);
        add(pInfoPath, BorderLayout.SOUTH);
        
        actualizarPathsCreador();
        
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
	

	public void cambiarPathSelected(LearningPath seleccionado) {
		pInfoPath.actualizarPath(seleccionado );
		
	}


    public void cerrarVentana( )
    {
    	ventanaCreador.setVisible(true);
        dispose( );
    }

	
}
