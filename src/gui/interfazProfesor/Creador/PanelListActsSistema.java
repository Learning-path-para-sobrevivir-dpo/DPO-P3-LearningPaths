package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.GUIManejoDatos;
import modelo.LearningPath;
import modelo.actividades.Actividad;

public class PanelListActsSistema extends JPanel implements ListSelectionListener{

    private DefaultListModel<Actividad> dataModel;
    private JList<Actividad> listaActs;
	
    private VentanaAddActividad ventanaAddAct;
	
	public PanelListActsSistema(VentanaAddActividad ventanaAddAct) {
		super();
		this.ventanaAddAct = ventanaAddAct;
		
        Font font = new Font("SansSerif", Font.BOLD, 18); 
        TitledBorder border = BorderFactory.createTitledBorder("Actividades en el Sistema:");
        border.setTitleFont(font);

        setBorder(border);
        
        setLayout(new BorderLayout());
        
        dataModel = new DefaultListModel<>( );
        listaActs = new JList<>( dataModel );
        listaActs.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        listaActs.addListSelectionListener( this );

        // Crear un panel con barras de desplazamiento para la lista
        JScrollPane scroll = new JScrollPane( listaActs );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

        add( scroll );
        
	}
	
    public void mostrarActs (List<Actividad> acts)
    {
        List<Actividad> mostrarActs = new ArrayList<Actividad>();
        for( Actividad a : acts )
        {
            if( !dataModel.contains( a ) )
                mostrarActs.add( a );
        }
        dataModel.addAll(mostrarActs);
    }

    @Override
    public void valueChanged( ListSelectionEvent e )
    {
        // Revisa cuál es el restaurante seleccionado actualmente
        Actividad seleccionado = listaActs.getSelectedValue( );

        // Le envía la ventana principal el restaurante seleccionado para que se actualice el resto de la interfaz
        this.ventanaAddAct.cambiarActSelected( seleccionado );
    }
    
    public Actividad getActSelected() {
    	return listaActs.getSelectedValue();
    }

    /**
     * Cambia el restaurante seleccionado en la lista
     * @param restaurante
     */
    public void seleccionarRestaurante( Actividad act )
    {
        listaActs.setSelectedValue( act, true );
    }


}
