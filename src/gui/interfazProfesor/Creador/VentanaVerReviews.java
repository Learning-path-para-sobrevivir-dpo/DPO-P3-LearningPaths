package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.GUIManejoDatos;
import gui.PanelHeader;
import modelo.LearningPath;
import modelo.Profesor;
import modelo.Review;
import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class VentanaVerReviews extends JFrame{

	private VentanaProfCreadorLP ventanaCreador;
	private Profesor prof;
	private GUIManejoDatos datos;

	private PanelListActsSistema actsSistema;
	private JPanel panelReviews; 
	
	public VentanaVerReviews(VentanaProfCreadorLP ventanaCreador, Profesor prof, GUIManejoDatos datos) throws HeadlessException {
		super();
		this.ventanaCreador = ventanaCreador;
		this.prof = prof;
		this.datos = datos;
		
		setLayout(new BorderLayout());
				
		PanelHeader header = new PanelHeader("Ver Ratings por actividad");
		add(header, BorderLayout.NORTH);

        actsSistema = new PanelListActsSistema(this);
        add( actsSistema, BorderLayout.CENTER);
        
        panelReviews = new JPanel();
        add(panelReviews, BorderLayout.SOUTH);
        
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
	    if (seleccionado != null) {
	        mostrarReviews(seleccionado);
	    } else {
	        System.err.println("Actividad seleccionada es nula.");
	    }
	}

	
	private void mostrarReviews(Actividad actividad) {
	    panelReviews.removeAll();
	    
	    List<Review> reviews = actividad.getReviews();
	    
	    
	    JPanel reviewsPanel = new JPanel();
	    reviewsPanel.setLayout(new BorderLayout());
	    
	    if (reviews.isEmpty()) {
	        // Si no hay reseñas, muestra un mensaje
	        reviewsPanel.add(new JLabel("No hay reseñas para esta actividad."), BorderLayout.CENTER);
	    } else {
	        // Itera sobre las reseñas y agrégalas al panel
	        JPanel listPanel = new JPanel();
	        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
	        
	        for (Review rev : reviews) {
	            double rating = rev.getRating();
	            String comentario = rev.getContenido();
	            
	            JPanel reviewItem = new JPanel(new BorderLayout());
	            reviewItem.add(new JLabel("Rating: " + rating), BorderLayout.NORTH);
	            reviewItem.add(new JLabel("Contenido: " + comentario), BorderLayout.CENTER);
	            listPanel.add(reviewItem);
	        }
	        
	        reviewsPanel.add(new JScrollPane(listPanel), BorderLayout.CENTER);
	    }
	    
	    // Agrega las reseñas al panel principal
	    panelReviews.add(reviewsPanel, BorderLayout.CENTER);
	    
	    // Revalida y repinta para actualizar la vista
	    revalidate();
	    repaint();
	}


    public void cerrarVentana( )
    {
    	ventanaCreador.setVisible(true);
        dispose( );
    }

	
}
