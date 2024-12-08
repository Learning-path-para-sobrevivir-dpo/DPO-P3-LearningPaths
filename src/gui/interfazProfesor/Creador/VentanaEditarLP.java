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
import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class VentanaEditarLP extends JFrame{

	private VentanaProfCreadorLP ventanaCreador;
	
	private PanelListaPaths listaPaths; 
	private Profesor prof;
	private PanelEditarLP pEditar;
	private VentanaAddActividad ventanaAddAct;
	private GUIManejoDatos datos;
	private PanelListActsLP listaActs;


	public VentanaEditarLP(VentanaProfCreadorLP ventanaCreador, Profesor prof, GUIManejoDatos datos) throws HeadlessException {
		super();
		this.ventanaCreador = ventanaCreador;
		this.prof = prof;
		this.datos = datos;
		
		setLayout(new BorderLayout());
				
		PanelHeader header = new PanelHeader("Editar Learning Paths");
		add(header, BorderLayout.NORTH);
		
        listaPaths = new PanelListaPaths( this);
        add( listaPaths, BorderLayout.CENTER);
        
        pEditar = new PanelEditarLP(this);
        add(pEditar, BorderLayout.SOUTH);
        
        listaActs = new PanelListActsLP(this);
        add(listaActs, BorderLayout.EAST);
        
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
	
	@SuppressWarnings("unused")
	public void actualizarActsPath(LearningPath path) {
		
		List<Actividad> listActs = new ArrayList<Actividad>();
		
		Map<Integer,Actividad> actsPath = path.getActividades();
		
		for (Actividad act: actsPath.values())
		{

			listActs.add(act);
		}
			
		if (actsPath.isEmpty()) {
			
			listActs = new ArrayList<Actividad>();
			
		}
		
		if (path == null) {
			
			listActs = new ArrayList<Actividad>();
			
		}
		
		listaActs.actualizarActs(listActs);
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
		
		LearningPath path = listaPaths.getPathSelected();
		String nombre = pEditar.getNombre(); 
		String descripcion = pEditar.getDescripcion(); 
		String objetivo = pEditar.getObjetivo();
		int dificultad = pEditar.getDificultad();
		  
		if (!nombre.equals("")) {
			path.setTitulo(nombre);
			  
		 }
		if(!descripcion.equals("")) {
			path.setDescripcion(descripcion);
		}if(!objetivo.equals("")) {
			path.setObjetivo(objetivo);
		}
			
		path.setNivelDificultad(dificultad);
		
		datos.actualizarLearningPath(path);
	}
	
	public void mostrarVentanaAddAct() {
		// TODO Auto-generated method stub
		if (ventanaAddAct == null || !ventanaAddAct.isVisible())
		{
			ventanaAddAct = new VentanaAddActividad(this, prof, datos, listaPaths.getPathSelected());
			ventanaAddAct.setVisible(true);
			setVisible(false);
		}
	}
	
    public void cerrarVentana( )
    {
    	ventanaCreador.setVisible(true);
        dispose( );
    }

	public void eliminarAct(int pos) {
		// TODO Auto-generated method stub
		LearningPath pathSelected = listaPaths.getPathSelected();
		
		pathSelected.eliminarActividadPorPos(pos);
		datos.actualizarLearningPath(pathSelected);

	}

	
	
}

