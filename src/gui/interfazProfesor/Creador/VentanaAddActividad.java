package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.GUIManejoDatos;
import modelo.LearningPath;
import modelo.Profesor;
import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class VentanaAddActividad extends JFrame{
	
	private VentanaEditarLP ventanaEditar;
	private PanelListActsSistema actsSistema;
	private GUIManejoDatos datos;
    private PanelDetallesActSistema pDetalles;
    private Profesor prof;
    private LearningPath pathSelected;


	public VentanaAddActividad(VentanaEditarLP ventanaEditar,Profesor prof, GUIManejoDatos datos, LearningPath pathSelected) throws HeadlessException {
		super();
		this.ventanaEditar = ventanaEditar;
		this.datos = datos;
		this.pathSelected = pathSelected;
		this.prof=prof;
		
        actsSistema = new PanelListActsSistema(this);
        add( actsSistema, BorderLayout.CENTER);
        
        pDetalles = new PanelDetallesActSistema(this);
        add( pDetalles, BorderLayout.SOUTH );
        
        mostrarActsSistema();
        
		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 900, 600 );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
        
	    
		
	}
	
	
	public void mostrarActsSistema() {
		
		List<Actividad> listActs = datos.getActividadesSistema();
		
		actsSistema.mostrarActs(listActs);
	}

	public void cambiarActSelected(Actividad seleccionado) {
		pDetalles.actualizarActividad(seleccionado );
		
	}

	public void addActividad(String nomPath) {
	    // Obtén el LearningPath seleccionado
	    Map<String, LearningPath> lps = prof.getLearningPathsCreados();
	    if (!lps.isEmpty()) {
	        for (LearningPath path : lps.values()) {
	            if (path.getTitulo().equals(nomPath)) {
	                pathSelected = path;
	                break;
	            }
	        }
	    }
	    
	    if (pathSelected == null) {
	        JOptionPane.showMessageDialog(this, "Learning Path no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    // Obtén la actividad seleccionada
	    Actividad actAdd = actsSistema.getActSelected();
	    if (actAdd == null) {
	        JOptionPane.showMessageDialog(this, "No hay actividad seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    // Solicita la posición al usuario
	    String input = JOptionPane.showInputDialog(this, "Ingrese la posición de la actividad a añadir (-1 para última):");
	    if (input == null) return; // Cancelar
	    int pos;
	    try {
	        pos = Integer.parseInt(input);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Entrada inválida para la posición.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Añade la actividad al LearningPath
	    try {
	        pathSelected.addActividadPorPos(actAdd, pos);
	        datos.addLearningPath(pathSelected);
	        JOptionPane.showMessageDialog(this, "Actividad añadida exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

	        // Actualiza la interfaz de VentanaEditarLP
	        datos.actualizarLearningPath(pathSelected); // Implementa este método en VentanaEditarLP
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Error al añadir la actividad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
    public void cerrarVentana( )
    {
    	ventanaEditar.setVisible(true);
        dispose( );
    }
	
	
	
	

}
