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

import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class PanelListActsLP extends JPanel implements ListSelectionListener{


    private DefaultListModel<Actividad> dataModel;
    private JList<Actividad> listaActs;
	
    private VentanaEditarLP ventanaEditar;
	
	public PanelListActsLP(VentanaEditarLP ventanaEditar) {
		super();
		this.ventanaEditar = ventanaEditar;
		
        Font font = new Font("SansSerif", Font.BOLD, 18); 
        TitledBorder border = BorderFactory.createTitledBorder("Actividades en el Learning Path:");
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
	
    public void actualizarActs (List<Actividad> acts)
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
        Actividad seleccionado = listaActs.getSelectedValue( );

    }

    public Actividad getActSelected() {
    	
    	return listaActs.getSelectedValue();
    }

    public void seleccionarRestaurante( Actividad act )
    {
        listaActs.setSelectedValue( act, true );
    }
	
	

}
