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
public class VentanaEditarAct extends JFrame{

	private VentanaProfCreadorLP ventanaCreador;
	
	private PanelListActsSistema listaActs; 
	private Profesor prof;
	private PanelEditarAct pEditar;
	private GUIManejoDatos datos;

	public VentanaEditarAct(VentanaProfCreadorLP ventanaCreador, Profesor prof, GUIManejoDatos datos) throws HeadlessException {
		super();
		this.ventanaCreador = ventanaCreador;
		this.prof = prof;
		this.datos = datos;
		
		setLayout(new BorderLayout());
				
		PanelHeader header = new PanelHeader("Editar Actividad");
		add(header, BorderLayout.NORTH);

        
        pEditar = new PanelEditarAct(this);
        add(pEditar, BorderLayout.SOUTH);
        
        listaActs = new PanelListActsSistema(this);
        add(listaActs, BorderLayout.CENTER);
        
        actualizarActsCreadas();
        
		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 900, 600 );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
	    
	}
	
	public void actualizarActsCreadas() {
		
		List<Actividad> listActs = new ArrayList<Actividad>();
		
		List<Actividad> actsCreadas = prof.getActCreadas();
		
		for (Actividad act: actsCreadas)
		{

			listActs.add(act);
		}
		
		listaActs.mostrarActs(listActs);
	}
	
	
	
    public void cerrarVentana( )
    {
    	ventanaCreador.setVisible(true);
        dispose( );
    }


	public void cambiarActSelected(Actividad act) {
		// TODO Auto-generated method stub
		
	}

	public void guardarCambios() {
		
		Actividad act = listaActs.getActSelected();
		String nombre = pEditar.getNombre(); 
		String descripcion = pEditar.getDescripcion(); 
		int dificultad = pEditar.getDificultad();
		String duracion = pEditar.getDuracion();
		String tiempoSug = pEditar.getTiempo();
		boolean obligatorio = pEditar.getObligatorio();
		
		if (!nombre.equals("")) {
			act.setTitulo(nombre);
			  
		 }
		if(!duracion.equals("")) {
			act.setDuracionMin(Integer.parseInt(duracion));
		}
		if(!duracion.equals("")) {
			act.setTiempoCompletarSugerido(Integer.parseInt(tiempoSug));
		}
		if (!descripcion.equals(""))
		act.setNivelDificultad(dificultad);
		act.setObligatorio(obligatorio);
		
		datos.actualizarActividad(act);		
	}

	
	
}