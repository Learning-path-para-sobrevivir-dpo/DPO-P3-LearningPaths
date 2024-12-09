package gui.interfazProfesor.Creador;

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
public class PanelCrearQuizVF extends JPanel implements ActionListener {
	
    private VentanaCrearActividad ventanaAct;
	
	private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JComboBox<String> cbbDificultad;
    private JTextField txtDuracion;
    private JComboBox<String> cbbObligatorio;
    private JTextField txtTiempo;
    private JTextField txtCalifMin;
    

	private static final String CREAR = "nuevo";
    private static final String CERRAR = "cerrar";

    private JButton bCrear;
    private JButton bCerrar;
	
	public PanelCrearQuizVF(VentanaCrearActividad ventanaAct) {
		super();
		this.ventanaAct = ventanaAct;
		
		setLayout(new GridLayout(10,2));

        Font font = new Font("SansSerif", Font.BOLD, 18); 
        TitledBorder border = BorderFactory.createTitledBorder("Se necesita la siguiente información:");
        border.setTitleFont(font);
        setBorder(border);
        
        
        JLabel lblNombre = new JLabel("    Nombre");
        txtNombre = new JTextField();
        
        JLabel lblDescripcion = new JLabel("    Descripción");
        txtDescripcion = new JTextField();
        
        JLabel lblDificultad = new JLabel("    Nivel de Dificultad");
        cbbDificultad = new JComboBox<>(new String[]{"1", "2", "3"});
        
        JLabel lblDuracion = new JLabel("    Duracion");
        txtDuracion= new JTextField();
        
        JLabel lblObligatorio = new JLabel("    Obligatorio?");
        cbbObligatorio= new JComboBox<>(new String[]{"Sí", "No"});
        
        JLabel lblTiempo = new JLabel("    Tiempo sugerido");
        txtTiempo= new JTextField();
        
        JLabel lblCalifMin = new JLabel("    Calificación mínima");
        txtCalifMin= new JTextField();
        
        
        add(lblNombre);
        add(txtNombre);
        add(lblDescripcion);
        add(txtDescripcion);
        add(lblDificultad);
        add(cbbDificultad);
        add(lblDuracion);
        add(txtDuracion);
        add(lblObligatorio);
        add(cbbObligatorio);
        add(lblTiempo);
        add(txtTiempo);
        add(lblCalifMin);
        add(txtCalifMin);

        bCrear = new JButton("Crear Quiz");
        bCrear.addActionListener(this);
        bCrear.setVisible(true);
        bCrear.setActionCommand(CREAR);
		bCrear.setFont(new Font("Calibri", Font.PLAIN, 18));
        this.add(bCrear);

        bCerrar = new JButton("Cerrar");
        bCerrar.addActionListener(this);
        bCerrar.setVisible(true);
        bCerrar.setActionCommand(CERRAR);
		bCerrar.setFont(new Font("Calibri", Font.PLAIN, 18));
        this.add(bCerrar);

        
		
		
	}

    @Override
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( CREAR ) )
        {
            ventanaAct.crearQuizVF( );
			JOptionPane.showMessageDialog(this, "Quiz Verdadero Falso creado exitosamente!");
			ventanaAct.cerrarVentana();

        }
        else if( comando.equals( CERRAR ) )
        {
            ventanaAct.cerrarVentana( );
        }
    }

	public String getNombre() {
    	String nom = txtNombre.getText();
        return nom; 
	}

	public String getDescripcion() {
    	String desc = txtDescripcion.getText();
        return desc;	
    }

	public int getDificultad() {
        String difi = ( String )cbbDificultad.getSelectedItem( );
        return Integer.parseInt( difi );
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
    	String x = txtTiempo.getText();
        return x; 
	}

	public String getCalifMin() {
    	String x = txtCalifMin.getText();
        return x;  
	}
	



}

