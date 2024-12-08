package gui.interfazProfesor.Creador;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PanelDetallesCrearLP extends JPanel{
    
	private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtObjetivo;
    private JComboBox<String> cbbDificultad;
    
    
	public PanelDetallesCrearLP() {
		super();
		
		setLayout(new GridLayout(4,2));

        Font font = new Font("SansSerif", Font.BOLD, 18); 
        TitledBorder border = BorderFactory.createTitledBorder("Se necesita la siguiente información:");
        border.setTitleFont(font);
        setBorder(border);
        
        
        JLabel lblNombre = new JLabel("    Nombre");
        txtNombre = new JTextField();
        
        JLabel lblDescripcion = new JLabel("    Descripción");
        txtDescripcion = new JTextField();
        
        JLabel lblObjetivo = new JLabel("    Objetivo");
        txtObjetivo= new JTextField();
        
        JLabel lblDificultad = new JLabel("    Nivel de Dificultad");
        cbbDificultad = new JComboBox<>(new String[]{"1", "2", "3"});
        
        add(lblNombre);
        add(txtNombre);
        add(lblDescripcion);
        add(txtDescripcion);
        add(lblObjetivo);
        add(txtObjetivo);
        add(lblDificultad);
        add(cbbDificultad);

        
        
	}

	
    public int getDificultad( )
    {
        String difi = ( String )cbbDificultad.getSelectedItem( );
        return Integer.parseInt( difi );
    }

    /**
     * Indica el nombre digitado para el LP
     * @return
     */
    public String getNombre( )
    {
    	String nom = txtNombre.getText();
        return nom;
    }
    
    public String getObjetivo( )
    {
    	String obj = txtObjetivo.getText();
        return obj;
    }
    
    public String getDescripcion( )
    {
    	String desc = txtDescripcion.getText();
        return desc;
    }
}
