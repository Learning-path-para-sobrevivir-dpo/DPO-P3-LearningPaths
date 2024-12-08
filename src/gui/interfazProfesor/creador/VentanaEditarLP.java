package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import gui.GUIManejoDatos;
import gui.PanelHeader;
import modelo.LearningPath;
import modelo.Profesor;

@SuppressWarnings("serial")
public class VentanaEditarLP extends JFrame{

	private VentanaProfCreadorLP ventanaCreador;
	
	private PanelListaPaths listaPaths; 
	private Profesor prof;
	private PanelEditarLP pEditar;
	private VentanaAddActividad ventanaAddAct;
	private GUIManejoDatos datos;


	public VentanaEditarLP(VentanaProfCreadorLP ventanaCreador, Profesor prof, GUIManejoDatos datos) throws HeadlessException {
		super();
		this.ventanaCreador = ventanaCreador;
		this.prof = prof;
		this.datos = datos;
		
		setLayout(new BorderLayout());
				
		PanelHeader header = new PanelHeader("Editar Learning Paths");
		add(header, BorderLayout.NORTH);
		
        listaPaths = new PanelListaPaths(  );
        add( listaPaths, BorderLayout.CENTER);
        
        pEditar = new PanelEditarLP(this);
        add(pEditar, BorderLayout.SOUTH);
        
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
	
	public void guardarCambios() {
		
	}
	
	public void mostrarVentanaAddAct() {
		// TODO Auto-generated method stub
		if (ventanaAddAct == null || !ventanaAddAct.isVisible())
		{
			ventanaAddAct = new VentanaAddActividad(this, datos);
			ventanaAddAct.setVisible(true);
			setVisible(false);
		}
	}
	
    public void cerrarVentana( )
    {
    	ventanaCreador.setVisible(true);
        dispose( );
    }

	
	
}

