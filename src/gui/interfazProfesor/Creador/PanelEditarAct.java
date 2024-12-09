package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PanelEditarAct extends JPanel implements ActionListener{

	private VentanaEditarAct ventanaEditar;
	
    
	private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JComboBox<String> cbbDificultad;
    private JTextField txtDuracion;
    private JComboBox<String> cbbObligatorio;
    private JTextField txtTiempoSug;
    
    private JButton bGuardar;
    private static final String GUARDAR = "guardar";
    
    private JButton bCerrar;
    private static final String CERRAR = "cerrar";

    
	public PanelEditarAct(VentanaEditarAct ventanaEditar) {
		super();
		this.ventanaEditar = ventanaEditar;
		
        Font font = new Font("SansSerif", Font.BOLD, 18); 
        TitledBorder border = BorderFactory.createTitledBorder("Para modificar la Actividad seleccionada:");
        border.setTitleFont(font);

        setBorder(border);
		setLayout(new BorderLayout());

		JPanel elemModify = new JPanel();
		JPanel botonesActs = new JPanel();
		JPanel bGeneral = new JPanel();

		
		elemModify.setLayout(new GridLayout(6,2));
		
        JLabel lblNombre = new JLabel("    Nombre");
        txtNombre = new JTextField();
        
        JLabel lblDescripcion = new JLabel("    Descripción");
        txtDescripcion = new JTextField();
        
        JLabel lblDificultad = new JLabel("    Nivel de Dificultad");
        cbbDificultad = new JComboBox<>(new String[]{"1", "2", "3"});
        
        JLabel lblDuracion = new JLabel("    Duración");
        txtDuracion = new JTextField();
        
        JLabel lblObligatorio = new JLabel("    Obligatorio?");
        cbbObligatorio = new JComboBox<>(new String[]{"Sí", "No"});
        
        JLabel lblTiempo = new JLabel("    Tiempo Sugerido Completar");
        txtTiempoSug = new JTextField();
        
        elemModify.add(lblNombre);
        elemModify.add(txtNombre);
        elemModify.add(lblDescripcion);
        elemModify.add(txtDescripcion);
        elemModify.add(lblDificultad);
        elemModify.add(cbbDificultad);
        elemModify.add(lblDuracion);
        elemModify.add(txtDuracion);
        elemModify.add(lblObligatorio);
        elemModify.add(cbbObligatorio);
        elemModify.add(lblTiempo);
        elemModify.add(txtTiempoSug);

        
        bCerrar = new JButton("Cerrar");
        bCerrar.addActionListener(this);
        bCerrar.setVisible(true);
        bCerrar.setActionCommand(CERRAR);
		bCerrar.setFont(new Font("Calibri", Font.PLAIN, 18));
		bGeneral.add(bCerrar);
        
        bGuardar = new JButton("Guardar Cambios");
        bGuardar.addActionListener(this);
        bGuardar.setVisible(true);
        bGuardar.setActionCommand(GUARDAR);
        bGuardar.setFont(new Font("Calibri", Font.PLAIN, 18));
        bGeneral.add(bGuardar);

        
        add(elemModify, BorderLayout.NORTH);
        add(botonesActs, BorderLayout.CENTER);
        add(bGeneral, BorderLayout.SOUTH);

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
    
    
    public String getDescripcion( )
    {
    	String desc = txtDescripcion.getText();
        return desc;
    }
    
	public String getDuracion() {
    	String x = txtDuracion.getText();
        return x; 
	}

	public boolean getObligatorio() {
        String obligatorio = ( String )cbbObligatorio.getSelectedItem( );
        if (obligatorio == "Sí") {
        	return true;
        }
        else {
        	return false;
        }
	}

	public String getTiempo() {
    	String x = txtTiempoSug.getText();
        return x; 
	}
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( GUARDAR ) )
        {
        	ventanaEditar.guardarCambios();
			JOptionPane.showMessageDialog(this, "Cambios realizados exitosamente!");
			ventanaEditar.cerrarVentana();
        }
        else if( comando.equals( CERRAR ) )
        {
        	ventanaEditar.cerrarVentana( );
        }

    }


}
