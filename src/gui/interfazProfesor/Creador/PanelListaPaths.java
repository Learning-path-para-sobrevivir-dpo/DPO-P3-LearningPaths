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

import modelo.LearningPath;
import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class PanelListaPaths extends JPanel implements ListSelectionListener{

    private DefaultListModel<LearningPath> dataModel;
    private JList<LearningPath> listaPaths;
	private VentanaEditarLP ventanaEditar;
    
	public PanelListaPaths(VentanaEditarLP ventanaEditar) {
		super();
		this.ventanaEditar = ventanaEditar;

        Font font = new Font("SansSerif", Font.BOLD, 18); 
        TitledBorder border = BorderFactory.createTitledBorder("Learning Paths Creados:");
        border.setTitleFont(font);

        setBorder(border);
        
        setLayout(new BorderLayout());
        
        dataModel = new DefaultListModel<>( );
        listaPaths = new JList<>( dataModel );
        listaPaths.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        listaPaths.addListSelectionListener( this );

        // Crear un panel con barras de desplazamiento para la lista
        JScrollPane scroll = new JScrollPane( listaPaths );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

        add( scroll );
        
	}

    /**
     * Actualiza la lista de learning paths que se muestran en la lista.
     * 
     * Para esto, lo que se modifica es el model (y no el JList)
     * @param learning paths
     */
    public void actualizarLearningPaths ( List<LearningPath> paths )
    {
        List<LearningPath> nuevosPaths = new ArrayList<LearningPath>( );
        for( LearningPath q : paths )
        {
            if( !dataModel.contains( q ) )
                nuevosPaths.add( q );
        }
        dataModel.addAll( nuevosPaths );
    }
    
    public void valueChanged( ListSelectionEvent e )
    {
        // Revisa cuál es el lp seleccionado actualmente
        LearningPath seleccionado = listaPaths.getSelectedValue( );


    }
    
    public LearningPath getPathSelected() {
    	return listaPaths.getSelectedValue();
    	}

    /**
     * Cambia el restaurante seleccionado en la lista
     * @param restaurante
     */
    public void seleccionarLearningPath( LearningPath path )
    {
        listaPaths.setSelectedValue( path, true );
    }


}