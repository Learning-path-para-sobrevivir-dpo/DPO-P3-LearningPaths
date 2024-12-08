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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PanelEditarLP extends JPanel implements ActionListener{

	private VentanaLPCreados ventanaCreados;
	
    
	private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtObjetivo;
    private JComboBox<String> cbbDificultad;
    
    private JButton addAct;
    private static final String ADDACT = "add actividad";
    
    private JButton delAct;
    
    private JButton bGuardar;
    private static final String GUARDAR = "guardar";
    
    private JButton bCerrar;
    private static final String CERRAR = "cerrar";

    
	public PanelEditarLP(VentanaLPCreados ventanaCreados) {
		super();
		this.ventanaCreados = ventanaCreados;
		
        Font font = new Font("SansSerif", Font.BOLD, 18); 
        TitledBorder border = BorderFactory.createTitledBorder("Para modificar el Learning Path seleccionado:");
        border.setTitleFont(font);

        setBorder(border);
		setLayout(new BorderLayout());

		JPanel elemModify = new JPanel();
		JPanel botonesActs = new JPanel();
		JPanel bGeneral = new JPanel();

		
		elemModify.setLayout(new GridLayout(4,2));
		
        JLabel lblNombre = new JLabel("    Nombre");
        txtNombre = new JTextField();
        
        JLabel lblDescripcion = new JLabel("    Descripción");
        txtDescripcion = new JTextField();
        
        JLabel lblObjetivo = new JLabel("    Objetivo");
        txtObjetivo= new JTextField();
        
        JLabel lblDificultad = new JLabel("    Nivel de Dificultad");
        cbbDificultad = new JComboBox<>(new String[]{"1", "2", "3"});
        
        elemModify.add(lblNombre);
        elemModify.add(txtNombre);
        elemModify.add(lblDescripcion);
        elemModify.add(txtDescripcion);
        elemModify.add(lblObjetivo);
        elemModify.add(txtObjetivo);
        elemModify.add(lblDificultad);
        elemModify.add(cbbDificultad);
        
        addAct = new JButton("Añadir Actividad");
        addAct.addActionListener(this);
        addAct.setVisible(true);
        addAct.setActionCommand(ADDACT);
        addAct.setFont(new Font("Calibri", Font.PLAIN, 16));
        botonesActs.add(addAct);
		
        delAct = new JButton("Eliminar Actividad");        
        botonesActs.add(delAct);
        
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


    @Override
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( GUARDAR ) )
        {
            ventanaCreados.guardarCambios();
        }
        else if( comando.equals( CERRAR ) )
        {
            ventanaCreados.cerrarVentana( );
        }
        else if( comando.equals( ADDACT ) )
        {
            ventanaCreados.mostrarVentanaAddAct( );
        }
    }


	
	
}
